package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.user.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService getuserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder(10);
		
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider  authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(this.getuserDetailsService());
		authenticationProvider.setPasswordEncoder(this.getBCryptPasswordEncoder());
		
		return authenticationProvider;
		
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/adduser").hasRole("ADMIN")
                .antMatchers("/user/delete/**").hasRole("ADMIN")
                .antMatchers("/user/userid/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/user/allusers").hasAnyRole("USER","ADMIN")
                .antMatchers("/employee/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();




    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(daoAuthenticationProvider());
    }

}
