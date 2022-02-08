package com.sjarno.springangularcrud.services;

import java.util.Optional;
import java.util.stream.Collectors;

import com.sjarno.springangularcrud.models.UserAccount;
import com.sjarno.springangularcrud.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(username);
        if (userAccount.isPresent()) {
            return new User(
                userAccount.get().getUsername(),
                userAccount.get().getPassword(),
                true,
                true,
                true,
                true,
                userAccount.get().getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        }
        throw new UsernameNotFoundException("No such user as "+username);
    }
    
}
