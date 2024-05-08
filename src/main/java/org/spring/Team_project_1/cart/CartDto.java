package org.spring.Team_project_1.cart;

import lombok.*;
import org.spring.Team_project_1.entity.CartItemListEntity;
import org.spring.Team_project_1.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

  private Long id;

  private MemberEntity memberEntity;

  private List<CartItemListEntity> cartItemListEntityList;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

}
