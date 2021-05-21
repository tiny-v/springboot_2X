package com.tinyv.demo.global.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

/**
 * @author TinyV
 * @date 2020/5/13
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /** 使用用户名查询密码 */
    String pwdQuery = "select user_name, pwd, available from t_user where user_name = ?";

    /** 使用用户名查询角色 */
    String roleQuery = "select u.user_name, r.role_name from t_user u, t_user_role ur, t_role r "
                        + "where u.id = ur.user_id and r.id = ur.role_id and u.user_name = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication()
            // 密码编码器
            .passwordEncoder(passwordEncoder)
            // 数据源
            .dataSource(dataSource)
            // 查询用户，自动判断密码是否一致
            .usersByUsernameQuery(pwdQuery)
            // 赋予权限
            .authoritiesByUsernameQuery(roleQuery);
    }




}
