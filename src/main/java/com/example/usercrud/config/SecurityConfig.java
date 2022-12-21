package com.example.usercrud.config;

import com.example.usercrud.service.serviceImp.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        // Các trang không yêu cầu login
        httpSecurity.authorizeRequests()
                .antMatchers("/","/login","/logout")
                .permitAll();
        // Trang userInfo yêu cầu login với vai trò ROLE_USER hoặc ROLE_ADMIN
        httpSecurity.authorizeRequests()
                .antMatchers("/userInfo")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");
        //Trang chỉ dành cho ADMIN
        httpSecurity.authorizeRequests()
                .antMatchers("/admin")
                .access("hasRole('ROLE_ADMIN')");
        //Ngoại lệ AccessDeniedException không đúng role
        httpSecurity.authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        //Cấu hình login form
        httpSecurity.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userAccountInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
