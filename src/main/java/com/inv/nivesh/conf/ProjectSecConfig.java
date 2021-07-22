package com.inv.nivesh.conf;

import com.inv.nivesh.repositories.UserRepository;
import com.inv.nivesh.service.JdbcUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProjectSecConfig {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("Created User Details service");
        /*InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("ishan")
                .password("12345")
                .authorities("read")
                .build();

        uds.createUser(u1);
        return uds;*/
        return new JdbcUserDetailService(userRepository);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
