package com.sarpio.security.services;

import com.sarpio.security.model.CustomUserDetails;
import com.sarpio.security.model.UsersEntity;
import com.sarpio.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsersEntity> userByLogin = userRepository.findByUsername(username);
        userByLogin.orElseThrow(() -> new UsernameNotFoundException("Username does not exists"));
        return userByLogin.map(CustomUserDetails::new).get();
    }

}

