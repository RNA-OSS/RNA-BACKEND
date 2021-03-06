package com.dna.rna.domain.clubBoard;

import com.dna.rna.domain.board.Board;
import com.dna.rna.domain.board.BoardRepositoryImpl;
import com.dna.rna.domain.club.Club;
import com.dna.rna.domain.club.ClubRepository;
import com.dna.rna.domain.school.School;
import com.dna.rna.domain.school.SchoolRepositoryImpl;
import com.dna.rna.domain.testUtils.RNAJpaTestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubBoardRepositoryTest extends RNAJpaTestUtils {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private ClubBoardRepositoryImpl clubBoardRepository;

    public School makeSchool(String schoolName) {
        School school = School.of(schoolName);
        schoolRepository.save(school);
        return school;
    }

    public Club makeClub(School school, String clubName) {
        Club club = Club.of(school, clubName, buildUser(), LocalDate.now(),
                "1기", "dongguk univ", "hello", "loooong", "good");
        clubRepository.save(club);
        return club;
    }

    @Test
    public void fetchAllBoardsOfClub() {
        School school = makeSchool("dgu");
        Club club = makeClub(school, "rna");
        String newBoardName = "foo_board";
        Board board = Board.of("foo_board");
        boardRepository.save(board);
        ClubBoard clubBoard = ClubBoard.of(club, board, null, 0);
        clubBoardRepository.save(clubBoard);

        List<ClubBoard> aClubBoard = clubBoardRepository.fetchBoardsOfClub(club, board);
        assertThat(aClubBoard.size()).isEqualTo(1);
        assertThat(aClubBoard.get(0).getBoard().getBoardName()).isEqualTo(newBoardName);

    }
}
