package de.bluelight.api.database.dao;

import de.bluelight.api.database.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User, Long> {

    @Query(value = "SELECT COUNT(1) FROM users WHERE email = :email", nativeQuery = true)
    int getEmailCount(@Param("email") String email);

}
