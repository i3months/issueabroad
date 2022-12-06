package issueabroad.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

//    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, BindingResult result) { // Valid
        // @Valid 애너테이션을 사용하면 MemberForm 의 NotEmpty 등에 대해 Validation을 진행 해 준다.

        if(result.hasErrors()) { // Valid에 통과하지 못하면 result에 오류가 담긴다.
            return "members/createMemberForm";
            // MemberForm 에서 NotEmpty 애너테이션 이후 메세지를 추가해주면
            // 해당 메세지를 bindingresult가 가져가서 뷰에서 사용할 수 있도록 해 준다.
            // html 파일을 적절히 조작해 어느 부분에서 에러가 발생했는지 사용자에게 알려주자. (소스코드 참고)
            // Member 엔티티 대신 MemberForm을 쓰는 이유는 이게 더 깔끔해서
        }

        // form 에 있는 정보들을 꺼내서 적당히 사용해줌.

        // 이후 Member 인스턴스를 만든다. (form에 있는 정보를 사용함)
        // memberService 의 join을 호출해 멤버를 db에 저장

        return "redirect:/"; // 저장 된 후 다시 재로딩을 방지해야 한다. 리다이렉트를 사용함

    }

    @GetMapping("/members/find")
    public String findHome() { // model 으로

        return "members/findHome";
    }

    @GetMapping("/members/findId")
    public String fingId() {

        return "members/findId";
    }

    @GetMapping("/members/findPassword")
    public String findPassword() {

        return "members/findPassword";
    }
}
