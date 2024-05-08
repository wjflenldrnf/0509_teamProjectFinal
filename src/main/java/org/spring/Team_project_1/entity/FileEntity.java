package org.spring.Team_project_1.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.Team_project_1.file.FileDto;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "file_tb1")
public class FileEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private String newFileName;

    @Column(nullable = false)
    private String oldFileName; // 원본파일


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private MemberEntity fileMemberEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public static FileEntity toInsertFile(FileDto fileDto) {
        FileEntity fileEntity=new FileEntity();
        fileEntity.setNewFileName(fileDto.getNewFileName());
        fileEntity.setOldFileName(fileDto.getOldFileName());
        fileEntity.setShopEntity(fileDto.getShopEntity());

        return fileEntity;
    }



}
