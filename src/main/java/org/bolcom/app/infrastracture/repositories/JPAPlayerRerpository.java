package org.bolcom.app.infrastracture.repositories;
import org.bolcom.app.infrastracture.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAPlayerRerpository extends JpaRepository<PlayerEntity,UUID> {


}
