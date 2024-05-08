package org.spring.Team_project_1.admin.controller;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.member.MemberDto;
import org.spring.Team_project_1.member.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

  private final MemberService memberService;
  @GetMapping("/memberList")
  public String memberList(@PageableDefault(page=0,size = 8, sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(name = "subject", required = false) String subject,
                           @RequestParam(name = "search", required = false)
                           String search, Model model) {


    Page<MemberDto> pageList = memberService.memberSearchPagingList(pageable,subject,search);

    int totalPages = pageList.getTotalPages();// 전체 페이지
    int newPage = pageList.getNumber(); // 현재 페이지
    long  totalElements = pageList.getNumberOfElements(); // 전체 레코드 갯수

    int size = pageList.getSize(); // 페이지당 보이는 갯수

    int blockNum = 3; // 브라우저에 보이는 페이지 번호

    int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
        ? (Math.floor(newPage / blockNum)* blockNum ) + 1
        : totalPages
    );

    int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) -1 : totalPages;

    model.addAttribute("startPage",startPage);

    model.addAttribute("endPage",endPage);

    model.addAttribute("pagingList",pageList);


    return "admin/member/memberList";



  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {

    MemberDto member = memberService.selectOne(id);

    model.addAttribute("member",member);

    return "admin/member/memberDetail";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute MemberDto memberDto){

    int rs =memberService.memberUpdate(memberDto);

    if (rs!=1) {

      return "redirect:/admin/member/adminList";
    }else {

      return "redirect:/admin/member/detail" + memberDto.getId();
    }
  }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {

    memberService.memberDelete(id);

    String html = "<script>" +
        "alert('회원삭제 성공');"+
        "location.href='/member/';"+
        "</script>";

    return html;
    }















}
