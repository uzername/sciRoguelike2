package sciroguelike2.datastructs;

import sciroguelike2.algodata.PrototypeCollector;

/**
 * player entity. The main Character of the game. Player is only one here.
 * Crude singleton. Contains: 
 * <ol>
 * <li>player movement routines</li> <li>other player routines</li>
 * <li>player characteristics: </li>
 * <div style="margin-left:10px;"> AlgoData parameters: Coordinates on map, etc.; Interface parameters: Color, Symbol </div>
 * </ol>
 * <p>
 * <strong>Player movement. </strong>
 * This class class contains primitive routines for player movement inside chunk. 
 * Crossing the border of chunk may lead to changing MapFragment or to changing the AllMapsArray (loading new MapFragments and MapChunks)
 * </p>
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
    /**
     * used to detect walls. In case of wall player stops without losing a turn.
     * This variable might be put out of use after implementing a turn-based system
     * (player should take action with a minimal timelapse)
     */
    private static boolean rejectPlayerMovement=false; 
    
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
    
    public static void printPlayerData() {
        System.out.println("coords in mapchunk: ("+mapChunkXCoord+";"+mapChunkYCoord+");"+
                "mapchunk number: "+currMapChunk+" Map fragment: "+currMapFragment);
    }
    /**
     * move player up on map. key handler! Should move it somewhere, but I don't know where
     * Changes player chunk coord, position and player fragment
     */
    public static void movePlayerUp() {
        
        if (mapChunkYCoord<=0)  {
            //character stepped from the current mapChunk
            System.out.println("changing chunk while moving up");
            Integer mvmntDirection = 3; //moving north
            java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDirection);
            mapChunkYCoord=sciroguelike2.algodata.GeneralCoreData.ChunkHeight-1;
            currMapChunk = newMarker.get(0);
            System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
        } else {
        //recalculate coordinates
        mapChunkYCoord--;
        }
        //redraw
        sciroguelike2.algomaps.MapDisplay.generalizedDraw();
    }
    /**
     * move player down (to south).
     */
    public static void movePlayerDown() {
        
        if (mapChunkYCoord==sciroguelike2.algodata.GeneralCoreData.ChunkHeight-1) {
            //change chunk
            System.out.println("changing chunk while moving down");
            Integer mvmntDirection = 7; //moving south
            java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDirection);
            mapChunkYCoord=0;
            currMapChunk = newMarker.get(0);
            System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
            } else {
        //recalculate coordinates
        mapChunkYCoord++;
        //redraw
        //sciroguelike2.algomaps.MapDisplay.renderMap(); sciroguelike2.algomaps.MapDisplay.renderCharacters(); sciroguelike2.algomaps.MapDisplay.refreshAll();
        }
        sciroguelike2.algomaps.MapDisplay.generalizedDraw();
    }
    public static void movePlayerRight() {
        if (mapChunkXCoord>=sciroguelike2.algodata.GeneralCoreData.ChunkWidth-1) {
            System.out.println("changing chunk while moving right");
            Integer mvmntDirection = 5; //moving north
            java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDirection);
            currMapChunk = newMarker.get(0);
            mapChunkXCoord=0;
            System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
            } else {
        //recalculate coordinates
        mapChunkXCoord++;
        
    }
        //redraw
        //sciroguelike2.algomaps.MapDisplay.renderMap();
        //sciroguelike2.algomaps.MapDisplay.renderCharacters();
        //sciroguelike2.algomaps.MapDisplay.refreshAll();
        try {
            sciroguelike2.algomaps.MapDisplay.generalizedDraw();
        } catch (Exception e) {
            printPlayerData();
            e.printStackTrace();
        }//redraw
        //sciroguelike2.algomaps.MapDisplay.renderMap();
        //sciroguelike2.algomaps.MapDisplay.renderCharacters();
        //sciroguelike2.algomaps.MapDisplay.refreshAll();
        //System.out.println("character goes right!");
        //printPlayerData();
    }
    public static void movePlayerLeft() {
        if (mapChunkXCoord==0) {
            System.out.println("changing chunk while moving left");
            Integer mvmntDirection = 1; //moving west
            java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDirection);
            currMapChunk = newMarker.get(0);
            mapChunkXCoord=sciroguelike2.algodata.GeneralCoreData.ChunkWidth-1;
            System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
            } else {
        //recalculate coordinates
        mapChunkXCoord--;
        }
        //redraw always!
        try {
            sciroguelike2.algomaps.MapDisplay.generalizedDraw();
        } catch (Exception e) {
            printPlayerData();
            e.printStackTrace();
        }
        //System.out.println("character goes left!");
        //printPlayerData();
    }
    /**
     * some events may occur when player steps on a tile.
     * Also detect walls on player's way here. Appears to be a wrapper-over-wrapper
     * This routine is called from KbProcessing
     * calls utilities from ProcessTrigger class. 
     * @param mvmntDir movement direction:  NW:2, N:3, NE:4, W:1, E:5, SW:8, S:7, SE:6
     */
    public static void processMovementTrigger (Integer mvmntDir) {
        //saving parameters of player here.
        //we'll probably need to save some states here and pass them as arguments while processing trigger
        Integer testFragment = currMapFragment;
        Integer testChunk = currMapChunk;
        Integer testX = mapChunkXCoord;
        Integer testY = mapChunkYCoord;
        switch(mvmntDir) {
            case 3: { //trying to move north
                if (testY<=0)  {
                    //character stepped from the current mapChunk
                    java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDir);
                    testY=sciroguelike2.algodata.GeneralCoreData.ChunkHeight-1;
                    testChunk = newMarker.get(0);
                    testFragment = newMarker.get(1);
                    //testX remains the same
                } else {
                    testY=mapChunkYCoord-1;
                }
                break;
            }
            case 1: { //trying to move west
                
                if (testX==0) {
                    //System.out.println("changing chunk while moving left");
                    java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDir);
                    testChunk = newMarker.get(0);
                    testFragment = newMarker.get(1);
                    testX=sciroguelike2.algodata.GeneralCoreData.ChunkWidth-1;
                    //testY is not changing
                    //System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
                    } else {
                        //recalculate coordinates
                        testX = testX-1;
                    }
                
                break;
            }
            case 5: { //trying to move east
                
                if (testX>=sciroguelike2.algodata.GeneralCoreData.ChunkWidth-1) {
                    //System.out.println("changing chunk while moving right");
                    //Integer mvmntDirection = 5; //moving north
                    java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDir);
                    testChunk = newMarker.get(0);
                    testFragment = newMarker.get(1);
                    testX=0;
                    //System.out.println("Player is going to move to new Chunk and Fragment: "+newMarker.get(0)+";"+newMarker.get(1));
                    } else {
                        //recalculate coordinates
                        testX=testX+1;
                }
                
                break;
            }
            case 7: { //trying to move south
                
                if (testY==sciroguelike2.algodata.GeneralCoreData.ChunkHeight-1) {            
                    java.util.ArrayList<Integer> newMarker = sciroguelike2.algomaps.MapProcessor.getNeighbourMapArea(getCurrMapChunk(),getCurrMapFragment(), mvmntDir);
                    testY=0;
                    testChunk = newMarker.get(0);
                    testFragment = newMarker.get(1);
                } else {
                    //recalculate coordinates
                    testY++;
                }
                
                break;
            }
        }        
        sciroguelike2.algodata.triggers.GameTriggers theTrigger = sciroguelike2.algodata.PrototypeCollector.mapTilesData.get(sciroguelike2.algomaps.MapProcessor.getMapTileByCoordinates(testFragment, testChunk, testX, testY).prototypeIndex).tileTrigger;
        sciroguelike2.algodata.triggers.ProcessTrigger.getInstance().processTrigerRoutine(theTrigger, mvmntDir);
    }
    /**
     * wrapper for character movement
     * @param mvmntDir movement direction:  NW:2, N:3, NE:4, W:1, E:5, SW:8, S:7, SE:6
     */
    public static void generalizeMovement(Integer mvmntDir) {
        //retrieving maptile in the direction of character movement
        //making actual movement
        switch(mvmntDir) {
            case 3: { //trying to move north
                movePlayerUp();
                break;
            }
            case 1: { //trying to move west
                movePlayerLeft();
                break;
            }
            case 5: { //trying to move east
                movePlayerRight();
                break;
            }
            case 7: { //trying to move south
                movePlayerDown();
                break;
            }
        }
    }
}
