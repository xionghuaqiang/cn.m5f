package cn.m5f.config;

import cn.m5f.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author xhq
 * @date 2022/1/23 20:49
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                    .withUser("admin").password("123").roles("admin");
        auth.userDetailsService(userService);  //数据库
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasAnyRole("admin","user")//任意一个角色
                .antMatchers("/dba/**").hasRole("dba")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();//关闭cfs攻击
    }

    //    //路劲权限配置
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .antMatchers("/admin/**").hasRole("admin")
//                    .antMatchers("user/**").hasAnyRole("admin","user")//任意一个角色
//                    .anyRequest().authenticated() //剩下的登陆之后都可访问
//                    .and()
//                    .formLogin() //表单登陆
//                    .loginProcessingUrl("/doLogin")//登陆接口
//                    .loginPage("/login") //登陆页面
//                    .usernameParameter("name")//默认username 自定义
//                    .passwordParameter("password")
//                    .successHandler(new AuthenticationSuccessHandler() {//成功登陆回调
//                        @Override
//                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////                            authentication 保存了用户登陆成功的信息r
//                            response.setContentType("application/json;charset=utf-8");
//                            PrintWriter out = response.getWriter();
//                            HashMap<Object, Object> map = new HashMap<>();
//                            map.put("status",200);
//                            map.put("msg",authentication);
//                            out.write(new ObjectMapper().writeValueAsString(map));
//
//                            out.flush();
//                            out.close();
//                        }
//                    })
//                    .failureHandler(new AuthenticationFailureHandler() {  //登陆失败
//                        @Override
//                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                            response.setContentType("application/json;charset=utf-8");
//                            PrintWriter out = response.getWriter();
//                            HashMap<Object, Object> map = new HashMap<>();
//                            map.put("status",401);
//                            if(e instanceof LockedException){
//                                map.put("msg","账户被锁定");
//                            }else{
//                                map.put("msg","登陆失败");
//                            }
//                            out.write(new ObjectMapper().writeValueAsString(map));
//                        }
//                    })
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .logoutUrl("/logout")//注销登陆地址
//                    .logoutSuccessHandler(new LogoutSuccessHandler() {  //注销成功回调
//                        @Override
//                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                            response.setContentType("application/json;charset=utf-8");
//
//                            PrintWriter out = response.getWriter();
//                            HashMap<Object, Object> map = new HashMap<>();
//                            map.put("status",200);
//                            map.put("msg","注销登陆成功");
//                            out.write(new ObjectMapper().writeValueAsString(map));
//
//                            out.flush();
//                            out.close();
//                        }
//                    })
//                    .and()
//                    .csrf().disable();//关闭cfs攻击
//    }
}
