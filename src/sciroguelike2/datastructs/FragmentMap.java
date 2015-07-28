/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sciroguelike2.datastructs;

import java.util.ArrayList;

/**
 * Single map fragment. Consists of 8 MapChunks
 * @author ivan
 */
public class FragmentMap {
    public ArrayList<MapChunk> fragmentContainer = new ArrayList<>(sciroguelike2.algodata.GeneralCoreData.numberChunks);
    
}
