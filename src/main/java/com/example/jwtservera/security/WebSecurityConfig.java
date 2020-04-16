package com.example.jwtservera.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // 关闭跨站请求防护
        .cors().and().csrf().disable()
        // 允许不登陆就可以访问的方法，多个用逗号分隔
        .authorizeRequests().antMatchers("/test").permitAll()
        // 其他的需要授权后访问
        .anyRequest().authenticated()

        .and()
        // 增加是否登陸过滤
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  }


}