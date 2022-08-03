package com.smile.auth.service;

import com.smile.api.admin.feign.UserFeignClient;
import com.smile.auth.domain.SecurityUser;
import com.smile.basic.core.base.Result;
import com.smile.basic.core.constant.MessageConstant;
import com.smile.basic.core.dto.sys.UserDTO;
import com.smile.basic.core.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户管理业务类
 * 这段代码主要是spring security 给我们提供查询用户的
 */
@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserFeignClient userFeignClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里查询数据库
        Result<UserDTO> result = userFeignClient.loadUserByUsername(username);
        System.out.println(result);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SecurityUser securityUser = new SecurityUser(result.getData());
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }


    /** @PostConstruct
    public void initData() {
    String password = passwordEncoder.encode("123456");
    userList = new ArrayList<>();
    userList.add(new UserDTO("1","admin", password,1,"client-app",
    CollUtil.toList("ADMIN")));
    userList.add(new UserDTO("2","test", password,1, "client-app",
    CollUtil.toList("TEST")));
    }*/

}
