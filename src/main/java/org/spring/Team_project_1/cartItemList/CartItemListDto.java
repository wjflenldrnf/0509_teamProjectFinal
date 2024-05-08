package org.spring.Team_project_1.cartItemList;

import lombok.*;
import org.spring.Team_project_1.entity.CartEntity;
import org.spring.Team_project_1.entity.CartItemListEntity;
import org.spring.Team_project_1.entity.ShopEntity;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemListDto {

  private Long id;

  private int count;

  private ShopEntity shopEntity;

  private CartEntity cartEntity;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  public static CartItemListDto toSelectCart(CartItemListEntity cartItemList) {
    CartItemListDto cartItemListDto=new CartItemListDto();

    cartItemListDto.setId(cartItemList.getId());
    cartItemListDto.setCount(cartItemList.getCount());
    cartItemListDto.setCreateTime(cartItemList.getCreateTime());
    cartItemListDto.setUpdateTime(cartItemList.getUpdateTime());
    cartItemListDto.setCartEntity(cartItemList.getCartEntity());
    cartItemListDto.setShopEntity(cartItemList.getShopEntity());

    return cartItemListDto;



  }
}
