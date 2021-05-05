package de.bluelight.api.database.dao;

import de.bluelight.api.database.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleDao extends CrudRepository<Vehicle, Long> {
}
