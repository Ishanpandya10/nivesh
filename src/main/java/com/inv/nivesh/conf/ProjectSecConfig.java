package com.inv.nivesh.conf;

import com.inv.nivesh.repositories.UserRepository;
import com.inv.nivesh.service.MyJdbcUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProjectSecConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

    @Bean
    public MyJdbcUserDetailsManager userDetailsService() {
        System.out.println("Created User Details service");
        /*InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("ishan")
                .password("12345")
                .authorities("read")
                .build();

        uds.createUser(u1);
        return uds;*/
        //return new JdbcUserDetailService(userRepository);
        return new MyJdbcUserDetailsManager(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/createUser", "/saveUser").permitAll()
                .anyRequest().authenticated();
    }
}
