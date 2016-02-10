package player;
import board.Grid;

/**
 * Created by Student on 2/10/2016.
 */
public class HumanPlayer extends Player{
    Grid grid;

    public void update(Grid grid) {
        if (active == true) {
            if (grid.clicked != null) {
                move = grid.clicked.col;
                movemade = true;
            }
        }
    }
}
