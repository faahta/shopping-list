package com.rest.shoppinglist.controllers;

import com.rest.shoppinglist.config.TokenProvider;
import com.rest.shoppinglist.dao.UserDao;
import com.rest.shoppinglist.models.UserPrincipal;
import com.rest.shoppinglist.models.Users;
import com.rest.shoppinglist.payload.JwtResponse;
import com.rest.shoppinglist.services.UserService;
import com.rest.shoppinglist.util.exception.UserAlreadyExistsException;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Fassil on 06/12/20.
 */
@RestController
public class UserController {
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);
    final AuthenticationManager authenticationManager;
    private final UserDao userRepository;
    private final TokenProvider tokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDao userRepository, TokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/v1/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody Users user) throws UserAlreadyExistsException, MessagingException, MessagingException {
        logger.info("{registerUser} - Received user registration request: POST /v1/user "+user.getEmail());
        //Let's check if user already registered with us
        if(userService.checkIfUserExist (user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists for this email");
        }
        userService.registerUser(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/api/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUserByEmail(@PathVariable(value = "email")String email) throws NotFoundException {
        logger.info("{getUserByEmail} - Received request - GET /v1/api/user/"+email);
        Users user = userService.getUserByEmail(email);
        if(user.equals(null)){ throw new NotFoundException("User not found"); }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/v1/api/user/me")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<JwtResponse> getCurrentUser(@RequestHeader("Authorization") String authToken) {
        logger.info("{getCurrentUser} - Received GET /v1/user/me: Token: "+ authToken);
        Users user = this.tokenProvider.getUserFromToken(authToken);
        logger.info("User id "+ user.getUserId());
        logger.info("Password "+ user.getPassword());


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getFullName()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(authToken, user.getUserId(), user.getFullName(), user.getEmail()));
    }

//    @DeleteMapping(value = "/v1/api/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deleteUserAccount(@PathVariable("userId") UUID userId){
//        logger.info("{deleteUserAccount} - Received request - DELETE /v1/api/users/"+userId);
//        if(userService.getUserById(userId) == null){ return ResponseEntity.notFound().build(); }
//        userService.deleteUserAccount(userId);
//        return ResponseEntity.ok().body(HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/v1/api/user", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> checkPassword(@RequestBody RequestChangePassword requestChangePassword){
//        logger.info("{checkPassword} - Received request - POST /v1/api/user - {} "+requestChangePassword.getEmail());
//        Users user = userService.getUserByEmail(requestChangePassword.getEmail());
//        userService.checkUserPassword(requestChangePassword.getPassword(), user);
//        return ResponseEntity.ok().body(new ResponseCheckPassword(requestChangePassword.getEmail(), true));
//    }
//
//    @PutMapping(value = "/v1/api/user", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> changePassword(@RequestBody RequestChangePassword requestChangePassword){
//        logger.info("{changePassword} - Received request - PUT /v1/api/user - {} "+requestChangePassword.getEmail());
//        userService.changeUserPassword(requestChangePassword.getEmail(), requestChangePassword.getPassword());
//        return ResponseEntity.ok().body(HttpStatus.OK);
//    }

}
