package com.dna.rna.domain.ClubBoard;

import com.dna.rna.domain.BaseAuditorEntity;
import com.dna.rna.domain.Board.Board;
import com.dna.rna.domain.Board.BoardItem;
import com.dna.rna.domain.Club.Club;
import com.dna.rna.domain.boardGroup.BoardGroup;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import static com.dna.rna.domain.Board.Board.BOARD_ID;
import static com.dna.rna.domain.Club.Club.CLUB_ID;
import static com.dna.rna.domain.boardGroup.BoardGroup.BOARD_GROUP_ID;
import static java.util.Objects.requireNonNull;

@Getter
@Setter
@Entity
@Table(name = "club_board")
public class ClubBoard extends BaseAuditorEntity implements BoardItem {

    private static final Logger logger= LoggerFactory.getLogger(ClubBoard.class);

    public static final String CLUB_BOARD_ID = "club_board_id";

    @Id @GeneratedValue(strategy=  GenerationType.SEQUENCE)
    @Column(name = CLUB_BOARD_ID)
    private long clubBoardId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = BOARD_ID, nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CLUB_ID, nullable = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = BOARD_GROUP_ID, nullable = false)
    private BoardGroup boardGroup;

    public static ClubBoard of(Club club, Board board, BoardGroup boardGroup) {
        requireNonNull(board, "board는 null이 될 수 없습니다.");
        requireNonNull(club, "club은 null이 될 수 없습니다.");
        return new ClubBoard(club, board, boardGroup);
    }

    private ClubBoard() {}

    private ClubBoard(Club club, Board board, BoardGroup boardGroup) {
        this.board = board;
        this.club = club;
        this.boardGroup = boardGroup;
    }

}
