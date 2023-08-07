package hello.hellospring.Service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  // 이거 읽고
public class SpringConfig {


    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 이렇게 되면 멤버서비스가 스프링 빈에 등록이 됨
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource); // DbMemoryMemberRepository 로 바꿔주기만 하면 db가 자동적으로 연결됨(!?)
    }   // 이제 db와 연결이 되었다.

}
