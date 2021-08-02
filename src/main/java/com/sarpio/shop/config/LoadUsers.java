package com.sarpio.shop.config;

import com.sarpio.security.model.RoleEntity;
import com.sarpio.security.model.UsersEntity;
import com.sarpio.security.repository.RoleRepository;
import com.sarpio.security.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadUsers {

    @Bean
    CommandLineRunner initUsers(RoleRepository repository) {

        final Logger log = LoggerFactory.getLogger(LoadUsers.class);
        RoleEntity admin = new RoleEntity();
        RoleEntity user = new RoleEntity();
        admin.setRole("ADMIN");
        user.setRole("USER");
        return args -> {
            log.info("Preloading" + repository.save(admin));
            log.info("Preloading" + repository.save(user));
        };
    }
}
