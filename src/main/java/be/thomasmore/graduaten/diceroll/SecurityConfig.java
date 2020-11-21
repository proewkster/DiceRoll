package be.thomasmore.graduaten.diceroll;

import be.thomasmore.graduaten.diceroll.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Attributes
    private final AuthUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    //Constructors
    @Autowired
    public SecurityConfig(AuthUserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    //Getters and Setters
    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    //Methods
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("").hasAnyRole("User") //Add pages accessible for Users
                //.antMatchers("").hasAnyRole("Admin") // Add pages accessible for Admins
                .and().formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/authenticated")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and().logout()
                    .logoutSuccessUrl("/login?logout=true")
                .and().csrf().disable();
    }
}
