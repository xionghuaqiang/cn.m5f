package cn.m5f.service;

import cn.m5f.bean.User;
import cn.m5f.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xhq
 * @date 2022/5/27 17:13
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登陆时候传的用户名
        User user = userMapper.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));  //如果存在，就根据用户名id 去查全拥有的角色

        return user;
    }
}
