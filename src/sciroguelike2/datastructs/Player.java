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
    public static Integer playerMainColor=0x11BBCC;
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
}
