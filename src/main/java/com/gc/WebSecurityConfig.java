package com.gc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login_fail", "/login_success", "/register").permitAll()
                .antMatchers("/roleTest").hasAnyAuthority("admin")
                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login_fail")
                .successForwardUrl("/login_success")
                .usernameParameter("account")
                .passwordParameter("password")
                .permitAll()

                .and().logout()
                .logoutSuccessUrl("/login_success")
                .permitAll()

                .and().csrf().disable()

                .rememberMe()
                .alwaysRemember(true)//待修改
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(31536000);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenReposistoryImpl = new JdbcTokenRepositoryImpl();
        tokenReposistoryImpl.setDataSource(dataSource);
        return tokenReposistoryImpl;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication().
                dataSource(this.dataSource)
                .usersByUsernameQuery("SELECT account, password,enabled " +
                        "From user WHERE account = ?")
                .authoritiesByUsernameQuery("SELECT account, description " +
                        "From user , user_role , role" +
                        "WHERE user.id=user_role.user_id AND user_role.role_id=role.id AND account = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}