package com.expenses.jonsnow.service;

import com.expenses.jonsnow.model.SecurityUser;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired) )
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepo users;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.findByUserName(username);
        SecurityUser securityUser = new SecurityUser(user.get());
        String name = securityUser.getUsername();
        if(!username.equals(name)){
            throw new UsernameNotFoundException("UserNmae not founf");
        } else {
            return securityUser;
        }
    }
}
