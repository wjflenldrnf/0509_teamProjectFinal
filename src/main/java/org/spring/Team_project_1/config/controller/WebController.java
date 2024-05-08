package org.spring.Team_project_1.config.controller;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.config.MyUserDetails;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class WebController {

    private final ShopService shopService;

    @GetMapping("/kakaoMap")
    public String join(ShopDto shopDto, Model model) {

        model.addAttribute("shopDto", shopDto);

        return "map/kakaoMap";
    }

    @GetMapping("/sell/{id}")
    public String sell(@PathVariable("id") Long id,
                       @AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {

        ShopDto shop = shopService.detail(id);

        model.addAttribute("id", id);
        model.addAttribute("shop", shop);
        model.addAttribute("myUserDetails", myUserDetails);

        return "map/sell";
    }

}
