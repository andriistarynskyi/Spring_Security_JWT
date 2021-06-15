package com.example.spring_boot_jwt.service;

import com.example.spring_boot_jwt.model.User;
import com.example.spring_boot_jwt.model.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SecurityUserAService implements UserDetailsService {
    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByLogin(userName);
        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new UserPrincipal(user);
    }
}
