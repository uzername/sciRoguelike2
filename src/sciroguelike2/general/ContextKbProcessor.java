package sciroguelike2.general;

import java.awt.event.KeyEvent;
import sciroguelike2.algodata.GameActions;
import sciroguelike2.algodata.GameStates;

/**
 * Static class which contains processing routines for user's input. 
 * necessary mapings of key codes and corresponding actions are defined in ConfigProcessor
 * feels like it's the largest class in whole program
 * @author ivan
 */
public class ContextKbProcessor{
    /**
     * entry point for keyboard input processing.
     * Corresponding to user's input, current GameState, previous GameState (maybe) 
     * we choose GameAction perform the action and then set the GameState, using GameStateResolver
     */
    //=============================
    /*a semantic layer subroutines (wrappers) to be executed inside performAndChooseAction*/
    private static void kbNextMenuItemWrapper() {
        sciroguelike2.menugame.MainMenu.highlightNextItem();
    }
    private static void kbPrevMenuItemWrapper() {
        sciroguelike2.menugame.MainMenu.highlightPreviousItem();
    }
    private static void kbProcessCurrentMenuItemWrapper() {
        sciroguelike2.menugame.MainMenu.processCurrentMenuItem();
    }
    //=============================
    public static void performAndChooseAction(KeyEvent k) {
        //System.out.println("inside performAndChooseAction: "+processKeyCode);
        Integer processKeyCode = k.getExtendedKeyCode();
        Boolean isKbShiftDown = k.isShiftDown();
        Boolean isKbControlDown = k.isControlDown();
        Boolean isKbAltDown = k.isAltDown();
        System.out.println("Shift="+isKbShiftDown+" Control="+isKbControlDown+" Alt= "+isKbAltDown);
        sciroguelike2.algodata.GameStates currGameState = sciroguelike2.algodata.GameStateResolver.getCurrGameState();
        switch (processKeyCode) {
            case (KeyEvent.VK_Q): {
                if (isKbShiftDown==false) { //small 'q' symbol
                    if (currGameState==sciroguelike2.algodata.GameStates.MAINMENU) {
                        //quit game by pressing Q key, while in main menu
                        sciroguelike2.algodata.GameStateResolver.changeGameState(sciroguelike2.algodata.GameStates.QUITGAME);
                    }
                } else { //big 'q' symbol
                    
                }
                break;
            }
            case (KeyEvent.VK_UP): {
                if (currGameState==sciroguelike2.algodata.GameStates.MAINMENU) {
                    //move to previous menu item and highlight it
                    kbPrevMenuItemWrapper();
                    //do not change state, we're still in main menu
                } else {
                    if (currGameState==sciroguelike2.algodata.GameStates.MAINGAME) {
                        //move character one square up
                        sciroguelike2.datastructs.Player.movePlayerUp();
                        //do not change game state
                    }
                }
                break;
            }
            case (KeyEvent.VK_DOWN): {
                if (sciroguelike2.algodata.GameStateResolver.getCurrGameState()==sciroguelike2.algodata.GameStates.MAINMENU) {
                    //move to next menu item and highlight it
                    kbNextMenuItemWrapper();
                    //do not change state, we're still in main menu
                } else {
                    if (currGameState==sciroguelike2.algodata.GameStates.MAINGAME) {
                    sciroguelike2.datastructs.Player.movePlayerDown();
                    }
                }
                break;
            }
            case (KeyEvent.VK_ENTER): {
                if (sciroguelike2.algodata.GameStateResolver.getCurrGameState()==sciroguelike2.algodata.GameStates.MAINMENU){
                    kbProcessCurrentMenuItemWrapper();
                }
                break;
            }
            case (KeyEvent.VK_LEFT): {
                if (currGameState==sciroguelike2.algodata.GameStates.MAINGAME) {
                    sciroguelike2.datastructs.Player.movePlayerLeft();
                }
                break;
            }
            case (KeyEvent.VK_RIGHT): {
                if (currGameState==sciroguelike2.algodata.GameStates.MAINGAME) {
                    sciroguelike2.datastructs.Player.movePlayerRight();
                }
                break;
            }
        }
        //performAction(theChosenAction)
    }
    /**
     * Brand new way to choose what action to perform o user's input
     */
    public static void fancyPerformAndChooseAction(KeyEvent k) {
        sciroguelike2.algodata.GameStates currGameState = sciroguelike2.algodata.GameStateResolver.getCurrGameState();
        KeyProcessingStruct dataToAnalyze = new KeyProcessingStruct(
                k.getExtendedKeyCode(), 
                k.isShiftDown(), k.isControlDown(), k.isAltDown(), 
                currGameState);
        sciroguelike2.algodata.GameActions action2Perform = KbConfig.getKbConfig().getCorrespondingAction(dataToAnalyze);
        switch(action2Perform) {
            case MAINMENU_DOWN: {
                //move to next menu item and highlight it
                kbNextMenuItemWrapper();
                //do not change state, we're still in main menu
                break;
            }
            case MAINMENU_UP: {
                //move to previous menu item and highlight it
                kbPrevMenuItemWrapper();
                //do not change state, we're still in main menu
            }
        }
    }
    
}

