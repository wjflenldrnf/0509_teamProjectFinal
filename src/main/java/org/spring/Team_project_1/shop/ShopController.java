package org.spring.Team_project_1.shop;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.cartItemList.CartItemListService;
import org.spring.Team_project_1.config.MyUserDetails;
import org.spring.Team_project_1.member.MemberDto;
import org.spring.Team_project_1.shopReply.ShopReplyDto;
import org.spring.Team_project_1.shopReply.ShopReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Transactional
@RequestMapping("/shop")
@SessionAttributes("hotList")
public class ShopController {


    private final ShopService shopService;
    private final ShopReplyService shopReplyService;
    private final CartItemListService cartItemListService;


    @GetMapping("/write")
    public String shopWrite(ShopDto shopDto, Model model, @AuthenticationPrincipal MyUserDetails myUserDetails
            , @ModelAttribute("hotList") List<ShopDto> hotList) {


        model.addAttribute("memberId", myUserDetails.getMemberEntity().getId());

        return "shop/write";
    }

    @PostMapping("/write")
    public String shopWriteOk(@Valid ShopDto shopDto, BindingResult bindingResult, MemberDto memberDto, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            return "shop/write";
        }
        shopService.writeShop(shopDto);

        model.addAttribute("memberDto", memberDto);

        return "redirect:/shop/shopList";

    }

    @GetMapping("/shopList")
    public String shopList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false)
                           String search, Model model,
                           @ModelAttribute("hotList") List<ShopDto> hotList) {


        Page<ShopDto> pageList = shopService.ShopSearchPagingList(pageable, subject, search);

        int totalPages = pageList.getTotalPages();// 전체 페이지
        int newPage = pageList.getNumber(); // 현재 페이지
        long totalElements = pageList.getNumberOfElements(); // 전체 레코드 갯수

        int size = pageList.getSize(); // 페이지당 보이는 갯수

        int blockNum = 3; // 브라우저에 보이는 페이지 번호

        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
                ? (Math.floor(newPage / blockNum) * blockNum) + 1
                : totalPages
        );

        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        model.addAttribute("startPage", startPage);

        model.addAttribute("endPage", endPage);

        model.addAttribute("pagingList", pageList);


        return "shop/shopList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model,
            @ModelAttribute("hotList") List<ShopDto> hotList) {

        ShopDto shop = shopService.detail(id);

        model.addAttribute("shop", shop);
        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("nickName", myUserDetails.getNickName());

        return "shop/detail";

    }

    @PostMapping("/update")
    public String shopUpdateGo(@ModelAttribute ShopDto shopDto) throws IOException {

        shopService.updateShop(shopDto);


        return "redirect:/shop/detail/" + shopDto.getId();
    }

    @ResponseBody
    @GetMapping("/delete/{id}")
    public String shopDelete(@PathVariable("id") Long id,
                             @ModelAttribute("hotList") List<ShopDto> hotList) {


        shopService.shopDelete(id);

        String html = "<script>" +
                "alert('글삭제되었습니다');" +
                "location.href='/shop/shopList';" +
                "</script>";

        return html;
    }

    @GetMapping("/sell/{id}")
    public String sell(MemberDto memberDto, @PathVariable("id") Long id, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model,
                       @ModelAttribute("hotList") List<ShopDto> hotList) {

        shopService.updateHit(id);

        ShopDto shop = shopService.sellPage(id);

        List<ShopReplyDto> replyList = shopReplyService.ajaxReplyList(id);

        model.addAttribute("shopReplyList", replyList);
        model.addAttribute("myUserDetails", myUserDetails);
        model.addAttribute("shop", shop);


        return "/shop/sell";
    }

    @GetMapping("/sell/{id}/good")
    public ResponseEntity<?> good(@PathVariable("id") Long id) {

        int count = shopService.good(id);

        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @GetMapping("/sell/{id}/bad")
    public ResponseEntity<?> bad(@PathVariable("id") Long id) {

        int count = shopService.bad(id);

//    return "/shop/{id}/good";
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }


    // @GetMapping("/pathvars/item/{itemId}/detail/{dtlId}")
    // @GetMapping("/shopList2/{search}")


    @GetMapping("/shopList2")
    public String shopList2(@RequestParam(name = "search", required = false)
                            String search, Model model,
                            @ModelAttribute("hotList") List<ShopDto> hotList) {


        List<ShopDto> shopList = shopService.shopPlaceAll(search);

        model.addAttribute("shopList", shopList);


        return "index";
    }


}

