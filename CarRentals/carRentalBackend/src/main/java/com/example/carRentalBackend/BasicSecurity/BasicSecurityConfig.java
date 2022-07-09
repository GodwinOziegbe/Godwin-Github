package com.example.carRentalBackend.BasicSecurity;

import com.example.carRentalBackend.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
              // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/v1/login**").hasAnyRole(ApplicationUserRole.CUSTOMER.name(),ApplicationUserRole.ADMIN.name())
                .antMatchers("/api/v1/car**").hasAnyRole(ApplicationUserRole.CUSTOMER.name(),ApplicationUserRole.ADMIN.name())
                .antMatchers("/api/v1/rental**").hasAnyRole(ApplicationUserRole.CUSTOMER.name(),ApplicationUserRole.ADMIN.name())
                .antMatchers("/api/v1/getcustomer**").hasAnyRole(ApplicationUserRole.CUSTOMER.name(),ApplicationUserRole.ADMIN.name())
               //.antMatchers("/api/v1/car**").hasAnyRole(ApplicationUserRole.STUDENT.name(),ApplicationUserRole.ADMIN.name())
                //.antMatchers("/api/v1/rental**").hasAnyRole(ApplicationUserRole.STUDENT.name(),ApplicationUserRole.ADMIN.name())
                .antMatchers("/api/v1/admin**").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers("/api/v1/admin/addcustomer").hasRole(ApplicationUserRole.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMINTRAINEE.name(),ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
      Customer customer;
        UserDetails annaSmithUser=User.builder()
                .username(("annasmith"))
                .password((passwordEncoder.encode("password")))
               // .roles(ApplicationUserRole.STUDENT.name())
                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())

                .build();
       UserDetails lindaUser=User.builder()
            .username(("linda"))
            .password((passwordEncoder.encode("password1234")))
            //.roles(ApplicationUserRole.ADMIN.name())
               .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())

            .build();

        UserDetails godwinUser=User.builder()
                .username(("godwin"))
                .password((passwordEncoder.encode("1234")))
                //.roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())

                .build();

        UserDetails janeUser=User.builder()
                .username(("jane"))
                .password((passwordEncoder.encode("1234")))
                //.roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())

                .build();

        UserDetails TomUser=User.builder()
                .username(("tom"))
                .password((passwordEncoder.encode("password1234")))
              //  .roles(ApplicationUserRole.ADMINTRAINEE.name())
                .authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                annaSmithUser,lindaUser,TomUser,godwinUser,janeUser
        );
    }
}
