package cn.m5f.mapper;

import cn.m5f.bean.Role;
import cn.m5f.bean.User;

import java.util.List;

public interface UserMapper {
    //用户名查   密码security 自己会去匹配不需要我们管
    User loadUserByUsername(String username);
    //根据用户名id 查所拥有的角色
    List<Role> getUserRolesById(Integer id);
}
