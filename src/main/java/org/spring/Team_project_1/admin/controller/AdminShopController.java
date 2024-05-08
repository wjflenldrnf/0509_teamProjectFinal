package org.spring.Team_project_1.admin.controller;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopService;
import org.spring.Team_project_1.shopReply.ShopReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin/shop")
public class AdminShopController {

  private final ShopService shopService;

  private final ShopReplyService shopReplyService;


  @GetMapping("/shopList")
  public String shopList(@PageableDefault(page=0,size = 8, sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam(name = "subject", required = false) String subject,
                         @RequestParam(name = "search", required = false)
                         String search, Model model) {


    Page<ShopDto> pageList = shopService.ShopSearchPagingList(pageable,subject,search);

    int totalPages = pageList.getTotalPages();// 전체 페이지
    int newPage = pageList.getNumber(); // 현재 페이지
    long  totalElements = pageList.getNumberOfElements(); // 전체 레코드 갯수

    int size = pageList.getSize(); // 페이지당 보이는 갯수

    int blockNum = 3; // 브라우저에 보이는 페이지 번호

    int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
        ? (Math.floor(newPage / blockNum)* blockNum ) + 1
        : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) -1 : totalPages;

    model.addAttribute("startPage",startPage);

    model.addAttribute("endPage",endPage);

    model.addAttribute("pagingList",pageList);


    return "admin/shop/shopList";


  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {

    ShopDto shop = shopService.detail(id);

    model.addAttribute("shop",shop);

    return "admin/shop/shopDetail";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute ShopDto shopDto) throws IOException {

    shopService.updateShop(shopDto);

    return "redirect:/admin/shop/shopDetail" + shopDto.getId();

  }



  @ResponseBody
  @PostMapping("/delete1{id}")
    public String shopDelete(@PathVariable("id")Long id) {

    shopService.shopDelete(id);

    String html = "<script>" +
        "alert('글 삭제 되었습니다');" +
        "location.href='/shop/shopList';"+
        "</script>";

    return html;

  }


//  @GetMapping("/ajaxShop")
//  public String ajaxShop(Model model) {
//    List<ShopDto> shopDtoList = shopService.selectAll();
//    model.addAttribute("shopList", shopDtoList);
//
//    return "admin/shopList";
//
//  }


}
