package com.rest.shoppinglist.controllers;

import com.rest.shoppinglist.config.TokenProvider;
import com.rest.shoppinglist.dao.ConfirmationTokenDao;
import com.rest.shoppinglist.models.ConfirmationToken;
import com.rest.shoppinglist.models.UserPrincipal;
import com.rest.shoppinglist.payload.JwtRequest;
import com.rest.shoppinglist.payload.JwtResponse;
import com.rest.shoppinglist.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fassil on 05/12/20.
 */
public class AuthenticationController {
    final AuthenticationManager authenticationManager;
    final TokenProvider tokenProvider;
    final ConfirmationTokenDao confirmationTokenDao;
    final UserService userService;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, ConfirmationTokenDao confirmationTokenDao, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.confirmationTokenDao = confirmationTokenDao;
        this.userService = userService;
    }

    @PostMapping(value = "/v1/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        // authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        logger.info("Received login request: POST "+jwtRequest.getEmail()+", "+jwtRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        // Users user = (Users) authentication.getPrincipal();
        logger.info("user login: "+ userDetails.getEmail() + " token: "+ jwt);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getFullName(), userDetails.getEmail()));

    }

    @GetMapping(value = "/v1/confirm-account")
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken){
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);
        logger.info("{confirmUserAccount} Received confirm account request: GET /confirm-account?token="+ confirmationToken);
        if(token != null) {
            UserDetails user = (UserDetails) userService.getUserByEmail(token.getUser().getEmail());
            // user.setEnabled(true);
            userService.updateUser(user.getUsername());
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
