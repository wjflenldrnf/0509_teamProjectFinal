package org.spring.Team_project_1.shopReply;

import org.spring.Team_project_1.entity.ShopEntity;
import org.spring.Team_project_1.entity.ShopReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ShopReplyRepository extends JpaRepository<ShopReplyEntity,Long> {

//    @Query(value = "select r.* \n" +
//            "from shop_reply_tb1 r inner join  shop_tb1 b\n" +
//            "on r.shop_id=b.shop_id\n" +
//            "where r.shop_id" , nativeQuery = true)
//    List<ShopReplyEntity> replyJoinShop(@Param("id") Long id);


    @Query(value = "select r.* \n" +
            "from shop_reply_tb1 r inner join shop_tb1 b\n" +
            "on r.shop_id=b.shop_id\n" +
            "where b.shop_id=:id" , nativeQuery = true)
    List<ShopReplyEntity> replyJoinShop(@Param("id") Long id);

    List<ShopReplyEntity> findAllByShopEntity(ShopEntity shopEntity);

    @Modifying
    @Query(value = "update shop_reply_tb1 s set s.good=s.good+1 where shop_reply_id=:id ", nativeQuery = true)
    void replyGood(@Param("id") Long id);

    @Modifying
    @Query(value = "update shop_reply_tb1 s set s.bad=s.bad+1 where shop_reply_id=:id ", nativeQuery = true)
    void replyBad(@Param("id") Long id);
}
