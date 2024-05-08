package org.spring.Team_project_1.board;

import org.spring.Team_project_1.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    Page<BoardEntity> findByTitleContaining(Pageable pageable, String search);

    Page<BoardEntity> findByContentContaining(Pageable pageable, String search);

    Page<BoardEntity> findByWriterContaining(Pageable pageable, String search);
}
