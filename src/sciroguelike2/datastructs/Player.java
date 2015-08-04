package sciroguelike2.datastructs;
/**
 * player entity. The main Character of the game. Player is only one here.
 * Crude singleton
 * @author ivan
 */
public class Player {
    //current location of player
    private static Integer currMapChunk;
    private static Integer currMapFragment;
    private static Integer mapChunkXCoord;
    private static Integer mapChunkYCoord;
    
    //player display parameters
    public static java.awt.Color playerFgColor=java.awt.Color.WHITE;
    public static java.awt.Color playerBkgColor=java.awt.Color.BLACK;
    public static Character playerMainCharacter = '@';
    
    private static boolean initialized=false;
    private static Player playerEntity=null;
    //retrieve coordinates of player
    public static Integer getCurrMapChunk() {
        return currMapChunk;
    }
    public static Integer getCurrMapFragment() {
        return currMapFragment;
    }
    public static Integer getMapChunkXCoord() {
        return mapChunkXCoord;
    }
    public static Integer getMapChunkYCoord() {
        return mapChunkYCoord;
    }
    private Player() {
        
    }
    public static Player getPlayer() {
        if (initialized==false) {
            playerEntity = new Player();
            initialized = true; 
            currMapChunk=0; currMapFragment=0;
            mapChunkXCoord=0; mapChunkYCoord=0;
        }
        return playerEntity;
    }
    
    /**
     * move player up on map. key handler! Should move it somewhere, but I don't know where
     * Changes player chunk coord, position and player fragment
     */
    public static void movePlayerUp() {
        if (mapChunkYCoord==0) return;
        //recalculate coordinates
        mapChunkYCoord--;
        //redraw
        sciroguelike2.algomaps.MapDisplay.renderMap();
        sciroguelike2.algomaps.MapDisplay.renderCharacters();
        System.out.println("character goes up!");
    }
    public static void movePlayerDown() {
        if (mapChunkYCoord==sciroguelike2.algodata.GeneralCoreData.ChunkHeight-1) return;
        //recalculate coordinates
        mapChunkYCoord++;
        //redraw
        sciroguelike2.algomaps.MapDisplay.renderMap();
        sciroguelike2.algomaps.MapDisplay.renderCharacters();
        System.out.println("character goes down!");
    }
    public static void movePlayerRight() {
        if (mapChunkXCoord==sciroguelike2.algodata.GeneralCoreData.ChunkWidth-1) return;
        //recalculate coordinates
        mapChunkXCoord++;
        //redraw
        sciroguelike2.algomaps.MapDisplay.renderMap();
        sciroguelike2.algomaps.MapDisplay.renderCharacters();
        System.out.println("character goes right!");
    }
    public static void movePlayerLeft() {
        if (mapChunkXCoord==0) return;
        //recalculate coordinates
        mapChunkXCoord--;
        //redraw
        sciroguelike2.algomaps.MapDisplay.renderMap();
        sciroguelike2.algomaps.MapDisplay.renderCharacters();
        System.out.println("character goes left!");
    }
}
