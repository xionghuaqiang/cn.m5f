package cn.m5f.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xhq
 * @date 2022/5/27 16:52
 */
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private List<Role> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {   //账户是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   //账户是否未锁定
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {   //返回用户的所有角色
        //我们查到的是 List<Role> roles; 然后重新整理放进 Collextion
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
