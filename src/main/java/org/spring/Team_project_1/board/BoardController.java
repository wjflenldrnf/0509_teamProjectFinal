
package org.spring.Team_project_1.board;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.shop.ShopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@SessionAttributes("hotList")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String write(Model model, @ModelAttribute("hotList") List<ShopDto> hotList) {

        model.addAttribute("boardDto", new BoardDto());

        return "board/write";
    }

    @PostMapping("/write")
    public String writeOk(@Valid BoardDto boardDto, BindingResult bindingResult) {

        // form에서 전송된 데이터를 BoardDto에 설정된 유효성검사가 규칙을 위반하면
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        boardService.write(boardDto);
        return "redirect:/board/pagingList";
    }

    @GetMapping("/boardList")
    public String boardList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(name = "subject", required = false) String subject,
                            @RequestParam(name = "search", required = false) String search,
                            Model model, @ModelAttribute("hotList") List<ShopDto> hotList) {

        Page<BoardDto> pagingList = boardService.boardSearchPagingList(pageable, subject, search);
        ;

        int totalPages = pagingList.getTotalPages();// 전체페이지
        int newPage = pagingList.getNumber();// 현재페이지
        long totalElements = pagingList.getTotalElements();// 전체레코드 갯수
        int size = pagingList.getSize(); // 페이지당 보이는 갯수

        int blockNum = 3;  // 브라우저에 표이는 페이지번호
        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
                ? (Math.floor(newPage / blockNum) * blockNum) + 1
                : totalPages
        );
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingList", pagingList);


//    model.addAttribute("boardList", pagingList);
        return "board/boardList";
    }

    @GetMapping("/pagingList")
    public String pagingList(@PageableDefault(page = 0, size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model, @ModelAttribute("hotList") List<ShopDto> hotList) {

        Page<BoardDto> pagingList = boardService.pagingList(pageable);

        int totalPages = pagingList.getTotalPages();// 전체페이지
        int newPage = pagingList.getNumber();// 현재페이지
        long totalElements = pagingList.getTotalElements();// 전체레코드 갯수
        int size = pagingList.getSize(); // 페이지당 보이는 갯수

        int blockNum = 3;  // 브라우저에 표이는 페이지번호
        int startPage = (int) ((Math.floor(newPage / blockNum) * blockNum) + 1 <= totalPages
                ? (Math.floor(newPage / blockNum) * blockNum) + 1
                : totalPages
        );
        int endPage = (startPage + blockNum) - 1 < totalPages ? (startPage + blockNum) - 1 : totalPages;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingList", pagingList);

        return "board/pagingList";
    }

    //게시글 수정
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto) {
        boardService.update(boardDto);
        return "redirect:/board/detail/" + boardDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        boardService.deleteBoard(id);
        return "redirect:/board/pagingList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, @ModelAttribute("hotList") List<ShopDto> hotList) {
        BoardDto board = boardService.boardOne(id);
        if (board != null) {
            model.addAttribute("board", board);
        }
        return "board/detail";
    }


}
