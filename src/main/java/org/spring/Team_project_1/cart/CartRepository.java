package org.spring.Team_project_1.cart;

import org.spring.Team_project_1.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
  Optional<CartEntity> findByMemberEntityId(Long id);
}
