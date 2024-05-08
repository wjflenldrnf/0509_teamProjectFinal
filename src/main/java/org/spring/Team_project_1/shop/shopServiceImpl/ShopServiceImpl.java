package org.spring.Team_project_1.shop.shopServiceImpl;

import org.spring.Team_project_1.cartItemList.CartItemListDto;
import org.spring.Team_project_1.shop.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ShopServiceImpl {
  void writeShop(ShopDto shopDto) throws IOException;


//  Page<ShopDto> shopList(Pageable pageable);

  void updateHit(Long id);


  ShopDto detail(Long id);

  void updateShop(ShopDto shopDto) throws IOException;

  void shopDelete(Long id);

  ShopDto sellPage(Long id);

  Page<ShopDto> ShopSearchPagingList(Pageable pageable, String subject, String search);

  void addCart(Long id, Long id1, ShopDto shopDto,int priceCount);

  int good(Long id);

  int bad(Long id);

  List<ShopDto> shopPhoto();

  List<ShopDto> shopList();

  List<CartItemListDto> cartList(Long id);


//  List<ShopDto> shopOnClick(String title);

  List<ShopDto> shopSearchList(String subjectTwo, String searchTwo);

  List<ShopDto> shopPlaceAll(String search);

  List<ShopDto> hotList(ShopDto shopDto);


}
