package org.spring.Team_project_1.shop;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.cart.CartRepository;
import org.spring.Team_project_1.cartItemList.CartItemListDto;
import org.spring.Team_project_1.cartItemList.CartItemListRepository;
import org.spring.Team_project_1.entity.*;
import org.spring.Team_project_1.file.FileDto;
import org.spring.Team_project_1.file.FileRepository;
import org.spring.Team_project_1.member.MemberRepository;
import org.spring.Team_project_1.shop.shopServiceImpl.ShopServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService implements ShopServiceImpl {

  private final ShopRepository shopRepository;
  private final FileRepository fileRepository;
  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final CartItemListRepository cartItemListRepository;


  @Override
  public void writeShop(ShopDto shopDto) throws IOException {

    MemberEntity memberEntity = MemberEntity.builder().id(shopDto.getMemberId()).build();

    shopDto.setShopMemberEntity(memberEntity);

    if (shopDto.getShopFile().isEmpty()) {

      ShopEntity shopEntity = ShopEntity.toInsertShopEntity(shopDto);
      shopRepository.save(shopEntity);
    } else {
      MultipartFile shopFile = shopDto.getShopFile();

      String oldFileName = shopFile.getOriginalFilename();
      UUID uuid = UUID.randomUUID();
      String newFileName = uuid + "_" + oldFileName;
      String fileSavePath = "C:/openApi/saves/" + newFileName;
      shopFile.transferTo(new File(fileSavePath));

      ShopEntity shopEntity = ShopEntity.toInsertShopFileEntity(shopDto);
      Long id = shopRepository.save(shopEntity).getId();

      Optional<ShopEntity> optionalShopEntity = shopRepository.findById(id);
      if (optionalShopEntity.isPresent()) {
        ShopEntity shopEntity1 = optionalShopEntity.get();

        FileDto fileDto = FileDto.builder().oldFileName(oldFileName)
                .newFileName(newFileName)
                .shopEntity(shopEntity1)
                .build();

        FileEntity fileEntity = FileEntity.toInsertFile(fileDto);
        fileRepository.save(fileEntity);
      } else {
        throw new IllegalArgumentException("xxxx");
      }
    }
  }

//  @Override
//  public Page<ShopDto> shopList(Pageable pageable) {
//
//    Page<ShopEntity> shopList=shopRepository.findAll(pageable);
//
//    Page<ShopDto> shopDtos= shopList.map(ShopDto::toSelectShopList);
//
//
//    return shopDtos;
//
//  }

  @Override
  public Page<ShopDto> ShopSearchPagingList(Pageable pageable, String subject, String search) {


    Page<ShopEntity> shopEntities = null;


    //************************
    if (search == null || subject == null) {    //************************

      shopEntities = shopRepository.findAll(pageable);

    } else {

      if (subject.equals("title")) {
        shopEntities = shopRepository.findByTitleContains(pageable, search);
      } else if (subject.equals("content")) {
        shopEntities = shopRepository.findByContentContains(pageable, search);
      }  else {
        shopEntities = shopRepository.findAll(pageable);
      }

    }

    Page<ShopDto> shopDtos = shopEntities.map(ShopDto::toSelectShopList);

    return shopDtos;
  }

  @Override
  public ShopDto detail (Long id){

    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(id);

    if (optionalShopEntity.isPresent()) {

      ShopEntity shopEntity = optionalShopEntity.get();

      ShopDto shopDto = ShopDto.toSelectShopList(shopEntity);

      return shopDto;

    }

    return null;
  }

  @Override
  public void updateShop (ShopDto shopDto) throws IOException {

    ShopEntity shopEntity = shopRepository.findById(shopDto.getId()).orElseThrow(() -> {
      throw new IllegalArgumentException("x");
    });
    ///*************
    Optional<FileEntity> optionalFileEntity = fileRepository.findByShopEntityId(shopDto.getId());

    if (optionalFileEntity.isPresent()) {
      String fileNewName = optionalFileEntity.get().getNewFileName();
      String filePath = "C:/openApi/saves/" + fileNewName;
      File deleteFile = new File(filePath);

      if (deleteFile.exists()) {
        deleteFile.delete();

      } else {

      }
      fileRepository.delete(optionalFileEntity.get());
    }


    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(shopDto.getId());
    MemberEntity memberEntity = MemberEntity.builder().id(shopDto.getMemberId()).build();
    shopDto.setShopMemberEntity(memberEntity);

    if (shopDto.getShopFile().isEmpty()) {

      shopEntity = ShopEntity.toUpdateShopEntity(shopDto);
      shopRepository.save(shopEntity);

      MultipartFile shopFile = shopDto.getShopFile();
      String fileOldName = shopFile.getOriginalFilename();
      UUID uuid = UUID.randomUUID();
      String fileNewName = uuid + "_" + fileOldName;

      String savePath = "C:/openApi/saves/" + fileNewName;

      shopFile.transferTo(new File(savePath));


      shopEntity = ShopEntity.toUpdateFileShopEntity(shopDto);
      shopRepository.save(shopEntity);


      FileEntity bFileEntity = FileEntity.builder()
              .shopEntity(shopEntity)
              .newFileName(fileNewName)
              .oldFileName(fileOldName)
              .build();

      Long fileId = fileRepository.save(bFileEntity).getId();

      fileRepository.findById(fileId).orElseThrow(() -> {
        throw new IllegalArgumentException();
      });
    } else if (!shopDto.getShopFile().isEmpty()) {

      shopEntity = ShopEntity.toUpdateShopEntity(shopDto);
      shopRepository.save(shopEntity);

      MultipartFile shopFile = shopDto.getShopFile();
      String fileOldName = shopFile.getOriginalFilename();
      UUID uuid = UUID.randomUUID();
      String fileNewName = uuid + "_" + fileOldName;

      String savePath = "C:/openApi/saves/" + fileNewName;

      shopFile.transferTo(new File(savePath));


      shopEntity = ShopEntity.toUpdateFileShopEntity(shopDto);
      shopRepository.save(shopEntity);


      FileEntity bFileEntity = FileEntity.builder()
              .shopEntity(shopEntity)
              .newFileName(fileNewName)
              .oldFileName(fileOldName)
              .build();

      Long fileId = fileRepository.save(bFileEntity).getId();

      fileRepository.findById(fileId).orElseThrow(() -> {
        throw new IllegalArgumentException();
      });


    }

    shopRepository.findById(shopDto.getId()).orElseThrow(() -> {
      throw new IllegalArgumentException();
    });

  }

  @Override
  public void shopDelete (Long id){

    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(id);

    if (optionalShopEntity.isPresent()) {
      shopRepository.delete(optionalShopEntity.get());
    }

  }

  @Override
  public ShopDto sellPage (Long id){

    Optional<ShopEntity> optionalShopEntity = shopRepository.findById(id);

    if (optionalShopEntity.isPresent()) {

      ShopEntity shopEntity = optionalShopEntity.get();

      ShopDto shopDto = ShopDto.toSelectShopList(shopEntity);

      return shopDto;

    }

    return null;
  }


  @Override
  public void updateHit (Long id){

    shopRepository.updateHit(id);
  }

  public ShopDto findById (Long shopId){

    ShopEntity shopEntity = shopRepository.findById(shopId).orElseThrow(IllegalAccessError::new);

    return ShopDto.builder().id(shopEntity.getId()).build();

  }

  /////////////////////카트//////////////////////////////

  @Override
  public void addCart (Long id, Long shopId,ShopDto shopDto,int priceCount){

    MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    Optional<CartEntity> cartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());
    CartEntity cartEntity1 = null;


    if (!cartEntity.isPresent()) {
      cartEntity1 = CartEntity.builder().memberEntity(memberEntity).build();
      cartRepository.save(cartEntity1);
    } else {
      cartEntity1 = cartRepository.findByMemberEntityId(memberEntity.getId()).orElseThrow(IllegalArgumentException::new);
    }

    ShopEntity shopEntity = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);

    List<CartItemListEntity> cartItemListEntity =
            cartItemListRepository.findByCartEntityIdAndShopEntityId(cartEntity1.getId(), shopEntity.getId());

    if (cartItemListEntity.isEmpty()) {

      ShopEntity shopEntity1 = shopRepository.findById(shopDto.getId()).orElseThrow(() -> {
        throw new IllegalArgumentException("x");
      });

      int oldCount=shopEntity1.getPriceCount();

      CartItemListEntity cartItemListEntity1 = CartItemListEntity.builder()
              .count(priceCount+oldCount)
              .cartEntity(cartEntity1)
              .shopEntity(shopEntity)
              .build();

      cartItemListRepository.save(cartItemListEntity1);
    } else {
      ShopEntity shopEntity1 = shopRepository.findById(shopDto.getId()).orElseThrow(() -> {
        throw new IllegalArgumentException("x");
      });
      int oldCount=shopEntity1.getPriceCount();

      cartItemListRepository.save(CartItemListEntity.builder()
              .id(cartItemListEntity.get(0).getId())
              .count(cartItemListEntity.get(0).getCount() + priceCount+oldCount)
              .cartEntity(cartEntity1)
              .shopEntity(shopEntity)
              .build());
    }
  }




  @Override
  public int good(Long id) {

    shopRepository.good(id);

    ShopEntity shopEntity=shopRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    return ShopDto.builder().good(shopEntity.getGood()).build().getGood();
  }

  @Override
  public int bad(Long id) {
    shopRepository.bad(id);

    ShopEntity shopEntity=shopRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    return ShopDto.builder().bad(shopEntity.getBad()).build().getBad();
  }

  @Override
  public List<ShopDto> shopPhoto() {

    List<ShopEntity> shopEntities=shopRepository.findAll();
    return  shopEntities.stream().map(ShopDto::toSelectShopList).collect(Collectors.toList());
  }

  @Override
  public List<ShopDto> shopList() {

    List<ShopEntity> shopEntities=shopRepository.findAll();

    List<ShopDto> shopDtoList=new ArrayList<>();

    for(ShopEntity shopEntity:shopEntities){
      ShopDto shopDto=ShopDto.toSelectShopList(shopEntity);
      shopDtoList.add(shopDto);
    }
    return shopDtoList;
  }

  @Override
  public List<CartItemListDto> cartList(Long id) {

    MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    Optional<CartEntity> optionalCartEntity = cartRepository.findByMemberEntityId(memberEntity.getId());

    if (!optionalCartEntity.isPresent()) {
      throw new IllegalArgumentException("xxx");
    }

    List<CartItemListEntity> cartItemListEntityList = cartItemListRepository.findAllByCartEntityId(optionalCartEntity.get().getId());
    List<CartItemListDto> cartItemListDtos = new ArrayList<>();

    cartItemListDtos = cartItemListEntityList.stream().map(item -> CartItemListDto.builder()
            .id(item.getId())
            .count(item.getCount())
            .cartEntity(item.getCartEntity())
            .shopEntity(item.getShopEntity())
            .build()).collect(Collectors.toList());


    return cartItemListDtos;
  }

  @Override
  public List<ShopDto> hotList(ShopDto shopDto) {

    List<ShopEntity> hot = shopRepository.findTop3ByOrderByHitDesc();

    List<ShopDto> hotDto = hot.stream().map(
        ShopDto::toSelectShopList).collect(Collectors.toList());

    return hotDto;
  }


