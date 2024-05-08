package org.spring.Team_project_1.shopReply.shopReplySerivceImpl;


import org.spring.Team_project_1.shopReply.ShopReplyDto;

import java.util.List;

public interface ShopReplyServiceImpl {

  ShopReplyDto ajaxInsert(ShopReplyDto replyDto);

  List<ShopReplyDto> ajaxReplyList(Long id);

  List<ShopReplyDto> replyList(Long id);

  int replyDelete(Long id);

  int replyGood(Long id);

  int replyBad(Long id);
}
