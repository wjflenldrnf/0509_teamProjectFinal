package org.spring.Team_project_1.cartItemList.cartItemListServiceImpl;

import org.spring.Team_project_1.cartItemList.CartItemListDto;

import java.util.List;

public interface CartItemListServiceImpl {
  List<CartItemListDto> cartList(Long id);

  CartItemListDto cartSelect(Long id);

  int cartDelete(Long id);

  int cartBuy(List<Long> cartIds);

  int cartBuys(List<Long> cartIds);
}
