package be.thomasmore.graduaten.diceroll;

import be.thomasmore.graduaten.diceroll.objects.LoginFailureHandler;
import be.thomasmore.graduaten.diceroll.objects.LoginSuccessHandler;
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
    private final LoginSuccessHandler authenticationSuccessHandler;
    private final LoginFailureHandler authenticationFailureHandler;

    //Constructors
    @Autowired
    public SecurityConfig(AuthUserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder,
                          LoginSuccessHandler authenticationSuccessHandler, LoginFailureHandler authenticationFailureHandler) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
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
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                .and().csrf().disable();
    }
}
