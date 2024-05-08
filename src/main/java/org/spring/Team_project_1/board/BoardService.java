
package org.spring.Team_project_1.board;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService implements BoardServiceInterface{

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public int write(BoardDto boardDto) {
        BoardEntity boardEntity=BoardEntity.toWriteBoardEntity(boardDto);
        Long id=boardRepository.save(boardEntity).getId();
        if(id!=null){
            System.out.println("성공");
            return 1;
        }
        System.out.println("실패");
        return 0;
    }

    @Override
    public Page<BoardDto> boardSearchPagingList(Pageable pageable, String subject, String search) {
        Page<BoardEntity> boardEntities = null;
        if(subject==null || search==null){
            boardEntities = boardRepository.findAll(pageable);
        } else{
            if (subject.equals("title")) {
                boardEntities = boardRepository.findByTitleContaining(pageable, search);
            } else if (subject.equals("content")) {
                boardEntities = boardRepository.findByContentContaining(pageable, search);
            } else if (subject.equals("writer")) {
                boardEntities = boardRepository.findByWriterContaining(pageable, search);
            } else {
                boardEntities = boardRepository.findAll(pageable);
            }
        }                            // paging -> map(DTO::DTO(매서드) -> Entity-> DTO
        Page<BoardDto> boardDtos = boardEntities.map(BoardDto::toSelectBoardDto);

        return boardDtos;
    }

    @Override
    public Page<BoardDto> pagingList(Pageable pageable) {
        Page<BoardEntity> pagingEntitys= boardRepository.findAll(pageable);
        //paging처리
        // -> 페이징 객체 하나 BoardEntity -> BoardDto의 toSelectBoardDto매서드에 추가
        Page<BoardDto> boardDtos=pagingEntitys.map(BoardDto::toSelectBoardDto);
//    pagingEntitys.get().forEach(boardEntity -> {
//      BoardDto.toSelectBoardDto(boardEntity);
//    });
        return boardDtos;
    }

    @Override
    public void update(BoardDto boardDto) {
        BoardEntity boardEntity =BoardEntity.toUpdateEntity(boardDto);

        Long id=  boardRepository.save(boardEntity).getId();

        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            System.out.println("회원수정 성공");
            return;
        }

        System.out.println("회원수정 실패");
        throw  new IllegalArgumentException("조회할 아이디가 없습니다");
    }

    @Transactional
    @Override
    public int deleteBoard(Long id) {
        boardRepository.deleteById(id);

        return 0;
    }

    @Override
    public BoardDto boardOne(Long id) {
        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity=optionalBoardEntity.get();
            BoardDto boardDto=BoardDto.toSelectBoardDto(boardEntity);
            return boardDto;
        }
        return null;
    }

}
