package de.bluelight.api.database.dao;

import de.bluelight.api.database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
}
