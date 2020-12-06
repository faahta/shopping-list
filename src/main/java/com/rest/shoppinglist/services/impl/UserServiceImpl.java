package com.rest.shoppinglist.services.impl;

import com.rest.shoppinglist.dao.UserDao;
import com.rest.shoppinglist.models.ConfirmationToken;
import com.rest.shoppinglist.models.Users;
import com.rest.shoppinglist.services.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * Created by Fassil on 06/12/20.
 */
@Repository
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userDao.findByEmail(email) !=null ? true : false;
    }

    @Override
    public void registerUser(Users u) throws MessagingException {
        Users user = new Users(u.getEmail(),
                encoder.encode(u.getPassword()), u.getFullName());

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        userDao.add(user, confirmationToken);

        //send welcome email message
        MimeMessage mail = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("token",confirmationToken.getConfirmationToken());
        context.setVariable("user",user);
        String body = templateEngine.process("registrationConfirmation", context);

        MimeMessageHelper helper = new MimeMessageHelper(mail, true);

        helper.setFrom("hexoad.bus@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Welcome to HexoBus");
        helper.setText(body, true);
        javaMailSender.send(mail);

    }

    @Override
    public void deleteUserAccount(Integer userId) {
        this.userDao.deletAccount(userId);
    }

    @Override
    public boolean getUserById(Integer userId) {
        if( this.userDao.findById(userId) !=null){
            return true;
        }
        return false;
    }

    @Override
    public Users getUserByEmail(String email) {
        return userDao.getUserByEmail();
    }

    @Override
    public void updateUser(String username) {
        this.userDao.updateUser(username);
    }
}
