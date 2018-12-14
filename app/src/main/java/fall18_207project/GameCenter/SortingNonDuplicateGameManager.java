package fall18_207project.GameCenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/***
 * This is the game history of Account. It stores all the games that the user saved, but have not finished.
 * For finished games, visit ScoreBoard.
 */
public class SortingNonDuplicateGameManager extends GameManager implements GameScoreBoard{

    public SortingNonDuplicateGameManager() {
        super();
    }


    /***
     * Do not allow two saved Games to have same saveId.
     */
    @Override
    void addGame(Game newGame) {
        if (hasGame(newGame.getSaveId())) {
            deleteGame(newGame.getSaveId());
        }
        this.gameList.add(newGame);
    }

    @Override
    public ArrayList<Game> getSortedGames(int gameId) {
        //TODO: After making UserHistoryAct and SavedGamesAct show a specific type of game, sort this.gameList
        return getSavedGames(gameId);
    }
}