package org.bolcom.app.infrastracture.repositories;

import org.bolcom.app.infrastracture.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends JpaRepository<UserEntity,String>  {
}
