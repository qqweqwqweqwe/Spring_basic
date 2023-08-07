package hello.hellospring.domain;

public class Member {
    private Long id;   // 시스템이 저장하는 아이디 (식별자 라고 생각하면 될듯)
    private String name; // 멤버의 아이디 ( 우리가 흔히 생각하는 그 아이디)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
