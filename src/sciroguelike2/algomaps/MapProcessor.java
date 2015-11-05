package sciroguelike2.algomaps;

import sciroguelike2.datastructs.AllMaps;
import sciroguelike2.datastructs.FragmentMap;
import sciroguelike2.datastructs.MapChunk;
import sciroguelike2.datastructs.MapTile;
import sciroguelike2.algodata.GeneralCoreData;
import sciroguelike2.algodata.PrototypeCollector;
import java.util.ArrayList;
/**
 * contains algorithms that operate on maps. Due to complexity, 
 * map save/load algorithms might be moved to another module 
 * Uses classes AllMaps, FragmentMap, MapChunk. 
 * @author ivan
 */
public class MapProcessor {
    /**
     * contains the actual game map data
     */
    public static AllMaps currentMapBuffer;
    public static void initMapProcessor() {
        //load tileset data
        PrototypeCollector.loadMapTilesData();
        currentMapBuffer = new AllMaps(); //initializing currently used MapBuffer
        currentMapBuffer.generalMap.add(new FragmentMap()); //at first we have only one Fragment
        for (int i=0; i<=8; i++) {
            MapChunk tmpChunk = new MapChunk();
            tmpChunk.fillChunkMapContainer();
            currentMapBuffer.generalMap.get(0).fragmentContainer.add(tmpChunk);
        }
    }
    /**
     * use this nice subroutine instead of crawling through data structures every time
     * when you need to get a MapTile by a set of specific coordinates
     * (method was previously researched at MapDisplay)
     * @param fragmentCoord
     * @param chunkCoord
     * @param tileX
     * @param tileY
     * @return MapTile
     */
    public static MapTile getMapTileByCoordinates(Integer fragmentCoord, Integer chunkCoord, Integer tileX, Integer tileY) {
        MapTile tile2Return = null;
        try {
        tile2Return = sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(fragmentCoord).fragmentContainer.get(chunkCoord).ChunkMapContainer.get(tileX).get(tileY);
        } catch (java.lang.IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return tile2Return;
    }

    /**
     * finds a neighbour MapChunk. Service routine, used in MapDisplay routines
     * @param initialChunkCoord - index of MapChunk for which we need to get an index
     * @param initialFragmentCoord - index of MapFragment. A chunk may be contained in another map fragment
     * @param movementDirection - direction that shows how to find neighbour: 
     * NW:2, N:3, NE:4, W:1, E:5, SW:8, S:7, SE:6
     * @return ArrayList. First number contains a coordinate of MapChunk, second number contains number of MapFragment
     */
    public static java.util.ArrayList getNeighbourMapArea(Integer initialChunkCoord, Integer initialFragmentCoord, Integer movementDirection) {
        java.util.ArrayList<Integer> result = new java.util.ArrayList<>();
        result.add(initialChunkCoord); result.add(initialFragmentCoord);
        switch (movementDirection) {
            case 1: { //moving to west
                switch (initialChunkCoord) {
                    //not switching MapFragment
                    case 0: { result.set(0, 1); break; }
                    case 3: { result.set(0, 2); break; }
                    case 7: { result.set(0, 8); break; }
                    case 4: { result.set(0, 3); break; }
                    case 5: { result.set(0, 0); break; }
                    case 6: { result.set(0, 7); break; }
                    //switching MapFragment, to left
                    case 2: { 
                        result.set(0, 4); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break; 
                    }
                    case 1: {
                        result.set(0, 5); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break;
                    }
                    case 8: {
                        result.set(0, 6); 
                        switch (initialFragmentCoord) {
                            case 0: { result.set(1, 1); break;}
                            case 3: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 4: { result.set(1, 3); break;}
                            case 5: { result.set(1, 0); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 2: { //moving north-west
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 2: { //switching Fragment
                        result.set(0,6);
                        switchChunk=true;
                        break;
                    }
                    case 3: { //switching Fragment
                        result.set(0,8);
                        switchChunk=true;
                        break;
                    }
                    case 4: { //switching Fragment
                        result.set(0, 7);
                        switchChunk=true;
                        break;
                    }
                    case 1: { //switching Fragment
                        result.set(1, 4);
                        switchChunk=true;
                        break;
                    }
                    case 0: { //not switchng Fragment
                        result.set(0, 2);
                        break;
                    }
                    case 5: { //not switching Fragment
                        result.set(0, 3);
                        break;
                    }
                    case 8: { //switching Fragment
                        result.set(0, 5);
                        switchChunk=true;
                        break;
                    }
                    case 7: { //not switching Fragment
                        result.set(0, 1);
                        break;
                    }
                    case 6: { //not switching
                        result.set(0, 0);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk==true) {
                    switch (initialFragmentCoord) {
                            case 0: { result.set(1, 2); break;}
                            case 7: { result.set(1, 8); break;}
                            case 2:
                            case 3:
                            case 4:
                            case 1:
                            case 8: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 5: { result.set(1, 3); break;}
                            case 6: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 3: { //moving north
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 2: {
                        result.set(0,8);
                        switchChunk = true;
                        break;
                    }
                    case 3: {
                        result.set(0,7);
                        switchChunk = true;
                        break;
                    }
                    case 4: {
                        result.set(0,4);
                        switchChunk = true;
                        break;
                    }
                    
                    case 1: {
                        result.set(0, 2);
                        break;
                    }
                    case 0: {
                        result.set(0,3);
                        break;
                    }
                    case 5: {
                        result.set(0,4);
                        break;
                    }
                    case 8: {
                        result.set(0,1);
                        break;
                    }
                    case 7: {
                        result.set(0,0);
                        break;
                    }
                    case 6: {
                        result.set(0, 5);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk==true) {
                    switch (initialFragmentCoord) {
                            case 0: { result.set(1, 3); break;}
                            case 7: { result.set(1, 0); break;}
                            case 2:
                            case 3:
                            case 4: {throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);}
                            case 5: { result.set(1, 4); break;}
                            case 6: { result.set(1, 5); break;}
                            case 1: { result.set(1, 2); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 4: { //moving north-east
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 1: { //not switching chunk
                        result.set(0,3);
                        break;
                    }
                    case 2: { //switch chunk
                        result.set(0,7);
                        switchChunk=true;
                        break;
                    }
                    case 3: { //switch chunk
                        result.set(0, 6);
                        switchChunk=true;
                        break;
                    }
                    case 4: {
                        result.set(0, 8);
                        switchChunk=true;
                        break;
                    }
                    case 5: {
                        result.set(0,2);
                        switchChunk=true;
                        break;
                    }
                    case 6: {
                        result.set(0,1);
                        switchChunk=true;
                        break;
                    }
                    case 7: { //not switching chunk
                        result.set(0,5);
                        break;
                    }
                    case 8: { //not switching chunk
                        result.set(0,0);
                        break;
                    }
                    case 0: {//not switching chunk
                        result.set(0,4);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk == true) {
                    switch (initialFragmentCoord) {
                            case 1: { result.set(1, 3); break;}
                            case 2: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 3: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord);  }
                            case 4: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 5: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 6: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 7: { result.set(1, 5); break;}
                            case 8: { result.set(1, 0); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 5: { //moving east
                Boolean switchChunk=false;
                switch (initialChunkCoord) {
                    case 0: {
                        result.set(0,5);
                        break;
                    }
                    case 1: {
                        result.set(0,0);
                        break;
                    }
                    case 2: {
                        result.set(0,3);
                        break;
                    }
                    case 3: {
                        result.set(0,4);
                        break;
                    }
                    case 4: { //switch fragment
                        result.set(0,2);
                        switchChunk=true;
                        break;
                    }
                    case 5: { //switch fragment
                        result.set(0,1);
                        switchChunk=true;
                        break;
                    }
                    case 6: {
                        result.set(0,8);
                        switchChunk=true;
                        break;
                    }
                    case 7: {
                        result.set(0,6);
                        break;
                    }
                    case 8: {
                        result.set(0,7);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk == true) {
                    switch (initialFragmentCoord) {
                            case 0: { result.set(1, 5); break; }
                            case 1: { result.set(1, 0); break; }
                            case 2: { result.set(1, 3); break; }
                            case 3: { result.set(1, 4); break; }
                            case 4: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 5: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 6: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 7: { result.set(1, 6); break;}
                            case 8: { result.set(1, 7); break;}
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 6: { //moving south-east
                Boolean switchChunk=false;
                switch (initialChunkCoord) {
                    case 1: {
                        result.set(0,7);
                        break;
                    }
                    case 2: {
                        result.set(0,0);
                        break;
                    }
                    case 3: {
                        result.set(0,5);
                        break;
                    }
                    case 4: {
                        result.set(0,1);
                        switchChunk=true;
                        break;
                    }
                    case 5: {
                        result.set(0,8);
                        switchChunk=true;
                        break;
                    }
                    case 6: {
                        result.set(0,2);
                        switchChunk=true;
                        break;
                    }
                    case 7: {
                        result.set(0, 4);
                        switchChunk=true;
                        break;
                    }
                    case 8: {
                        result.set(0, 3);
                        switchChunk=true;
                        break;
                    }
                    case 0: {
                        result.set(0, 6);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk == true) {
                    switch (initialFragmentCoord) {
                            case 1: { result.set(1, 7); break; }
                            case 2: { result.set(1, 0); break; }
                            case 3: { result.set(1, 5); break; }
                            case 4: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 5: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 6: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 7: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 8: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 7: { //moving south
                Boolean switchChunk=false;
                switch (initialChunkCoord) {
                    case 1: {
                        result.set(0, 8);
                        break;
                    }
                    case 2: {
                        result.set(0, 1);
                        break;
                    }
                    case 3: {
                        result.set(0, 0);
                        break;
                    }
                    case 4: {
                        result.set(0, 5);
                        break;
                    }
                    case 5: {
                        result.set(0, 6);
                        break;
                    }
                    case 6: {
                        result.set(0, 4);
                        switchChunk=true;
                        break;
                    }
                    case 7: {
                        result.set(0, 3);
                        switchChunk=true;
                        break;
                    }
                    case 8: {
                        result.set(0, 2);
                        switchChunk=true;
                        break;
                    }
                    case 0: {
                        result.set(0, 7);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk == true) {
                    System.out.println("Switching chunk on movement from "+initialChunkCoord+" in direction: 7");
                    switch (initialFragmentCoord) {
                            case 0: { result.set(1, 7); break;}
                            case 1: { result.set(1, 8); break; }
                            case 2: { result.set(1, 1); break; }
                            case 3: { result.set(1, 0); break; }
                            case 4: { result.set(1, 5); break; }
                            case 5: { result.set(1, 6); break; }
                            case 6: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 7: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 8: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord+" while moving to "+movementDirection);
                            }
                        }
                }
                break;
            }
            case 8: { //moving south-west
                Boolean switchChunk = false;
                switch (initialChunkCoord) {
                    case 1: {
                        result.set(0, 6);
                        switchChunk = true;
                        break;
                    }
                    case 2: {
                        result.set(0, 5);
                        switchChunk=true;
                        break;
                    }
                    case 3: {
                        result.set(0, 5);
                        break;
                    }
                    case 4: {
                        result.set(0, 0);
                        break;
                    }
                    case 5: {
                        result.set(0, 7);
                        break;
                    }
                    case 6: {
                        result.set(0, 3);
                        switchChunk=true;
                        break;
                    }
                    case 7: {
                        result.set(0, 2);
                        switchChunk=true;
                        break;
                    }
                    case 8: {
                        result.set(0, 4);
                        switchChunk=true;
                        break;
                    }
                    case 0: {
                        result.set(0, 8);
                        break;
                    }
                    default: {
                       throw new IllegalArgumentException("Invalid Chunk:"+initialChunkCoord);
                            }
                }
                if (switchChunk == true) {
                    switch (initialFragmentCoord) {
                            case 1: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 2: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 3: { result.set(1, 1); break; }
                            case 4: { result.set(1, 0); break; }
                            case 5: { result.set(1, 7); break; }
                            case 6: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 7: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            case 8: { throw new UnsupportedOperationException("moving away from AllMaps array from "+initialFragmentCoord); }
                            default: {
                                throw new IllegalArgumentException("Invalid Fragment:"+initialFragmentCoord);
                            }
                        }
                }
                break;
            }
            case 0: { //nothing to evaluate, nowhere to move
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid direction for neighbour");
            }
        }
        return result;
    }
}
