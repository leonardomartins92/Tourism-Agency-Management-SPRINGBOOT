package com.spring.voluptuaria.configuration;

import com.spring.voluptuaria.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myuserDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}


    private static final String[] EVERYBODY = {
            "/", "/homeFuncionario", "/pesquisaCliente", "/pesquisaDestino",
            "/pesquisaEmpresa", "/pesquisaFuncionario", "/pesquisaPacote", "/pesquisaPassagem",
            "/manterCliente", "/manterPacote", "/manterPassagem", "/manterDestino"
    };

    private static final String[] ADMIN = {
            "/manterEmpresa", "/manterFuncionario"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(EVERYBODY).hasAnyRole("ADMIN", "USER")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .antMatchers("/primeiroAcesso").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myuserDetailsService);
    }
}
