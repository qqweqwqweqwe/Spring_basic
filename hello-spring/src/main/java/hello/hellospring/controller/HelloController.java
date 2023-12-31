package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")  // hello 로 들어오면 밑에 있는걸로  매핑,  밑에있는 메서드 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public  String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    @GetMapping("hello-string")

    @ResponseBody
    public String helloString (@RequestParam("name") String name){
        return "hello"+name; // hello spring
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello  helloApi(@RequestParam("name") String name){// Hello 라는 객체 생성
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    } // api 방식
    static class  Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }

}
