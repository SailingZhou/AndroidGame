package fall18_207project.GameCenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game2048Board extends Board implements Cloneable {

    /**
     * The number of rows.
     */
    private int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    private int NUM_COLS = 4;


    /**
     * The tiles on the board in row-major order.
     */
    private Game2048Tile[][] tiles;


    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public Game2048Board(List<Game2048Tile> tiles, int size) {
        super(tiles, size);
        this.NUM_COLS = size;
        this.NUM_ROWS = size;
        this.tiles = new Game2048Tile[size][size];

        Iterator<?> iter = tiles.iterator();

        for (int row = 0; row != this.getNUM_COLS(); row++) {
            for (int col = 0; col != this.getNUM_COLS(); col++) {
                this.tiles[row][col] = (Game2048Tile) iter.next();
            }
        }
    }

    int getNUM_COLS() {
        return NUM_COLS;
    }

    int getNUM_ROWS() {
        return NUM_ROWS;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_ROWS * NUM_COLS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Game2048Tile getTile(int row, int col) {
        return (Game2048Tile) tiles[row][col];
    }

    Tile[][] getTiles(){
        return this.tiles;
    }
    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */

    void swapTiles(int row1, int col1, int row2, int col2) {
        Game2048Tile t1 = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = t1;
        setChanged();
        notifyObservers();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                temp.append(" ").append(this.getTile(row, col).getId());
            }
        }
        return "Board: { " +
                "tiles = [" + temp +
                " ]" + " }";
    }

//    @NonNull
//    @Override
//    public Iterator<Game2048Tile> iterator() {
//        return new BoardIterator();
//    }
//
//    /**
//     * The iterator class for Board's iterator.
//     */
//    private class BoardIterator implements Iterator<Game2048Tile> {
//        /**
//         * The position
//         */
//        private int position = 0;
//
//        @Override
//        public boolean hasNext() {
//            return position != NUM_ROWS * NUM_COLS;
//        }
//
//        @Override
//        public Game2048Tile next() {
//            int row = position / NUM_ROWS;
//            int col = position % NUM_COLS;
//            Game2048Tile nextTile = tiles[row][col];
//            position++;
//            return nextTile;
//        }
//    }

    public Game2048Board clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        List<Game2048Tile> tiles = new ArrayList<>();
        for (int i = 0; i < this.getNUM_COLS(); i++) {
            for (int j = 0; j < this.getNUM_ROWS(); j++) {
                tiles.add(this.getTile(i, j));
            }
        }

        return new Game2048Board(tiles, this.getNUM_ROWS());

    }
}

