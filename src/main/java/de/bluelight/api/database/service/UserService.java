package de.bluelight.api.database.service;

import de.bluelight.api.controller.profile.ProfileDTO;
import de.bluelight.api.database.dao.UserDao;
import de.bluelight.api.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

}
