/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sciroguelike2.datastructs;

import java.util.ArrayList;

/**
 * structure for holding the smallest map segment
 * @author ivan
 */
public class MapChunk {
    //public ArrayList<MapTile> ChunkMapContainer = new ArrayList<>(general.algodata.GeneralParam.ChunkHeight*general.algodata.GeneralParam.ChunkWidth);
    /**
     * stores MapTile objects. The most important container to store lvl terrain data
     */
    public ArrayList<ArrayList<MapTile>> ChunkMapContainer = null;
    /**
     * we need to fill ChunkMapContainer which stores MapTile objects if uniformTile is null.
     */
    public void fillChunkMapContainer() {
        ChunkMapContainer = new ArrayList<>();
        for (int i=0; i<sciroguelike2.algodata.GeneralCoreData.ChunkHeight; i++) {
            ArrayList<MapTile> singleTileRow = new ArrayList();
            for (int j=0; j<sciroguelike2.algodata.GeneralCoreData.ChunkWidth; j++) {
                singleTileRow.add(null);
            }
            ChunkMapContainer.add(singleTileRow);
        }
    }
    public void simpleInitChunkMapContainer() {
        ChunkMapContainer = new ArrayList<>();
    }
    //to save memory we may store uniformly filled MapChunk in a different manner
    //(for chunks which are filled with one MapTile, such as sky chunk for example)
    /**
     * if uniformTile is not null then this MapChunk is filled only with this MapTile
     * and ChunkMapContainer is ignored
     */
    public MapTile uniformTile = null; 
}
