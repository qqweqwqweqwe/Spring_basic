package hello.hellospring.Service;

import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  // 이거 읽고
public class SpringConfig {


    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 이렇게 되면 멤버서비스가 스프링 빈에 등록이 됨
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new JdbcTemplateMemberRepository(dataSource); // DbMemoryMemberRepository 로 바꿔주기만 하면 db가 자동적으로 연결됨(!?)

        return new JpamemberRepository(em);
    }   // 이제 db와 연결이 되었다.


}
