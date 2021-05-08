package de.bluelight.api.database.service;

import de.bluelight.api.controller.profile.ProfileDTO;
import de.bluelight.api.database.dao.UserDao;
import de.bluelight.api.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUser(long userId) {
        return userDao.findById(userId).orElseThrow();
    }

    public User createUser(ProfileDTO profileDTO) {
        User user = new User();
        user.setName(profileDTO.getName());
        user.setEmail(profileDTO.getEmail());
        user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        return userDao.save(user);
    }

    public boolean isEmailAvailable(String email) {
        return userDao.getEmailCount(email) == 0;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getByName(name).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", name))
        );
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }
}
