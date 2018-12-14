//package fall18_207project.GameCenter;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///***
// * A user ScoreBoard.
// */
//class UserScoreBoard implements GameScoreBoard {
//
//    // Map of <gameId> to list of ScoreEntry. ScoreEntry is Node of userName and Game.
////    Map<Integer, ArrayList<GameRecord>> recordMap;
//
//    // Map of <gameId> to <NonDuplicateGameManager>, each NonDuplicateGameManager is responsible for a specific type of Game.
////    private Map<Integer, GameManager> recordMap;
//
//    private ArrayList<Game> gameRecords;
//    UserScoreBoard() {
////        recordMap = new HashMap<Integer, ArrayList<GameRecord>>();
////        recordMap = new HashMap<Integer, GameManager>();
//        gameRecords = new ArrayList<Game>();
//    }
//
//
//    public void updateScore(Game completedGame) {
//        gameRecords.add(completedGame);
//    }
//
//
//
////    /***
////     * Helper function to extract a list of GameRecords from recordMap.
////     *
////     * @param gameId
////     * @return an ArrayList of sorted ScoreRecord with respect to the gameId.
////     */
////    ArrayList<GameRecord> getSortedGameRecord(int gameId) {
////        ArrayList<GameRecord> gameRecordList = recordMap.get(gameId);
////        Collections.sort(gameRecordList);
////        return gameRecordList;
////    }
//
//    public ArrayList<Game> getSortedGames(int gameId) {
//        ArrayList<Game> specificTypeGameList = new ArrayList<Game>();
//        for (Game g : gameRecords) {
//            if (g.getGameId() == gameId) {
//                specificTypeGameList.add(g);
//            }
//        }
////        specificTypeGameList.sort();
//        return specificTypeGameList;
//    }
//
//    /***
//     * Basically extracting all games from user score board. Should use getSortedGames(<gameId></>)
//     * @return ArrayList<Game>
//     */
//    @Deprecated ArrayList<Game> getAllGames() {
//        return gameRecords;
//    }
//
////    /**
////     * Wrapper class to make each GameRecord comparable.
////     */
////    private class GameRecord implements Comparable<GameRecord>{
////        private Game game;
////        GameRecord(Game game) {
////            this.game = game;
////        }
////
////        public Game getGame() {
////            return this.game;
////        }
////
////        @Override
////        public int compareTo(@NonNull GameRecord o) {
////            return this.game.calculateScore() - o.getGame().calculateScore();
////        }
////    }
//}
