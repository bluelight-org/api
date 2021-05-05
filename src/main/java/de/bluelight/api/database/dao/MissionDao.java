package de.bluelight.api.database.dao;

import de.bluelight.api.database.model.Mission;
import org.springframework.data.repository.CrudRepository;

public interface MissionDao extends CrudRepository<Mission, Long> {
}
