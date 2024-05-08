package org.spring.Team_project_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.Team_project_1.shop.ShopDto;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shop_tb1")
public class ShopEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int hit;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int isImage;

    @Column(nullable = false)
    private int good;

    @Column(nullable = false)
    private int bad;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int priceCount;

    @Column(nullable = false)
    private String place;

    // MemberEntity
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity shopMemberEntity;

    // ShopReplyEntity
    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ShopReplyEntity> shopReplyEntities;


    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<FileEntity> fileEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "shopEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<CartItemListEntity> cartItemListEntityList;

    public static ShopEntity toInsertShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();

        shopEntity.setId(shopDto.getId());
        shopEntity.setTitle(shopDto.getTitle());
        shopEntity.setContent(shopDto.getContent());
        shopEntity.setAddress(shopDto.getAddress());
        shopEntity.setHit(0);
        shopEntity.setPrice(shopDto.getPrice());
        shopEntity.setIsImage(0);
        shopEntity.setGood(0);
        shopEntity.setBad(0);
        shopEntity.setShopMemberEntity(shopDto.getShopMemberEntity());
        shopEntity.setPriceCount(0);
        shopEntity.setPlace(shopDto.getPlace());

        return shopEntity;
    }

    public static ShopEntity toInsertShopFileEntity(ShopDto shopDto) {

        ShopEntity shopEntity=new ShopEntity();

        shopEntity.setId(shopDto.getId());
        shopEntity.setTitle(shopDto.getTitle());
        shopEntity.setContent(shopDto.getContent());
        shopEntity.setAddress(shopDto.getAddress());
        shopEntity.setHit(0);
        shopEntity.setPrice(shopDto.getPrice());
        shopEntity.setIsImage(1);
        shopEntity.setGood(0);
        shopEntity.setBad(0);
        shopEntity.setShopMemberEntity(shopDto.getShopMemberEntity());
        shopEntity.setPriceCount(0);
        shopEntity.setPlace(shopDto.getPlace());

        return shopEntity;
    }

    public static ShopEntity toUpdateShopEntity(ShopDto shopDto) {

        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopMemberEntity(shopDto.getShopMemberEntity());
        shopEntity.setTitle(shopDto.getTitle());
        shopEntity.setAddress(shopDto.getAddress());
        shopEntity.setContent(shopDto.getContent());
        shopEntity.setPrice(shopDto.getPrice());
        shopEntity.setHit(shopDto.getHit());
        shopEntity.setIsImage(0);
        shopEntity.setPlace(shopDto.getPlace());

        return shopEntity;
    }

    public static ShopEntity toUpdateFileShopEntity(ShopDto shopDto) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setId(shopDto.getId());
        shopEntity.setShopMemberEntity(shopDto.getShopMemberEntity());
        shopEntity.setAddress(shopDto.getAddress());
        shopEntity.setTitle(shopDto.getTitle());
        shopEntity.setContent(shopDto.getContent());
        shopEntity.setHit(shopDto.getHit());
        shopEntity.setPrice(shopDto.getPrice());
        shopEntity.setIsImage(1);
        shopEntity.setPlace(shopDto.getPlace());

        return shopEntity;
    }



}
