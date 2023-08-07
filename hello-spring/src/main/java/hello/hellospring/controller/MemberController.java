package hello.hellospring.controller;


import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller  // 이러한 annotation으로 인해 스프링 컨테이너가 이제부터 관리함
public class MemberController {

    // private final MemberService memberservice =new MemberService();  // 이렇게 하면 여러 컨트롤러에서 멤버서비스를 쓸수가 있게 되어버림
    // 그래서 밑에처럼

    private final MemberService memberservice;

    @Autowired  //  이렇게 해주면 스프링 컨테이너에 있는 멤버서비스에 연결을 시켜줌 이게 바로 디펜던시 인젝션 즉 의존관계 주입임
    public MemberController(MemberService memberservice) {
        this.memberservice = memberservice;
    }
}
