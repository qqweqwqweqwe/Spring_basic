package hello.hellospring.controller;


import hello.hellospring.Service.MemberService;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  // 이러한 annotation으로 인해 스프링 컨테이너가 이제부터 관리함
public class MemberController {

    // private final MemberService memberservice =new MemberService();  // 이렇게 하면 여러 컨트롤러에서 멤버서비스를 쓸수가 있게 되어버림
    // 그래서 밑에처럼

    private final MemberService memberservice;

    @Autowired  //  이렇게 해주면 스프링 컨테이너에 있는 멤버서비스에 연결을 시켜줌 이게 바로 디펜던시 인젝션 즉 의존관계 주입임
    public MemberController(MemberService memberservice) {
        this.memberservice = memberservice;
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberservice.join(member);
        return "redirect:/";  // 회원 가입이 끝나면 홈 화면으로 보내버리는 것
    }

    // get은 조회할떄 post 는 전달할떄 (보통)
    // url은 같지만 다른 post/get 에 따라서 다른 요청을 처리할 수 있다.

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members = memberservice.findMembers();
        // 멤버를 전부다 끄집어 옴
        model.addAttribute("members", members);
        return "members/memberList";
    }


    // 위 과정은 모두 메모리멤버에 저장되기 때문에 서버를 내렸다 올리면 데이터가 삭제된다.
    // 그래서 우리는 저 이름들을 파일이나 데이터베이스에 저장해야 한다.
}
