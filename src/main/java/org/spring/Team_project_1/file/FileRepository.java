package org.spring.Team_project_1.file;

import org.spring.Team_project_1.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

  Optional<FileEntity> findByShopEntityId(Long id);


}
