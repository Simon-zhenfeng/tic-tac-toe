import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * @author Simon
 * @date 2020/8/1 17:19
 */
public class TicTacToeTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public void setUp() throws Exception {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void should_throw_exception_when_X_outside_board() {
        exception.expect(RuntimeException.class);
        exception.expectMessage(equalTo("X is outside board"));
        ticTacToe.play(5, 2);
    }

    @Test
    public void should_throw_exception_when_Y_outside_board() {
        exception.expect(RuntimeException.class);
        exception.expectMessage(equalTo("Y is outside board"));
        ticTacToe.play(2, 4);
    }

    @Test
    public void should_throw_exception_when_occupied() {
        ticTacToe.play(1, 2);
        exception.expect(RuntimeException.class);
        exception.expectMessage(equalTo("Here is occupied"));
        ticTacToe.play(1, 2);
    }

    @Test
    public void given_first_turn_when_next_player_then_X() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void given_last_turn_was_x_when_next_player_then_O() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    @Test
    public void when_play_then_no_winner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No winner", actual);
    }

    @Test
    public void when_play_and_whole_horizontal_line_then_winner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(1, 3);
        String actual = ticTacToe.play(3, 1);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void when_play_and_whole_vertical_line_then_winner() {
        ticTacToe.play(2, 1);//x
        ticTacToe.play(1, 1);//o
        ticTacToe.play(3, 1);//x
        ticTacToe.play(1, 2);//o
        ticTacToe.play(2, 2);//x
        String actual = ticTacToe.play(1, 3);
        assertEquals("O is the winner", actual);
    }

    @Test
    public void when_play_and_whole_top_bottom_diagonal_line_then_winner() {
        ticTacToe.play(1, 1);//x
        ticTacToe.play(1, 3);//o
        ticTacToe.play(2, 2);//x
        ticTacToe.play(2, 3);//o
        String actual = ticTacToe.play(3, 3);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void when_play_and_bottom_top_diagonal_line_then_winner() {
        ticTacToe.play(1, 3);//x
        ticTacToe.play(1, 1);//o
        ticTacToe.play(2, 2);//x
        ticTacToe.play(3, 3);//o
        String actual = ticTacToe.play(3, 1);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void when_all_board_area_filled_then_draw() {
        ticTacToe.play(1, 1);//x
        ticTacToe.play(1, 2);//o
        ticTacToe.play(1, 3);//x
        ticTacToe.play(2, 1);//o
        ticTacToe.play(2, 3);//o
        ticTacToe.play(2, 2);//o
        ticTacToe.play(3, 1);//o
        ticTacToe.play(3, 3);//o
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }
}
