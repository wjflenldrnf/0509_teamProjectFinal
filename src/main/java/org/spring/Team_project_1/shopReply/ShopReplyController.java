package org.spring.Team_project_1.shopReply;


import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/reply")
public class ShopReplyController {

  private final ShopReplyService shopReplyService;
  private final ShopService shopService;


  @ResponseBody
  @PostMapping("/replyWrite")
  public ResponseEntity<?> reply(ShopReplyDto replyDto){
    ShopReplyDto reply=shopReplyService.ajaxInsert(replyDto);
    return ResponseEntity.status(HttpStatus.OK).body(reply);
  }

  @ResponseBody
  @GetMapping("/replyList/{id}")
  public ResponseEntity<?>  replyList(@PathVariable("id")Long id) {
    List<ShopReplyDto> replyList = shopReplyService.ajaxReplyList(id);

    return ResponseEntity.status(HttpStatus.OK).body(replyList);

  }

  @ResponseBody
  @PostMapping("/replyDelete/{id}/shop/{shopId}")
  public ResponseEntity<?> replyDelete(@PathVariable("id")Long id, @PathVariable("shopId")Long shopId) {

    //1. 게시글 있는지 확인


    ShopDto shopDto=shopService.findById(shopId);

    int result= shopReplyService.replyDelete(id);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }


  @GetMapping("/{id}/good")
  public ResponseEntity<?> replyGood(@PathVariable("id")Long id){

    int count= shopReplyService.replyGood(id);

    return ResponseEntity.status(HttpStatus.OK).body(count);
  }

  @GetMapping("/{id}/bad")
  public ResponseEntity<?> replyBad(@PathVariable("id")Long id){

    int count= shopReplyService.replyBad(id);

    return ResponseEntity.status(HttpStatus.OK).body(count);
  }





}
