package org.spring.Team_project_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.Team_project_1.shopReply.ShopReplyDto;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shop_reply_tb1")
public class ShopReplyEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_reply_id")
    private Long id;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private int good;

    @Column(nullable = true)
    private int bad;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public static ShopReplyEntity toInsertReplyEntity(ShopReplyDto shopReplyDto) {
        ShopReplyEntity replyEntity=new ShopReplyEntity();

//        replyEntity.setId(shopReplyDto.getId());
        replyEntity.setShopEntity(shopReplyDto.getShopEntity());
//        replyEntity.setNick(shopReplyDto.getShopEntity().getShopMemberEntity().getNickName());
        replyEntity.setNick(shopReplyDto.getNick());
        replyEntity.setContent(shopReplyDto.getContent());
        replyEntity.setGood(0);
        replyEntity.setBad(0);


        return replyEntity;

    }


}
