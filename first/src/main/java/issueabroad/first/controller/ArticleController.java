package issueabroad.first.controller;

import issueabroad.first.dto.CrawlDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.service.CrawlService;
import issueabroad.first.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final UserService userService;
    private final CrawlService crawlService;


    @GetMapping("/crawl/read/{id}")
    public String readCrawl(@PathVariable("id") Long id, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        CrawlDTO dto = crawlService.read(id);
        model.addAttribute("dto", dto);

        crawlService.updateViewCount(id);

        return "crawlArticle";
    } // 이런 식으로 추가해주기

    @GetMapping("/user/read/{id}")
    public String readUser(@PathVariable("id") Long id, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        UserDTO dto = userService.read(id);
        model.addAttribute("dto", dto);

        return "userArticle";
    }


//    @GetMapping("/read")
//    public String read(@PathVariable("{articleid}") Long articleId, Model model) {
//        Item one = ItemService.findOne(articleid);

        // dto 또는 form 채우기
        // one.set... 이걸 활용함. 소스코드 참고하기

        // 이후 model.addAttribute로 dto또는 form 담아주기

//        return "view-article";
//    }

}
