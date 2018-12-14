package fall18_207project.GameCenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedGamesController {
//    public static String userEmail = "";
//    private AccountManager accountManager;
    // TODO: Maybe we don't need context
    private Context mContext;
    private GameManager gameManager;

   SavedGamesController(Context context){
//       this.accountManager = acManager;
//       this.userEmail = user;
       this.mContext = context;
   }

    public void getData(List<Map<String, Object>> list, String saveType, String gameType, ArrayList<Game> gameList ) {
        Account currAccount = CurrentAccountController.getCurrAccount();

        if (currAccount != null) {
            if (saveType.equals("autoSave")) {
                gameManager = currAccount.getAutoSavedGames();
            } else {
                gameManager = currAccount.getUserSavedGames();
            }

            ArrayList<Game> allGameList = gameManager.getAllGameList();
            for(Game game : allGameList){
                if("slidingTiles".equals(gameType)){
                    gameList.add(game);
                }
                else if("matchingCards".equals(gameType)){
                    gameList.add(game);
                }
                else if ("game2048".equals(gameType)){
                    gameList.add(game);
                }
            }
            Collections.reverse(gameList);
            for (Game g: gameList) {
                Map<String, Object> map = new HashMap<>();
                map.put("gameId", g.gameId);
                map.put("time", g.getTime());
                list.add(map);
            }
        }

    }
}
