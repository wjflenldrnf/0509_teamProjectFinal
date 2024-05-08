package org.spring.Team_project_1.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.Team_project_1.role.Role;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member_tb1")
public class MemberEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String note;

    @Column(nullable = false)
    private int number;

    // ShopEntity 1:N
    @JsonIgnore
    @OneToMany(mappedBy = "shopMemberEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ShopEntity> shopEntities;

//    // FileEntity 1:N
//    @OneToMany(mappedBy = "fileMemberEntity",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.REMOVE)
//    private List<FileEntity> fileEntities;

}
