package org.spring.Team_project_1.cartItemList;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.cart.CartRepository;
import org.spring.Team_project_1.cart.CartService;
import org.spring.Team_project_1.config.MyUserDetails;
import org.spring.Team_project_1.member.MemberRepository;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopRepository;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Transactional
@SessionAttributes("hotList")
public class CartItemListController {

    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;
    private final ShopService shopService;
    private final CartItemListService cartItemListService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemListRepository cartItemListRepository;


    @GetMapping("/cartList")
    public String cartList(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails,
                           @ModelAttribute("hotList") List<ShopDto> hotList) {

        List<CartItemListDto> shopList = shopService.cartList(myUserDetails.getMemberEntity().getId());
        model.addAttribute("shopList", shopList);
        model.addAttribute("myUserDetails", myUserDetails);


        return "shop/cart";
    }

    @ResponseBody
    @PostMapping("/cartDelete/{id}")
    public ResponseEntity<?> cartDelete(@PathVariable("id") Long id) {

//    cartService.cartDelete(id2);

        int result = cartItemListService.cartDelete(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/cartBuyCon")
    public String cartBuyCon(Model model,
                             @ModelAttribute("hotList") List<ShopDto> hotList) {


        List<ShopDto> shop = shopService.shopList();

        model.addAttribute("shopList", shop);

        return "/shop/cartBuyCon";
    }

    @ResponseBody
    @PostMapping("/cartBuy/{id}")
    public ResponseEntity<?> cartBuy(@PathVariable("id") Long id) {

        int result = cartItemListService.cartDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
