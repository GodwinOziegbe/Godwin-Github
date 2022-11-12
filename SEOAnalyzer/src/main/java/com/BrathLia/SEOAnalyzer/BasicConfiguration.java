package com.BrathLia.SEOAnalyzer;

import com.BrathLia.SEOAnalyzer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class handles the websecurity
 *
 * Needs rework and handled properly
 */
@Configuration
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        //TODO delete this and associated methods, this is used to test login with mock-users
        System.out.println(userService.userList());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    /**
     * Configures the security.
     * currently permits all access to getGoogle endpoint. Used for hack
     * Index is blocked by authentication instead of a proper login
     *
     * TODO enable ConnectID
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v0.1/getGoogle").permitAll()
                .antMatchers("/index").hasAnyAuthority("user")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/index.html", true);

        http.headers().frameOptions().disable();

    }

}
