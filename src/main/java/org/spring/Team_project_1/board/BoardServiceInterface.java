
package org.spring.Team_project_1.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardServiceInterface {
    public int write(BoardDto boardDto);

    Page<BoardDto> boardSearchPagingList(Pageable pageable, String subject, String search);

    Page<BoardDto> pagingList(Pageable pageable);

    void update(BoardDto boardDto);

    public int deleteBoard(Long id);

    BoardDto boardOne(Long id);
}

