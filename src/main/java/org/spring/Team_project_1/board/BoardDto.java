package org.spring.Team_project_1.board;

import lombok.*;
import org.spring.Team_project_1.entity.BoardEntity;
import org.spring.Team_project_1.entity.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long id;


    private String title;

    private String content;


    private String writer;


    private MemberEntity memberEntity;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;


    public static BoardDto toSelectBoardDto(BoardEntity boardEntity) {

        BoardDto boardDto=new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setWriter(boardEntity.getWriter());
        boardDto.setMemberEntity(boardEntity.getMemberEntity());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());

        return boardDto;





    }
}
