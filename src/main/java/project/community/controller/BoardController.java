package project.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.domain.Board;
import project.community.domain.User;
import project.community.repository.BoardRepository;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public String boards(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "allBoards";
    }

    @GetMapping("/add")
    public String addForm(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        if (session != null && session.getAttribute("loggedInUser") != null) {
            return "boardForm";
        } else {
            model.addAttribute("message", "로그인 시에만 이용할 수 있습니다.");
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String addBoard(Board board, HttpSession session){

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            board.setNickname(loggedInUser.getNickname());
            boardRepository.save(board);
        }

        return "redirect:/boards";
    }

}
