package com.spring.voluptuaria.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private static final String[] EVERYBODY = {
            "/", "/homeFuncionario","/pesquisaCliente","/pesquisaDestino",
            "/pesquisaEmpresa", "/pesquisaFuncionario", "/pesquisaPacote","/pesquisaPassagem",
            "/manterCliente","/manterPacote","/manterPassagem"
    };

    private static final String[] ADMIN = {
            "/manterEmpresa","/manterDestino"
    };


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeRequests()
               .antMatchers(EVERYBODY).access("hasAnyAuthority('USER','ADMIN')")
               .antMatchers(ADMIN).access("hasAuthority('ADMIN')")
               .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
       .and()
       .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
       .logoutSuccessUrl("/login").permitAll();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).authorities("USER")
                .and().withUser("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN");
    }
}
