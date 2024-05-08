package org.spring.Team_project_1.shopReply;

import lombok.*;
import org.spring.Team_project_1.entity.ShopEntity;
import org.spring.Team_project_1.entity.ShopReplyEntity;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopReplyDto {


  private Long id;

  private String nick;

  private String content;

  private int good;

  private int bad;

  private Long shopId;

  private ShopEntity shopEntity;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  public static ShopReplyDto toAjaxReplyDto(ShopReplyEntity shopReplyEntity) {
    ShopReplyDto shopReplyDto=new ShopReplyDto();

    shopReplyDto.setId(shopReplyEntity.getId());
    shopReplyDto.setNick(shopReplyEntity.getNick());
    shopReplyDto.setContent(shopReplyEntity.getContent());
    shopReplyDto.setGood(0);
    shopReplyDto.setBad(0);
    shopReplyDto.setShopEntity(shopReplyEntity.getShopEntity());
    shopReplyDto.setCreateTime(shopReplyEntity.getCreateTime());
    shopReplyDto.setUpdateTime(shopReplyEntity.getUpdateTime());

    return shopReplyDto;
  }

  public static ShopReplyDto toSelectReplyDto(ShopReplyEntity shopReplyEntity) {

    ShopReplyDto shopReplyDto=new ShopReplyDto();

    shopReplyDto.setId(shopReplyEntity.getId());
    shopReplyDto.setNick(shopReplyEntity.getNick());
    shopReplyDto.setContent(shopReplyEntity.getContent());
    shopReplyDto.setGood(shopReplyEntity.getGood());
    shopReplyDto.setBad(shopReplyEntity.getBad());
    shopReplyDto.setShopEntity(shopReplyEntity.getShopEntity());
    shopReplyDto.setCreateTime(shopReplyEntity.getCreateTime());
    shopReplyDto.setUpdateTime(shopReplyEntity.getUpdateTime());

    return shopReplyDto;




  }
}
