package sciroguelike2.general;

import java.util.ArrayList;
import java.util.HashMap;
import sciroguelike2.algodata.GameActions;
import sciroguelike2.algodata.GameStates;

/**
 * array of key configuration
 * @see KeyProcessingStruct
 * @author ivan
 */
public class KbConfig {
    private static KbConfig kbConfigInstance;
    private HashMap<KeyProcessingStruct, GameActions> allKbConfig; 
    //no need to make it static; It is accessed by kbConfigInstance
    private KbConfig() {
        allKbConfig = new HashMap<>(); //we'll use this struct later, let's have it created
    }
    public static KbConfig getKbConfig() {
        if (kbConfigInstance==null) {
            kbConfigInstance = new KbConfig();
        }
        return kbConfigInstance;
    }
    public void defineKeysInCode() {
        //by the moment of calling this subroutine allKbConfig is already initialized.
        //the entry point of all operations is getKbConfig() which initializes the instance. 
        //that's why allKbConfig is also single in the whole program.
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_Q, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINMENU), 
                GameActions.GAMEQUIT
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_UP, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINMENU), 
                GameActions.MAINMENU_UP
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_UP, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINGAME), 
                GameActions.MOVE_FORWARD
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_DOWN, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINGAME), 
                GameActions.MOVE_BACK
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_LEFT, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINGAME), 
                GameActions.MOVE_LEFT
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_RIGHT, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINGAME), 
                GameActions.MOVE_RIGHT
                       );
        allKbConfig.put(
                new KeyProcessingStruct(java.awt.event.KeyEvent.VK_ENTER, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, sciroguelike2.algodata.GameStates.MAINMENU), 
                GameActions.MAINMENU_ENTER
                       );
    }
    public GameActions getCorrespondingAction(KeyProcessingStruct inpKbData) {
        GameActions finalAction = GameActions.NULL_ACTION;
        finalAction = allKbConfig.get(inpKbData);
        if (finalAction==null) {
            finalAction = GameActions.NULL_ACTION;
        }
        return finalAction;
    }
}
