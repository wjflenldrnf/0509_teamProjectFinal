package org.spring.Team_project_1.admin.service.serviceImpl;

import org.spring.Team_project_1.member.MemberDto;
import org.spring.Team_project_1.shop.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminServiceImpl {



  Page<MemberDto> memberSearchPagingList(Pageable pageable, String subject, String search);

  Page<ShopDto> ShopSearchPagingList(Pageable pageable, String subject, String search);

  void memberDelete(Long id);
}
