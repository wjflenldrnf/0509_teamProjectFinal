package org.spring.Team_project_1.entity;

import lombok.*;
import org.spring.Team_project_1.board.BoardDto;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "board_tb1")
public class BoardEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_tb1")
    private MemberEntity memberEntity;

    public static BoardEntity toWriteBoardEntity(BoardDto boardDto) {
    BoardEntity boardEntity=new BoardEntity();
    boardEntity.setId(boardDto.getId());
    boardEntity.setTitle(boardDto.getTitle());
    boardEntity.setContent(boardDto.getContent());
    boardEntity.setWriter(boardDto.getWriter());
    return boardEntity;





    }

    public static BoardEntity toUpdateEntity(BoardDto boardDto) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setWriter(boardDto.getWriter());
        return boardEntity;
    }
}
