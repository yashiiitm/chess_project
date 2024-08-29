import org.junit.jupiter.api.Test;
import org.mycomp.Chess;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ChessTest {
    @Test
    public void test_get_pawn_possible_spots(){
        List<String> expected = List.of("D2");
        List<String> actual = Chess.getPawnPossibleSpots(1, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_get_king_possible_spots(){
        List<String> expected = List.of("C1", "D1", "E1", "C2", "E2", "C3", "D3", "E3");
        List<String> actual = Chess.getKingPossibleSpots(2, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_get_queen_possible_spots(){
        List<String> expected = List.of("B1", "D1", "F1", "C2", "D2", "E2", "A3", "B3", "C3", "E3", "F3", "G3", "H3", "C4", "D4", "E4", "B5", "D5", "F5", "A6", "D6", "G6", "D7", "H7", "D8");
        List<String> actual = Chess.getQueenPossibleSpots(3, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_get_possible_spots_king(){
        List<String> expected = List.of("C1", "D1", "E1", "C2", "E2", "C3", "D3", "E3");
        List<String> actual = Chess.getPossibleSpots("KING", 2, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_get_possible_spots_invalid_piece(){
        List<String> expected = List.of();
        List<String> actual = Chess.getPossibleSpots("ROOK", 2, 4);
        assertEquals(expected, actual);
    }
}
