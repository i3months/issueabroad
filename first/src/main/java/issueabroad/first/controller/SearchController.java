package issueabroad.first.controller;

import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.service.CrawlService;
import issueabroad.first.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SearchController {

    private final UserService userService;
    private final CrawlService crawlService;

    @GetMapping("/search/{keyword}")
    public String search(@PathVariable("keyword") String keyword, PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setType("to");
        pageRequestDTO.setKeyword(keyword); // 키워드 지정해줘야됨

        model.addAttribute("result", crawlService.getList(pageRequestDTO));
        model.addAttribute("title", keyword);
        model.addAttribute("url", "crawl");
        model.addAttribute("paging", "search/" + keyword );

        return "search/searchResult";
    }
}
