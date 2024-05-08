package org.spring.Team_project_1.shop;

import org.spring.Team_project_1.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity,Long> {


  @Modifying
  @Query(value = " update shop_tb1 s set s.hit=s.hit+1  where shop_id=:id ", nativeQuery = true)
  void updateHit(@Param("id") Long id);

  Page<ShopEntity> findByTitleContains(Pageable pageable, String search);

  Page<ShopEntity> findByContentContains(Pageable pageable, String search);

  @Modifying
  @Query(value = "update shop_tb1 s set s.good=s.good+1 where shop_id=:id ", nativeQuery = true)
  void good(@Param("id") Long id);

  @Modifying
  @Query(value = "update shop_tb1 s set s.bad=s.bad+1 where shop_id=:id ", nativeQuery = true)
  void bad(@Param("id") Long id);



  Optional<ShopEntity> findByPriceCount(int priceCount);



  List<ShopEntity> findByTitleContains(String subject);
  List<ShopEntity> findByContentContains(String search);

  List<ShopEntity> findByPlace(String place);

  List<ShopEntity> findByPlaceContains(String search);
  List<ShopEntity> findTop4ByOrderByHitDesc();

    List<ShopEntity> findTop3ByOrderByHitDesc();
}