//  @Override
//  public List<ShopDto> shopList() {
//
//    List<ShopEntity> shopEntities = shopRepository.findAll();
//    List<ShopDto> shopDto = new ArrayList<>();
//    for (ShopEntity shopEntity : shopEntities) {
//      shopDto.add(ShopDto.toSelectShopList(shopEntity));
//
//
//    }
//    return shopDto;
//  }

  @Override
  public List<ShopDto> shopSearchList(String subjectTwo, String searchTwo) {

    List<ShopEntity> shopEntities = new ArrayList<>();
    List<ShopDto> shopDtos = new ArrayList<>();

    if (searchTwo == null || subjectTwo == null) {    //************************

      shopEntities = shopRepository.findByContentContains(searchTwo);

    } else {
      if (searchTwo.equals("title")) {
        shopEntities = shopRepository.findByTitleContains(searchTwo);

      }else if (subjectTwo.equals("title")) {
        shopEntities = shopRepository.findByContentContains(searchTwo);
      }else if (subjectTwo.equals("content")){
        shopEntities = shopRepository.findByContentContains(searchTwo);

      }else if (subjectTwo.equals("price")){
        shopEntities = shopRepository.findByContentContains(searchTwo);

      } else {
        shopEntities = shopRepository.findByContentContains(searchTwo);
      }
    }
    for (ShopEntity shopEntity : shopEntities) {
      shopDtos.add(ShopDto.toSelectShopList(shopEntity));
    }
    return shopDtos;
  }

  @Override
  public List<ShopDto> shopPlaceAll(String search) {

   List<ShopEntity>  shopEntities= shopRepository.findByPlaceContains(search);

    return shopEntities.stream().map(ShopDto::toSelectShopList).collect(Collectors.toList());
  }

//    @Override
//    public List<ShopDto> shopSearchList(String subjectTwo, String searchTwo) {
//
//            List<ShopEntity> shopEntities = new ArrayList<>();
//            List<ShopDto> shopDtos = new ArrayList<>();
//
//            if (searchTwo == null || subjectTwo == null) {    //************************
//
//                shopEntities = shopRepository.findByContentContains(searchTwo);
//
//            } else {
//                if (searchTwo.equals("title")) {
//                    shopEntities = shopRepository.findByTitleContains(searchTwo);
//
//                }else if (subjectTwo.equals("title")) {
//                    shopEntities = shopRepository.findByContentContains(searchTwo);
//                }else if (subjectTwo.equals("content")){
//                    shopEntities = shopRepository.findByContentContains(searchTwo);
//
//                }else if (subjectTwo.equals("price")){
//                    shopEntities = shopRepository.findByContentContains(searchTwo);
//
//                } else {
//                    shopEntities = shopRepository.findByContentContains(searchTwo);
//                }
//            }
//            for (ShopEntity shopEntity : shopEntities) {
//                shopDtos.add(ShopDto.toSelectShopList(shopEntity));
//            }
//            return shopDtos;
//        }
}
