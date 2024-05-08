package org.spring.Team_project_1.shopReply;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.entity.ShopEntity;
import org.spring.Team_project_1.entity.ShopReplyEntity;
import org.spring.Team_project_1.shop.ShopRepository;
import org.spring.Team_project_1.shopReply.shopReplySerivceImpl.ShopReplyServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopReplyService implements ShopReplyServiceImpl {

    private final ShopRepository shopRepository;
    private final ShopReplyRepository shopReplyRepository;


    @Override
    public ShopReplyDto ajaxInsert(ShopReplyDto replyDto) {
        ShopEntity shopEntity = shopRepository.findById(replyDto.getShopId()).orElseThrow(() -> {
            throw new IllegalArgumentException("실패");
        });

        // 2. 덧글 작성
        replyDto.setShopEntity(ShopEntity.builder().id(replyDto.getShopId()).build());

        ShopReplyEntity replyEntity = ShopReplyEntity.toInsertReplyEntity(replyDto);

        Long id = shopReplyRepository.save(replyEntity).getId();


        ShopReplyEntity replyEntity1 = shopReplyRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("실패");
        });

        return ShopReplyDto.toAjaxReplyDto(replyEntity1);
    }

    @Override
    public List<ShopReplyDto> ajaxReplyList(Long id) {

        ShopEntity shopEntity = shopRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("없ㅂ스니다");
        });

//    List<ShopReplyEntity> replyEntities = shopReplyRepository.replyJoinShop(shopEntity.getId());
        List<ShopReplyEntity> replyEntities = shopReplyRepository.findAllByShopEntity(shopEntity);
        List<ShopReplyDto> replyDtos = new ArrayList<>();


        replyDtos=replyEntities.stream()
                .map(ShopReplyDto::toSelectReplyDto)
                .collect(Collectors.toList());

//    for (ShopReplyEntity replyEntity : replyEntities) {
//      ShopReplyDto replyDto = ShopReplyDto.toSelectReplyDto(replyEntity);
//      replyDtos.add(replyDto);
//    }
        return replyDtos;

    }

    @Override
    public List<ShopReplyDto> replyList(Long id) {
        ShopEntity shopEntity = shopRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("대실패");
        });


        List<ShopReplyEntity> replyEntities = shopReplyRepository.findAllByShopEntity(shopEntity);


        List<ShopReplyDto> replyDtos = new ArrayList<>();

        replyDtos = replyEntities.stream()
                .map(ShopReplyDto::toSelectReplyDto)
                .collect(Collectors.toList());

//    for(ShopReplyEntity replyEntity: replyEntities){
//      ShopReplyDto replyDto= ShopReplyDto.toSelectReplyDto(replyEntity);
//      replyDtos.add(replyDto);
//    }


        return replyDtos;
    }


    @Override
    public int replyDelete(Long id) {

        Optional<ShopReplyEntity> optionalReplyEntity = shopReplyRepository.findById(id);
        if (optionalReplyEntity.isPresent()) {
            shopReplyRepository.delete(optionalReplyEntity.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int replyGood(Long id) {
        shopReplyRepository.replyGood(id);

        ShopReplyEntity shopReplyEntity=shopReplyRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return ShopReplyDto.builder().good(shopReplyEntity.getGood()).build().getGood();
    }

    @Override
    public int replyBad(Long id) {
        shopReplyRepository.replyBad(id);

        ShopReplyEntity shopReplyEntity=shopReplyRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return ShopReplyDto.builder().good(shopReplyEntity.getBad()).build().getBad();
    }

}
