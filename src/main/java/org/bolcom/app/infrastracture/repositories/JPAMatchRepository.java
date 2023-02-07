package org.bolcom.app.infrastracture.repositories;

import org.bolcom.app.infrastracture.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAMatchRepository extends JpaRepository<MatchEntity, Long> {
}
