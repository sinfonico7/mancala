package org.bolcom.app.player.adapter;

import org.bolcom.app.player.model.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MancalaPlayerRepository extends JpaRepository<PlayerEntity,Long> {
}
