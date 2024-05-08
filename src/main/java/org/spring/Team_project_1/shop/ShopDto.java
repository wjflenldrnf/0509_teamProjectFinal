package org.spring.Team_project_1.shop;

import lombok.*;
import org.spring.Team_project_1.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDto {


    private Long id;

    @NotBlank(message = "상품명을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    //  @NotBlank(message = "가격을 정해주세요")
    private int price;

    private int hit;

    private int isImage;

    private MultipartFile shopFile;

    private String oldFileName;

    private String newFileName;

    private String address;

    private int good;

    private int bad;

    private int priceCount;

    private String place;

    // MemberEntity
    private MemberEntity shopMemberEntity;

    // ShopReplyEntity
    private List<ShopReplyEntity> shopReplyEntities;

    private List<FileEntity> fileEntities;

    private Long memberId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    private List<CartItemListEntity> cartItemListEntityList;

    public static ShopDto toSelectShopList(ShopEntity shopEntity) {
        ShopDto shopDto = new ShopDto();

        shopDto.setId(shopEntity.getId());
        shopDto.setTitle(shopEntity.getTitle());
        shopDto.setContent(shopEntity.getContent());
        shopDto.setPrice(shopEntity.getPrice());
        shopDto.setHit(shopEntity.getHit());
        shopDto.setGood(shopEntity.getGood());
        shopDto.setBad(shopEntity.getBad());
        shopDto.setShopMemberEntity(shopEntity.getShopMemberEntity());
        shopDto.setCreateTime(shopEntity.getCreateTime());
        shopDto.setUpdateTime(shopEntity.getUpdateTime());
        shopDto.setAddress(shopEntity.getAddress());

        shopDto.setPriceCount(shopEntity.getPriceCount());
        shopDto.setPlace(shopEntity.getPlace());

        if (shopEntity.getIsImage() == 0) {
            shopDto.setIsImage(shopEntity.getIsImage());
        } else {
            shopDto.setIsImage(shopEntity.getIsImage());
            shopDto.setNewFileName(shopEntity.getFileEntities().get(0).getNewFileName());
            shopDto.setOldFileName(shopEntity.getFileEntities().get(0).getOldFileName());
        }
        return shopDto;
    }


}
