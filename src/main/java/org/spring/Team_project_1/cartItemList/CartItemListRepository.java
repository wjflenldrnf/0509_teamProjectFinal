package org.spring.Team_project_1.cartItemList;

import org.spring.Team_project_1.entity.CartItemListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemListRepository extends JpaRepository<CartItemListEntity,Long> {
  List<CartItemListEntity> findByCartEntityIdAndShopEntityId(Long id, Long id1);

  List<CartItemListEntity> findAllByCartEntityId(Long id);
}
