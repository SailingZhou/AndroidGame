//package fall18_207project.GameCenter;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class ScoreBoard implements Serializable {
//    private ArrayList<Integer> rankPerGameThree;
//    private ArrayList<String> rankPerGameThreeUser;
//    private ArrayList<Integer> rankPerGameFour;
//    private ArrayList<String> rankPerGameFourUser;
//    private ArrayList<Integer> rankPerGameFive;
//    private ArrayList<String> rankPerGameFiveUser;
//
//    ScoreBoard() {
//        this.rankPerGameThree = new ArrayList<>();
//        this.rankPerGameThreeUser = new ArrayList<>();
//        this.rankPerGameFour = new ArrayList<>();
//        this.rankPerGameFourUser = new ArrayList<>();
//        this.rankPerGameFive = new ArrayList<>();
//        this.rankPerGameFiveUser = new ArrayList<>();
//    }
//
//    /**
//     * update the score of the game, only top 10 will be recorded.
//     *
//     * @param gameId   gameID is whether the game is size 3,4, or 5
//     * @param userName userName
//     * @param score    score of the user get in their game
//     */
//    void updateScorePerGame(int gameId, String userName, int score) {
//        // 3 x 3
//        if (gameId == 1) {
//            addSize3GameScore(userName, score);
//        } // 4 x 4
//        else if (gameId == 2) {
//            addSize4GameScore(userName, score);
//
//        } // 5 x 5
//        else if (gameId == 3) {
//            addSize5GameScore(userName, score);
//        }
//    }
//
//    ArrayList<String> getRankUserNamePerGame(int gameId) {
//        // 3 x 3
//        if (gameId == 1) {
//            return this.rankPerGameThreeUser;
//        } // 4 x 4
//        else if (gameId == 2) {
//            return this.rankPerGameFourUser;
//        } // 5 x 5
//        else if (gameId == 3) {
//            return this.rankPerGameFiveUser;
//        }
//
//        return null;
//    }
//
//    ArrayList<Integer> getRankScorePerGame(int gameId) {
//
//        if (gameId == 1) {
//            return this.rankPerGameThree;
//        } else if (gameId == 2) {
//            return this.rankPerGameFour;
//        } else if (gameId == 3) {
//            return this.rankPerGameFive;
//        }
//
//        return null;
//    }
//
//    /**
//     * private helper method for updateScore of size 3 board.
//     *
//     * @param userName userName
//     * @param score    score
//     */
//    private void addSize3GameScore(String userName, int score) {
//        if (this.rankPerGameThree.size() < 10) {
//            this.rankPerGameThree.add(score);
//            Collections.sort(this.rankPerGameThree);
//            Collections.reverse(this.rankPerGameThree);
//            int index = this.rankPerGameThree.indexOf(score);
//            this.rankPerGameThreeUser.add(index, userName);
//        } else {
//            this.rankPerGameThree.add(score);
//            Collections.sort(this.rankPerGameThree);
//            Collections.reverse(this.rankPerGameThree);
//            int index = this.rankPerGameThree.indexOf(score);
//            this.rankPerGameThreeUser.add(index, userName);
//            ArrayList<Integer> tmp = new ArrayList<>(this.rankPerGameThree.subList(0, 10));
//            this.rankPerGameThree = tmp;
//            ArrayList<String> tmpUser = new ArrayList<>(this.rankPerGameThreeUser.subList(0, 10));
//            this.rankPerGameThreeUser = tmpUser;
//        }
//    }
//
//    /**
//     * private method for updateScore of size 4 board.
//     *
//     * @param userName userName
//     * @param score    score
//     */
//    private void addSize4GameScore(String userName, int score) {
//        if (this.rankPerGameFour.size() < 10) {
//            this.rankPerGameFour.add(score);
//            Collections.sort(this.rankPerGameFour);
//            Collections.reverse(this.rankPerGameFour);
//            int index = this.rankPerGameFour.indexOf(score);
//            this.rankPerGameFourUser.add(index, userName);
//        } else {
//            this.rankPerGameFour.add(score);
//            Collections.sort(this.rankPerGameFour);
//            Collections.reverse(this.rankPerGameFour);
//            int index = this.rankPerGameFour.indexOf(score);
//            this.rankPerGameFourUser.add(index, userName);
//            ArrayList<Integer> tmp = new ArrayList<>(this.rankPerGameFour.subList(0, 10));
//            this.rankPerGameFour = tmp;
//            ArrayList<String> tmpUser = new ArrayList<>(this.rankPerGameThreeUser.subList(0, 10));
//            this.rankPerGameFourUser = tmpUser;
//        }
//    }
//
//    /**
//     * private method for updateScore of size 5 board.
//     *
//     * @param userName userName
//     * @param score    score
//     */
//    private void addSize5GameScore(String userName, int score) {
//        if (this.rankPerGameFive.size() < 10) {
//            this.rankPerGameFive.add(score);
//            Collections.sort(this.rankPerGameFive);
//            Collections.reverse(this.rankPerGameFive);
//            int index = this.rankPerGameFive.indexOf(score);
//            this.rankPerGameFiveUser.add(index, userName);
//        } else {
//            this.rankPerGameFive.add(score);
//            Collections.sort(this.rankPerGameFive);
//            Collections.reverse(this.rankPerGameFive);
//            int index = this.rankPerGameFive.indexOf(score);
//            this.rankPerGameFiveUser.add(index, userName);
//            ArrayList<Integer> tmp = new ArrayList<>(this.rankPerGameFive.subList(0, 10));
//            this.rankPerGameFive = tmp;
//            ArrayList<String> tmpUser = new ArrayList<>(this.rankPerGameFiveUser.subList(0, 10));
//            this.rankPerGameFiveUser = tmpUser;
//        }
//    }
//}
