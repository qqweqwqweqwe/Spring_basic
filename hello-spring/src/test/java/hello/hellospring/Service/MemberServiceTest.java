package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


class MemberServiceTest {
    // 테스트 코드는 한글로 작성해도된다
    // 테스트 코드는 실제 코드에 포함되지 않는다.

    /*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository =new MemoryMemberRepository();
    */

    // 근데 여기서 애매한점, 위에 MemberService의 메모리멤버리포지토리랑
    // 여기의 메모리멤버리포지토리랑 다른 객체임
    // 지금 문제는 멤버서비스에서 만든 메모리멤버리포지토리랑
    // 테스트케이스에서 만든 메모리멤버리포지토리랑 다른 리포지토리임
    // 테스트는 같은 리포지토리로 하는게 맞음
    // 그럼 어떻게?
    // 다음과 같이 짜주면 됨

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository= new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 테스트를 실행할 때마다 리포지토리를 생성해줌
        // 테스트는 독립적으로 실행되어야 하기 때문에
    }


    @AfterEach  // 테스트케이스 실행할 때마다 clearstore 데이터 베이스 클리어해줌
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given  주어진 상황
        Member member = new Member();
        member.setName("hello");

        // when 실행했을때
        Long saveId = memberService.join(member);

        // then 이게 나와야돼 (검증단계)

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

        // 테스트는 정상 출력도 중요하지만, 언제 예외처리 해줄지가 더 중요하다
    }

    @Test
    public void 중복회원예외(){  // 예외 처리 메소드가 잘 작동하는지 확인하는 함수
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 =new Member();
        member2.setName("spring");

        //when

        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2)); // 예외가 터져야함
/*
        try {
            memberService.join(member2); // 이게 실행되면 실패임 catch로 넘어가야함
            Assertions.fail();
        }
        catch (IllegalStateException e ){  // 이게 실행되면 예외가 터져서 정상적으로 실행된거
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        //then

 */

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}