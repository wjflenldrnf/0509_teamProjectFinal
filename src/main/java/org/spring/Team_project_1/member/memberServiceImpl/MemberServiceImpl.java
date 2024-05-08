package org.spring.Team_project_1.member.memberServiceImpl;

import org.spring.Team_project_1.member.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberServiceImpl {
    void insertMember(MemberDto memberDto);

    Page<MemberDto> memberSearchPagingList(Pageable pageable, String subject, String search);

    MemberDto selectOne(Long id);

    int memberUpdate(MemberDto memberDto);

    void memberDelete(Long id);


    int emailChecked(String email);

    void insertSeller(MemberDto memberDto);
}
