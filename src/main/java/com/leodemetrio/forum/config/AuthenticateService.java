package com.leodemetrio.forum.config;

import com.leodemetrio.forum.model.User;
import com.leodemetrio.forum.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isPresent()) return user.get();
        throw new UsernameNotFoundException("Data invalid");
    }
}
