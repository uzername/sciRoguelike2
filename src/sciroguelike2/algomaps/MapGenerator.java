package sciroguelike2.algomaps;

import sciroguelike2.datastructs.AllMaps;
import sciroguelike2.datastructs.FragmentMap;
import sciroguelike2.datastructs.MapChunk;
import sciroguelike2.datastructs.MapTile;
import sciroguelike2.algodata.GeneralCoreData;
import sciroguelike2.algodata.PrototypeCollector;
import java.util.ArrayList;

/**
 * this class is used to generate dynamic map data during runtime
 * @author ivan
 */
public class MapGenerator {
        public static void testFillMap() { //test routine of map
        //map chunks are initialized by this moment
        MapChunk firstMapChunk = sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(0);
                for (int j=0; j<(GeneralCoreData.ChunkHeight); j++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    for (int k=1; k<GeneralCoreData.ChunkWidth; k++) {
                        singleChunkRow.add(new MapTile(1));
                    }
                    firstMapChunk.ChunkMapContainer.set(j, singleChunkRow);
                }
        
        for (int i=1; i<=8; i++) {
            MapChunk theMapChunk =sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(i);
            System.out.println("TestFillMap: now working with Chunk #"+i+" : "+(theMapChunk==null ? "null!":"not null"));
                for (int j=0; j<(GeneralCoreData.ChunkHeight); j++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    singleChunkRow.add(new MapTile(2));
                    for (int k=1; k<GeneralCoreData.ChunkWidth; k++) {
                        singleChunkRow.add(new MapTile(2));
                    }
                    theMapChunk.ChunkMapContainer.set(j, singleChunkRow);
                }
        }
    }
}
