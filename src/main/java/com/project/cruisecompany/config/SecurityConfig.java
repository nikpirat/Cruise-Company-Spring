package com.project.cruisecompany.config;

import com.project.cruisecompany.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/","/index", "/registration").permitAll()
//                    .antMatchers("/admin").hasRole("ROLE_ADMIN")
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/").permitAll()
//                    .defaultSuccessUrl("/user", true)
//                    .usernameParameter("login")
//                    .passwordParameter("password")
//                    .and()
//                .logout().logoutSuccessUrl("/")
//                .permitAll()
//        ;

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index","/registration").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .loginPage("/").permitAll()
                .defaultSuccessUrl("/user", true)
                .usernameParameter("login")
                .passwordParameter("password")

                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
    }
}