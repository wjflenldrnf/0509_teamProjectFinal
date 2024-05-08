package org.spring.Team_project_1.member;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.config.MyUserDetails;
import org.spring.Team_project_1.shop.ShopDto;
import org.spring.Team_project_1.shop.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
@SessionAttributes("hotList")
public class MemberController {

    private final MemberService memberService;
    private final ShopService shopService;

    @GetMapping({"", "/", "/index"})
    public String index(@ModelAttribute("hotList") List<ShopDto> hotList) {


        return "member/index";
    }


    @GetMapping("/join")
    public String join(MemberDto memberDto,
                       ShopDto shopDto,
                       HttpSession session,
                       Model model) {

        List<ShopDto> hotList = shopService.hotList(shopDto);
        model.addAttribute("hotList", hotList); // 모델에도 추가

        List<ShopDto> shopDtoList = shopService.shopPhoto();
        model.addAttribute("shopList", shopDtoList);


        return "member/join";
    }

    @GetMapping("/joinSeller")
    public String joinSeller(MemberDto memberDto,
                             ShopDto shopDto,
                             Model model) {
        List<ShopDto> hotList = shopService.hotList(shopDto);
        model.addAttribute("hotList", hotList); // 모델에도 추가

        List<ShopDto> shopDtoList = shopService.shopPhoto();
        model.addAttribute("shopList", shopDtoList);

        return "member/joinSeller";
    }

    @PostMapping("/join")
    public String joinOk(@Valid MemberDto memberDto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        memberService.insertMember(memberDto);
        return "redirect:/index";
    }

    @PostMapping("/joinSeller")
    public String joinSellerOk(@Valid MemberDto memberDto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/joinSeller";
        }

        memberService.insertSeller(memberDto);
        return "redirect:/index";
    }

    /////////////////////////////////////////////////////////////////////////////
    @GetMapping("/join/emailChecked")
    @ResponseBody
    public int emailChecked (@RequestParam("email") String email){
        int result=memberService.emailChecked(email);

        return result;
    }


    /////////////////////////////////////////////////////////////////////////////

    @GetMapping("/login")
    public String login(
            // 에러안나면 null값 날아감. null날아가도 실행되도록 required = false
            @RequestParam( value="error", required = false  )String error,
            @RequestParam( value = "exception", required = false )String exception,
            ShopDto shopDto,
            Model model
    ) {


        List<ShopDto> hotList = shopService.hotList(shopDto);
        model.addAttribute("hotList", hotList); // 모델에도 추가

        List<ShopDto> shopDtoList = shopService.shopPhoto();
        model.addAttribute("shopList", shopDtoList);

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    ///////////////////////////////////////////////////////////////////

//
//    //memberList
//    @GetMapping("/memberList")
//    public String memberList(
//
//            @RequestParam(name = "subject", required = false) String subject
//            , @RequestParam(name = "search", required = false) String search
//            , @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
//            , Model model) {
//
//
//        Page<MemberDto> pagingList = memberService.memberSearchPagingList(pageable, subject, search);
//
//        int totalPages = pagingList.getTotalPages(); //전체 페이지
//        int nowPage = pagingList.getNumber(); //현재 페이지
//        long totalElements = pagingList.getTotalElements(); //전체 레코드 개수
//        int size = pagingList.getSize(); //페이지 당 보이는 개수
//
//        int blockNum = 3; // 브라우저에 보이는 페이지 번호
//
//        int startPage = (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPages);
//        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;
//
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("pagingList", pagingList);
//
//
//
//        return "member/memberList";
//    }
//

///////////////////////////////////////////////////////////////////

    //memberList
    @GetMapping("/memberList")
    public String memberList(
            @ModelAttribute("hotList") List<ShopDto> hotList,
            @RequestParam(name = "subject", required = false) String subject
            , @RequestParam(name = "search", required = false) String search
            , @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {


        Page<MemberDto> pagingList = memberService.memberSearchPagingList(pageable, subject, search);

        int totalPages = pagingList.getTotalPages(); //전체 페이지
        int nowPage = pagingList.getNumber(); //현재 페이지
        long totalElements = pagingList.getTotalElements(); //전체 레코드 개수
        int size = pagingList.getSize(); //페이지 당 보이는 개수

        int blockNum = 3; // 브라우저에 보이는 페이지 번호

        int startPage = (int) ((Math.floor(nowPage / blockNum) * blockNum) + 1 <= totalPages ? (Math.floor(nowPage / blockNum) * blockNum) + 1 : totalPages);
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingList", pagingList);


        return "member/memberList";
    }


    /////////////////////////////////////////////////////////////////////////////////

    // 상세조회
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         @ModelAttribute("hotList") List<ShopDto> hotList,
                         @AuthenticationPrincipal MyUserDetails myUserDetails,
                         Model model){
        MemberDto member = memberService.selectOne(id);
        model.addAttribute("member", member);
        model.addAttribute("myUserDetails", myUserDetails);
        return "member/detail";
    }

    /////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/update")
    public String update(
            MemberDto memberDto) {

        memberService.memberUpdate(memberDto);

        return "redirect:/member/detail/" + memberDto.getId();
    }

    /////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id,
                         @ModelAttribute("hotList") List<ShopDto> hotList){

        memberService.memberDelete(id);
        String html="<script>"+
                "alert('회원삭제성공');"+
                "location.href='/member/';"+
                "</script>";

        return html;

    }


}
