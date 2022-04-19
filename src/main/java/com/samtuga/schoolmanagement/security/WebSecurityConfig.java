package com.samtuga.schoolmanagement.security;

import com.samtuga.schoolmanagement.service.CustomAdminDetailsService;
import com.samtuga.schoolmanagement.service.CustomStudentDetailsService;
import com.samtuga.schoolmanagement.service.CustomTeacherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAdminDetailsService customAdminDetailsService;
    @Autowired
    private CustomStudentDetailsService studentDetailsService;
    @Autowired
    private CustomTeacherDetailsService teacherDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/admin/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated();

        http
                .csrf().disable()
                .headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.inMemoryAuthentication().withUser("samwus47@yahoo.com")
                .password(bCryptPasswordEncoder.encode("12345")).authorities("TEACHER");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customAdminDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider daoAuth(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoProvider.setUserDetailsService(studentDetailsService);
        return  daoProvider;
    }

    @Bean
    public DaoAuthenticationProvider dao(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(teacherDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }



}
