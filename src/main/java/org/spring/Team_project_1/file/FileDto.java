package org.spring.Team_project_1.file;

import lombok.*;
import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.entity.ShopEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class FileDto {


  private Long id;

  private String newFileName;

  private String oldFileName; // 원본파일

  private MemberEntity fileMemberEntity;

  private ShopEntity shopEntity;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

}
