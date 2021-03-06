package fall18_207project.GameCenter;

import java.io.Serializable;
import java.util.ArrayList;

interface GameScoreBoard extends Serializable{
//    TODO: GlobalScoreBoard and UserScoreBoard does not share the same update Method
//    But this interface theoretically should contain an updateScore() method.

//    void updateScore(Game completedGame);
//    void updateScore(String userName, Game completedGame);

    /***
     *
     * Function to get a ArrayList of ranked game for scoreBoard.
     * Parallel to getSortedUserNames() function.
     *
     * @param gameId The identifier for a specific type of Game. Like 3x3 SlidingTile, or 4x4 SlidingTile.
     * @return return a ArrayList of sorted Game for the specific game type. Sorting based on score.
     */
    ArrayList<Game> getSortedGames(int gameId);

}
