package sciroguelike2.datastructs;

import java.util.ArrayList;

/**
 * Contains current map of continuous game world
 * @author ivan
 */
public class AllMaps {
    //size of AllMaps is limited to ((MapChunkWidth*MapChunkHeight)*NumberChunks)*NumberFragments) tiles
    //it's quite few, but this array may be loaded from file at runtime
    public ArrayList<FragmentMap> generalMap = new ArrayList<>(sciroguelike2.algodata.GeneralCoreData.numberFragments);
}
