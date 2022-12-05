package com.brainerhub.loan.commonutil;

import com.brainerhub.loan.entity.Role;
import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.repository.RoleRepository;
import com.brainerhub.loan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DefaultData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roleList = roleRepository.findAllByIdIn(Arrays.asList(1L, 2L));
        if (roleList.isEmpty()) {
            roleRepository.save(new Role(1L, "ROLE_ADMIN"));
            roleRepository.save(new Role(2L, "ROLE_USER"));
        }
        User existAdmin = userRepository.findUserByEmail(Constant.ADMIN_EMAIL);
        if (existAdmin == null) {
            User user = User.builder()
                    .id(1L)
                    .email(Constant.ADMIN_EMAIL)
                    .age(28.0)
                    .password(bcryptEncoder.encode("Admin@123"))
                    .role(roleRepository.findByRole(Constant.ROLE_ADMIN))
                    .build();
            userRepository.save(user);
        }
    }
}
