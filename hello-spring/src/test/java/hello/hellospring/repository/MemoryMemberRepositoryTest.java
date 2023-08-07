package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 끝날때마다 실행되는 어떤 콜백 메소드
    public void afterEach(){
        repository.clearStore();  // 테스트가 한번씩 실행되고 끝날때마다 저장소를 지운다
    }


    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // nullable 값은 get을 통해서 꺼낼 수 있다.
        Assertions.assertEquals(result,member); // null 넣으면 error
        assertThat(member).isEqualTo(result); //
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result= repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List <Member>result = repository .findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
