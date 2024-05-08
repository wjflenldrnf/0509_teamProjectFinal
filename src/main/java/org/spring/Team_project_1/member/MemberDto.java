package org.spring.Team_project_1.member;

import lombok.*;
import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.role.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    @Size(min = 1, max = 255)
    private String email;

    @NotBlank(message = "userPw 입력해주세요")
    private String userPw;


    private Role role;

    @NotBlank(message = "name 입력해주세요")
    private String name;

    @NotBlank(message = "nickName 입력해주세요")
    private String nickName;

    @NotBlank(message = "gender 입력해주세요")
    private String gender;

    @NotBlank(message = "address 입력해주세요")
    private String address;


    private String note;

    //    @NotBlank(message = "number 입력해주세요")
    private int number;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public static MemberDto toMemberDto(MemberEntity memberEntity) {

        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setUserPw(memberEntity.getUserPw());
        memberDto.setRole(memberEntity.getRole());
        memberDto.setName(memberEntity.getName());
        memberDto.setNickName(memberEntity.getNickName());
        memberDto.setGender(memberEntity.getGender());
        memberDto.setAddress(memberEntity.getAddress());
        memberDto.setNote(memberEntity.getNote());
        memberDto.setNumber(memberEntity.getNumber());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());


        return memberDto;


    }
}
