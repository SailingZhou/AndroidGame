package fall18_207project.GameCenter;

/***
 * This is the game history of Account. It stores all the games that the user saved, but have not finished.
 * For finished games, visit ScoreBoard.
 */
public class DuplicateGameManager extends GameManager {

    public DuplicateGameManager() {
        super();
    }

    @Override
    void addGame(Game newGame) {
        this.gameList.add(newGame);
    }
}