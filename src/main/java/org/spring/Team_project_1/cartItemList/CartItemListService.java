package org.spring.Team_project_1.cartItemList;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.cart.CartRepository;
import org.spring.Team_project_1.cartItemList.cartItemListServiceImpl.CartItemListServiceImpl;
import org.spring.Team_project_1.entity.CartEntity;
import org.spring.Team_project_1.entity.CartItemListEntity;
import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.member.MemberRepository;
import org.spring.Team_project_1.shop.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartItemListService implements CartItemListServiceImpl {

  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final CartItemListRepository cartItemListRepository;
  private final ShopRepository shopRepository;

  @Override
  public List<CartItemListDto> cartList(Long id) {

    MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    Optional<CartEntity> optionalCartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());

    if (!optionalCartEntity.isPresent()) {
      throw new IllegalArgumentException("xxx");
    }

    List<CartItemListEntity> cartItemListEntityList = cartItemListRepository.findAllByCartEntityId(optionalCartEntity.get().getId());
    List<CartItemListDto> cartItemListDtos = new ArrayList<>();

    cartItemListDtos = cartItemListEntityList.stream().map(item -> CartItemListDto.builder()
            .id(item.getId())
            .count(item.getCount())
            .cartEntity(item.getCartEntity())
            .shopEntity(item.getShopEntity())
            .build()).collect(Collectors.toList());


    return cartItemListDtos;
  }

  @Override
  public CartItemListDto cartSelect(Long id) {


    Optional<CartItemListEntity> cartItemListEntity = cartItemListRepository.findById(id);

    if (cartItemListEntity.isPresent()) {

      CartItemListEntity cartItemList = cartItemListEntity.get();

      CartItemListDto cartItemListDto = CartItemListDto.toSelectCart(cartItemList);

      return cartItemListDto;

    }

    return null;
  }

  @Override
  public int cartDelete(Long id) {


    Optional<CartItemListEntity> cartList = cartItemListRepository.findById(id);
    if (cartList.isPresent()) {
      cartItemListRepository.delete(cartList.get());

      return 1;
    }
    return 0;

  }

  @Override
  public int cartBuy(List<Long> cartIds) {
    int deletedCount = 0;

    for (Long id : cartIds) {
      Optional<CartItemListEntity> cartItemOptional = cartItemListRepository.findById(id);
      if (cartItemOptional.isPresent()) {
        CartItemListEntity cartItem = cartItemOptional.get();
        cartItemListRepository.delete(cartItem);
        deletedCount++;
      } else {
        System.out.println("실패");
      }
    }

    return deletedCount;
  }

  @Override
  public int cartBuys(List<Long> cartIds) {


    for (Long id : cartIds) {
      Optional<CartItemListEntity> cart = cartItemListRepository.findById(id);
      if(cart.isPresent()){
        CartItemListEntity cart1=cart.get();
        cartItemListRepository.delete(cart1);
        return 1;
      }else{
        return 0;
      }
    }

    return 0;
  }

}

