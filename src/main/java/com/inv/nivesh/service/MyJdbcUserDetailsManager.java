package com.inv.nivesh.service;

import com.inv.nivesh.entity.User;
import com.inv.nivesh.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

@RequiredArgsConstructor
public class MyJdbcUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDetails user) {
        SecurityUser sa = (SecurityUser) user;
        userRepository.save(sa.getUser());
    }

    @Override
    public void updateUser(UserDetails user) {
        SecurityUser sa = (SecurityUser) user;
        userRepository.save(sa.getUser());
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user: " + username + " Not found"));
        return new SecurityUser(user);
    }
}
