package org.spring.Team_project_1.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.Team_project_1.config.MyUserDetails;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopRepository;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Log4j2
@SessionAttributes("hotList")
public class CartController {

    private final ShopService shopService;
    private final ShopRepository shopRepository;


    @GetMapping("/cart/{id}/{priceCount}")
    public String cart(@PathVariable("id") Long id, @PathVariable("priceCount") int priceCount, ShopDto shopDto, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model,
                       @ModelAttribute("hotList") List<ShopDto> hotList) {

        shopService.addCart(myUserDetails.getMemberEntity().getId(), id, shopDto, priceCount);


        return "redirect:/shop/cartList";


    }


}





