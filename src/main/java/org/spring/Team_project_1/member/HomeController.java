package org.spring.Team_project_1.member;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.List;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("hotList")
public class HomeController {
    private final ShopService shopService;

    @GetMapping({"/", "/index"})
    public String shopList(ShopDto shopDto, Model model, HttpSession session) {


        List<ShopDto> hotList = shopService.hotList(shopDto);
        model.addAttribute("hotList", hotList); // 모델에도 추가

        List<ShopDto> shopDtoList = shopService.shopPhoto();
        model.addAttribute("shopList", shopDtoList);

/*        List<ShopDto> hotList = shopService.hotList(shopDto);
        session.setAttribute("hotList", hotList);
        model.addAttribute("hotList", hotList);

        List<ShopDto> shopDtoList = shopService.shopPhoto();
        model.addAttribute("shopList", shopDtoList);*/

        return "index";
    }


    // 상품 리스트 (검색)
    @GetMapping("/index/shopList")
    public String shopList(@RequestParam(name = "subjectTwo", required = false) String subjectTwo,
                           @RequestParam(name = "searchTwo", required = false) String searchTwo,
                           Model model) {
        List<ShopDto> shopDtoList = new ArrayList<>();

        if (subjectTwo == null && searchTwo == null) {
            shopDtoList = shopService.shopList();


        } else {
            shopDtoList = shopService.shopSearchList(subjectTwo, searchTwo);
        }

        model.addAttribute("shopList", shopDtoList);
        return "index";


    }


}
