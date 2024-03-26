package project.community.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성된 식별자 사용
    private Long boardId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String nickname;

}
