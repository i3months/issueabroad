package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.service.CrawlService;
import issueabroad.first.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final CrawlService crawlService;

    @GetMapping("/")
    public String home(PageRequestDTO pageRequestDTO, Model model) {
        log.info("home controller");

        model.addAttribute("crawlURL", "crawl");
        model.addAttribute("userURL", "user");

        model.addAttribute("all", crawlService.getListMain(pageRequestDTO));
        model.addAttribute("week", crawlService.getListViewCountMain(pageRequestDTO));

        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("미국");

        model.addAttribute("america", crawlService.getListMain(pageRequestDTO));

        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("일본");

        model.addAttribute("japan", crawlService.getListMain(pageRequestDTO));

        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("자유");

        model.addAttribute("free", userService.getListMain(pageRequestDTO));

        pageRequestDTO.setType("d");
        pageRequestDTO.setKeyword("건의");

        model.addAttribute("suggest", userService.getListMain(pageRequestDTO));

        return "mainBeforeLogin";
    }

    @GetMapping("/home")
    public String home2() {

        return "redirect:/";
    }

}
