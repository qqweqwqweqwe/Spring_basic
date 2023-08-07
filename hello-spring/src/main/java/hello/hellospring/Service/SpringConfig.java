package hello.hellospring.Service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 이거 읽고
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 이렇게 되면 멤버서비스가 스프링 빈에 등록이 됨
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
