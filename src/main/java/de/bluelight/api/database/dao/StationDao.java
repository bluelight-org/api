package de.bluelight.api.database.dao;

import de.bluelight.api.database.model.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationDao extends CrudRepository<Station, Long> {
}
