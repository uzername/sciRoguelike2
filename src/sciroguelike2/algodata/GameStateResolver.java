package sciroguelike2.algodata;

/**
 * same keys may have different actions depending on where we are
 * (arrow/movement keys may be used to navigate a character or to move menu)
 * @see KbConfig
 * like other config classes, this one is mostly static
 * @author ivan
 */
public class GameStateResolver {
    private static GameStates prevGameState = GameStates.MAINMENU;
    private static GameStates currGameState = GameStates.MAINMENU;
    public static void changeGameState(GameStates newGameState) {
        prevGameState = currGameState;
        currGameState = newGameState;
    }

    /**
     * @return the prevGameState. might be usefull. sometimes.
     */
    public static GameStates getPrevGameState() {
        return prevGameState;
    }
    /**
     * @return the currGameState
     */
    public static GameStates getCurrGameState() {
        return currGameState;
    }
}
