package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.service.CrawlService;
import issueabroad.first.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final UserService userService;
    private final CrawlService crawlService; // service 추가는 검색 이후에 적용 ㄱㄱ

    @GetMapping("/all")
    public String all(PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", crawlService.getList(pageRequestDTO));
        model.addAttribute("title", "최신");
        model.addAttribute("url", "crawl");
        model.addAttribute("paging", "all");
        return "crawlBoard";
    }

    @GetMapping("/week") // week는 일단 crawl 재활용
    public String week(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", crawlService.getListViewCount(pageRequestDTO));
        model.addAttribute("title", "일주일간");
        model.addAttribute("url", "crawl");
        model.addAttribute("paging", "week");
        return "crawlBoard";
    }

    @GetMapping("/america")
    public String america(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("미국");

        model.addAttribute("result", crawlService.getList(pageRequestDTO));
        model.addAttribute("title", "미국");
        model.addAttribute("url", "crawl");
        model.addAttribute("paging", "america");

        return "crawlBoard";
    }

    @GetMapping("/japan")
    public String japan(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("일본");

        model.addAttribute("result", crawlService.getList(pageRequestDTO));
        model.addAttribute("title", "일본");
        model.addAttribute("url", "crawl");
        model.addAttribute("paging", "japan");

        return "crawlBoard";
    }

    @GetMapping("/free")
    public String free(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("자유");

        model.addAttribute("result", userService.getList(pageRequestDTO));
        model.addAttribute("title", "자유");
        model.addAttribute("url", "user");
        model.addAttribute("paging", "free");

        return "userBoard";
    }

    @GetMapping("/suggest")
    public String suggest(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("건의");

        model.addAttribute("result", userService.getList(pageRequestDTO));
        model.addAttribute("title", "건의");
        model.addAttribute("url", "user");
        model.addAttribute("paging", "suggest");

        return "userBoard";
    }

    // 위에서 한 것 처럼 recent week usa jp free suggest 모두 GetMapping 해주기
}
