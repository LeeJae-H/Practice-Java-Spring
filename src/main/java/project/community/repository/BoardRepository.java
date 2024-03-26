package project.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.community.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
