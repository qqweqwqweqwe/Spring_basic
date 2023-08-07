package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository ;

    @Autowired  // 이것또한 이거 SpringConfig의 memberservice 가 bean으로 설정되어 있지 않으면 안먹힘  스프링 빈으로 설정되어 있느거만 autowired 적용됨
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
        // 멤버리포지토리를 외부에서 넣어줌 이런걸 di라고 함
        // di란 무엇?
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        ValidateDuplicateMember(member);//중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void ValidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }        // m은 찾아진 회원객체를 의미한다. 즉 m이 존재할 때 ( null 이 아니면)만 람다표현식이 실행되는 것임


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional <Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
