package sciroguelike2.menugame;

import sciroguelike2.algodata.GeneralCoreData;
/**
 * this class is used to display game menu and handle user's input
 * it's good to make MainMenu static because it's single for all game
 */
public class MainMenu {
     /** 
     * Shows what current menu item is being selected. 
     */
    private static Integer menuIndex=0;
    private static String[] menuItems = {"New Game","Load Game","Quit Game"};
    private static int highlightedColor = 0x11BBCC; //color of highlighted menu item
    private static int regularColor = 0x1122CC;
    public static void displayMainMenu() {
        GeneralCoreData.foregroundpane.erase();
        GeneralCoreData.backgroundpane.erase();
         Integer currentRow = sciroguelike2.algodata.GeneralCoreData.screenHeight-menuItems.length-2;
        for (String menuItem : menuItems) {
            GeneralCoreData.foregroundpane.put(3, currentRow, menuItem, new squidpony.SColor(regularColor) );
            //general.algomaps.MapDisplay.globalCsi.print(3, currentRow, menuItem, regularColor);
            currentRow++;
        }
        highlightMainMenu(highlightedColor);
        GeneralCoreData.foregroundpane.refresh();
    }
    public static void highlightMainMenu(int hexintcolor) {
        Integer theLineIndex=sciroguelike2.algodata.GeneralCoreData.screenHeight-menuItems.length-2+menuIndex;
        GeneralCoreData.foregroundpane.put(3, theLineIndex, menuItems[menuIndex], new squidpony.SColor(hexintcolor));
        GeneralCoreData.foregroundpane.refresh();
    }
    
    /**
     * @return the menuIndex
     */
    public static Integer getMenuIndex() {
        return menuIndex;
    }
    /**
     * @param menuIndexInp the menuIndex to set
     */
    public static void setMenuIndex(Integer menuIndexInp) {
        menuIndex = menuIndexInp;
    }
    //this code may be merged into single subroutine
    public static void highlightNextItem() {
        highlightMainMenu(regularColor);
        menuIndex++;
        if (menuIndex>=menuItems.length) {
            menuIndex=0;
        }
        highlightMainMenu(highlightedColor);
    }
    public static void highlightPreviousItem() {
        highlightMainMenu(regularColor);
        menuIndex--;
        if (menuIndex<0) {
            menuIndex=menuItems.length-1;
        }
        highlightMainMenu(highlightedColor);
    }
    
    /**
     * Call processing routines for selected Menu item
     */
    public static void processCurrentMenuItem() {
        if (menuIndex==menuItems.length-1) { //quit game menu item
            //passing global signal. it will dispatched/activated in main game cycle. 
            //Exiting right here is a bad practice, although saves some CPU 
            //System.out.println("sent quitgame signal");
            sciroguelike2.algodata.GameStateResolver.changeGameState(sciroguelike2.algodata.GameStates.QUITGAME);
        }
        if (menuIndex==0) { //'new game' menu item
            
            sciroguelike2.algodata.GameStateResolver.changeGameState(sciroguelike2.algodata.GameStates.MAINGAME);
        }
    }
}
