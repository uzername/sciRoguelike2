/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sciroguelike2.datastructs;

import java.util.ArrayList;

/**
 * Single map fragment. Consists of 8 MapChunks. By the moment of 27oct2015, it's 
 * the biggest subunit of continuous map. 
 * @author ivan
 */
public class FragmentMap {
    private final int IDLength = 10;
    public String ID; //the random ID
    /** contains  reference to nearby FragmentMap objects; some of it might be null.
     * during generation we can refer to character's current FragmentMap. 
     * We fill in one neighbour of new FragmentMap and one - of current FragmentMap.
     * Use such indexes for saving a certain directions:  NW:1, N:2, NE:3, W:0, E:4, SW:7, S:6, SE:5
     */
    public ArrayList<String> neighbours; 
    public ArrayList<MapChunk> fragmentContainer = new ArrayList<>(sciroguelike2.algodata.GeneralCoreData.numberChunks);
    
}
