package sciroguelike2.algomaps;

import java.awt.Color;
import sciroguelike2.datastructs.Player;
import sciroguelike2.algodata.GeneralCoreData;


/**
 * class for displaying/rendering map on page. uses routines from squidlib
 * @author ivan
 */
public class MapDisplay {
    private static Integer playerPositionX =new Integer(GeneralCoreData.screenWidth/2) + 1;
    private static Integer playerPositionY =new Integer(GeneralCoreData.screenHeight/2) + 1;
    /**
     * get the detailed description of startChunk and endChunk, both as startFragment/endFragment.
     * used in renderMap
     */
    private static void detailDrawPositions() {
        
    }
    /**
     * render mapdata (one or several mapchunks). Used to draw terrain 
     * Making it static too, it is used in many places in project
     * The epic method. Probably should split it to several
     */
    public static void renderMap() {
        //we have index of current mapchunk in player's class
        //draw this mapchunk, centering it on screen.
        //System.out.println("===Starting draw map===");
        Integer playerFragmentMapIndex=sciroguelike2.datastructs.Player.getPlayer().getCurrMapFragment();
        Integer playerChunkMapIndex=sciroguelike2.datastructs.Player.getPlayer().getCurrMapChunk();
        Integer playerChunkMapXCoord = sciroguelike2.datastructs.Player.getPlayer().getMapChunkXCoord();
        Integer playerChunkMapYCoord = sciroguelike2.datastructs.Player.getPlayer().getMapChunkYCoord();
        //defining the coordinates to draw. Screen may contain map tiles from  
        //different Fragments and Chunks
        Integer startFragment = playerFragmentMapIndex;
        Integer endFragment = playerFragmentMapIndex;
        Integer startChunk = playerChunkMapIndex;
        Integer endChunk = playerChunkMapIndex;
        //coordinates of start and finish area to display. 
        //Must be used with context of corresponding chunk and even a fragment
        Integer startX=playerChunkMapXCoord-playerPositionX; Integer startY=playerChunkMapYCoord-playerPositionY;
        Integer endX=playerChunkMapXCoord+playerPositionX; Integer endY=playerChunkMapYCoord+playerPositionY;
        java.util.ArrayList<Integer> startMarker = null; java.util.ArrayList<Integer> endMarker = null;
        Integer mvmntDirection=0;
        //modifying chunk markers
                //left start chunk
        if ((startX<0)) { 
                if (startY>GeneralCoreData.ChunkHeight) { //lower left chunk
                    //this chink is located to southwest
                    mvmntDirection = 8;
                }
                if (startY<0) { //upper left chunk 
                    //this chunk is located norhwest
                    mvmntDirection = 2;
                }
                if ((startY>=0)&&(startY<=GeneralCoreData.ChunkHeight)) { //just leftmost chunk
                    //this chunk is located to west
                    mvmntDirection = 1;
                }
                startMarker = MapProcessor.getNeighbourMapArea(startChunk,startFragment,mvmntDirection);
                
        }
                //right start chunk - this variant is geometrically impossible. Just refer to graph model
        if (startX>GeneralCoreData.ChunkWidth) {
            throw new ArithmeticException("Display out of bounds. startX>ChunkWidth");
        }
                //central (straight) start chunk
        if ((startX<=GeneralCoreData.ChunkWidth)&&(startX>=0)) {
            // lower (straight) start chunk
            if (startY>GeneralCoreData.ChunkHeight) {
                //this chunk is located to north. mvmntDirection=3 was before...
                mvmntDirection=7;
            }
            //higher (straight) start chunk
            if (startY<0) {
                //this chunk is located to south. mvmntDirection=7
                mvmntDirection=3;
            }
            //nothing to change. Standing in place
            if ((startY>=0)&&(startY<=GeneralCoreData.ChunkHeight)) { //the chunk remains the same. nothing to change
                mvmntDirection=0;
            }
            startMarker = MapProcessor.getNeighbourMapArea(startChunk, startFragment, mvmntDirection);
        }
            //System.out.println("start MovementDirection = "+mvmntDirection+"; startMarker="+startMarker.toString());
        //checking end chunk markers
                //left end chunk - this variant is geometrically impossible
        if ((endX<0)) { 
                 throw new ArithmeticException("Display out of bounds. endX<0");
        }
                //right end chunk
        if (endX>GeneralCoreData.ChunkWidth) {
            if (endY>GeneralCoreData.ChunkHeight) { // lower right end chunk
                mvmntDirection=6;
            }
            if (endY<0) { //higher (right) end chunk
                mvmntDirection=4;
            }
            if ((endY>=0)&&(endY<=GeneralCoreData.ChunkHeight)) { //straight right end chunk
                mvmntDirection=5;
            }
            endMarker = MapProcessor.getNeighbourMapArea(endChunk, endFragment, mvmntDirection);
        }
                //central (straight) start chunk
        if ((endX<=GeneralCoreData.ChunkWidth)&&(endX>=0)) {
            if (endY>GeneralCoreData.ChunkHeight) { // lower (straight) end chunk
                mvmntDirection=7;
            }
            if (endY<0) { //higher (straight) start chunk
                mvmntDirection=3;
            }
            if ((endY>=0)&&(endY<=GeneralCoreData.ChunkHeight)) { //the chunk remains the same. nothing to change
                mvmntDirection=0;
            }
            endMarker = MapProcessor.getNeighbourMapArea(endChunk, endFragment, mvmntDirection);
        }
        //System.out.println("end MovementDirection="+mvmntDirection+"; endMarker="+endMarker.toString());
        //checking boundaries and adjusting display positions
        if (startX<0) {
            startX=GeneralCoreData.ChunkWidth+startX;        
        }
        if (endX>GeneralCoreData.ChunkWidth) {
            endX=GeneralCoreData.ChunkWidth-endX;
        }
        if (startY<0) {
            startY=GeneralCoreData.ChunkHeight+startY;
        }
        if (endY>GeneralCoreData.ChunkHeight) {
            endY=GeneralCoreData.ChunkHeight-endY;
        }
        //draw the stuff, each symbol of display screen
        //System.out.println("Printing map");
        //System.out.println("(startX="+startX+"; startY="+startY+"); endX="+endX+"; endY="+endY+"; start_chunk="+startMarker.get(0)+"; end_chunk="+endMarker.get(0));
        /*
        ++++++ Map display algorithm ++++++
        we have relative coordinates (startX, startY) and (endX, endY). These are relative to starting/ending 
        MapChunk and MapFragment. Starting mapChunk/mapFragment are in startFragment. This info is obtained from getNeighbourMapArea
        Ending mapChunk/mapFragment are in endFragment. We also have Player's current Fragment and Chunk.
        While drawing map, we start from (startX, startY, [mapChunk, mapFragment]). We move from left to right, crossing some borders of mapFragments/mapChunks.
        We take data from current-cycle MapChunk and draw these on screen
        */      

        Integer xCycle=startX; Integer yCycle=startY; /*we also have endX and endY*/
        Integer stord_chunk=startMarker.get(0); Integer currentCycleFragmentCoord=startMarker.get(1);        
        for (int j=0; j<GeneralCoreData.screenHeight; j++) {
            Integer cycle_chunk=stord_chunk;
            xCycle=startX;
            for (int i=0; i<GeneralCoreData.screenWidth; i++) {
                Integer curTilePrototype = sciroguelike2.algomaps.MapProcessor.getMapTileByCoordinates(currentCycleFragmentCoord, cycle_chunk, xCycle, yCycle).prototypeIndex;
                //System.out.println("("+xCycle+";"+yCycle+")");
                GeneralCoreData.foregroundpane.put(i, j, 
                        sciroguelike2.algodata.PrototypeCollector.mapTilesData.get( curTilePrototype ).mapSymbol, 
                        sciroguelike2.algodata.PrototypeCollector.mapTilesData.get( curTilePrototype ).symColor);
                GeneralCoreData.backgroundpane.put(i, j, 
                        sciroguelike2.algodata.PrototypeCollector.mapTilesData.get( curTilePrototype ).symBgColor);
                xCycle++;
                
                if (xCycle>=GeneralCoreData.ChunkWidth) {
                    java.util.ArrayList<Integer> newChunkFragmentData = MapProcessor.getNeighbourMapArea(cycle_chunk, currentCycleFragmentCoord, 5);
                    cycle_chunk = newChunkFragmentData.get(0);
                    currentCycleFragmentCoord = newChunkFragmentData.get(1);
                    xCycle = 0;
                }
            }            
            yCycle++; xCycle = startX; cycle_chunk=stord_chunk;
            if (yCycle>=GeneralCoreData.ChunkHeight) {
                java.util.ArrayList<Integer> newChunkFragmentData = MapProcessor.getNeighbourMapArea(stord_chunk, currentCycleFragmentCoord, 7);
                stord_chunk = newChunkFragmentData.get(0);
                currentCycleFragmentCoord = newChunkFragmentData.get(1);
                yCycle=0;
            }
        }
        //System.out.println("done drawing");
                //GeneralCoreData.foregroundpane.refresh();
                //GeneralCoreData.backgroundpane.refresh();        
    }
    /**
     * render only characters on map (including monsters, NPCs and Player)
     */
    public static void renderCharacters() {
        //render player
        //general.algomaps.MapDisplay.globalCsi.print(playerPositionX, playerPositionY, Player.playerMainCharacter, Player.playerMainColor);
        //    general.algomaps.MapDisplay.globalCsi.refresh();
        GeneralCoreData.foregroundpane.put(playerPositionX, playerPositionY, Player.getPlayer().playerMainCharacter, Player.getPlayer().playerFgColor );
        GeneralCoreData.backgroundpane.put(playerPositionX, playerPositionY, Player.getPlayer().playerBkgColor);
        
    }
    /**
     * We need to clear the whole pane not that often, due to blinking and minor lags
     * clear only when needed!
     */
    public static void clearAll() {
        GeneralCoreData.foregroundpane.erase();
        GeneralCoreData.backgroundpane.erase();
    }
    /**
     * We need to refresh the whole pane not that often, due to blinking and minor lags
     * refresh only when needed!
     */
    public static void refreshAll() {
        GeneralCoreData.foregroundpane.refresh();
        GeneralCoreData.backgroundpane.refresh();
    }
    
    /**
     * This repeating fragment is used in Player class, movement sector.
     */
    public static void generalizedDraw() {
        if (sciroguelike2.algodata.GeneralCoreData.drawing==false) {
            System.out.println("redraw called");
            sciroguelike2.algodata.GeneralCoreData.drawing=true;
            sciroguelike2.algomaps.MapDisplay.renderMap();
            sciroguelike2.algomaps.MapDisplay.renderCharacters();
            sciroguelike2.algomaps.MapDisplay.refreshAll(); 
                sciroguelike2.algodata.GeneralCoreData.drawing=false;
            }
    }
}
