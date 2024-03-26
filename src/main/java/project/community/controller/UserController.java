package project.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.domain.User;
import project.community.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String getSignup(){
        return "signupForm";
    }

    // 회원가입
    @PostMapping("/signup")
    public String postSignup(User user){
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "loginForm";
    }

    // 로그인
    @PostMapping("/login")
    public String postLogin(User user, HttpServletRequest request){

        Optional<User> foundUser = userRepository.findById(user.getId());

        if(foundUser.isPresent()){
            // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = request.getSession(); // 세션이 없을 때,
                                                            // 일단 중복 없는 sessionID(JSESSIONID)를 만들어서(값은 없는 상태) 세션 저장소에 저장해놓고,
                                                            // 이것을 브라우저에게 전달하는 기능까지 함.
            // sessionId의 값(value)을 설정함, 첫번째 파라미터는 value의 속성명
            session.setAttribute("loggedInUser", foundUser.get());
            return "redirect:/";
        } else{
            return "redirect:/login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 저장된 사용자 정보 삭제
        session.removeAttribute("loggedInUser");
        return "redirect:/";
    }

}
