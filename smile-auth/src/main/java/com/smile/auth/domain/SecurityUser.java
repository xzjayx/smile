package com.smile.auth.domain;

import com.smile.basic.core.dto.sys.UserDTO;
import com.smile.basic.core.enums.PasswordEncoderTypeEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @see org.springframework.security.core.userdetails.User 源码默认实现可以定制化改动
 * 登录用户信息
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态 1表示启动 0 表示禁用 下面等等账户锁定之类的都可以动态注入 isAccountNonLocked
     */
    private Boolean enabled;
    /**
     * 权限数据主要查询出来角色  ROLE_
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {

    }

    public SecurityUser(UserDTO userDTO) {
        this.setId(userDTO.getId());
        this.setUsername(userDTO.getUsername());
        /*
        * 简单场景里，直接用+好了可以拼接null，反正编译器默认会优化成StringBuilder，毕竟对一般人来说加号可读性高一点。
        * 但是在循环中使用或者是比较复杂的应用场景里，还是尽量自己直接用StringBuilder或String concat(@NotNULL)。
        * */
        this.setPassword(PasswordEncoderTypeEnum.NOOP.getPrefix().concat(userDTO.getPassword()));
        this.setEnabled(userDTO.getEnableStatus());
        if (userDTO.getRoles() != null) {
            authorities = new ArrayList<>();
            userDTO.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
