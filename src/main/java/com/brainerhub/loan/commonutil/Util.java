package com.brainerhub.loan.commonutil;

import com.brainerhub.loan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    private UserRepository userRepository;

    public com.brainerhub.loan.entity.User getAuthenticationUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.brainerhub.loan.entity.User users = userRepository.findUserByEmail(user.getUsername());
        return users;
    }
}
