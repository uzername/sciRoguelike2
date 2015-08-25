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
        MapChunk firstMapFragment = sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(0);
                for (int l=0; l<(GeneralCoreData.ChunkHeight); l++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    /*
                    for (int k=0; k<GeneralCoreData.ChunkWidth; k++) {
                        if (((k+1)%4==0)||(k%4==0)) {
                        singleChunkRow.add(new MapTile(2));
                        } else {singleChunkRow.add(new MapTile(1));}
                    }
                    */
                    if (l<=4) {singleChunkRow.add(new MapTile(1));} else {
                    singleChunkRow.add(new MapTile(2)); }
                    if ((l==0)||(l==GeneralCoreData.ChunkHeight-1)) {
                        for (int k=1; k<GeneralCoreData.ChunkWidth; k++) {
                            singleChunkRow.add(new MapTile(2));
                        }
                    } else {
                        if ((l%3==0)||( (l-1)%3==0 )) {
                            int k=1;
                            while (k<GeneralCoreData.ChunkWidth) {
                                singleChunkRow.add(new MapTile(2));
                                k++;
                                if (k>=GeneralCoreData.ChunkWidth) {break;}
                                singleChunkRow.add(new MapTile(2));
                                k++;
                                if (k>=GeneralCoreData.ChunkWidth) {break;}
                                singleChunkRow.add(new MapTile(1));
                                k++;
                                if (k>=GeneralCoreData.ChunkWidth) {break;}
                                singleChunkRow.add(new MapTile(1));
                                k++;
                                if (k>=GeneralCoreData.ChunkWidth) {break;}
                            }
                        } else {
                            for (int k=1; k<GeneralCoreData.ChunkWidth; k++) {
                                singleChunkRow.add(new MapTile(1));
                            }
                        }
                    }
                    singleChunkRow.add(new MapTile(2));
                    firstMapFragment.ChunkMapContainer.set(l, singleChunkRow);
                    //sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(0).ChunkMapContainer.set(j, singleChunkRow);
                }
                
        for (int i=1; i<=8; i++) {
            MapChunk theMapChunk =sciroguelike2.algomaps.MapProcessor.currentMapBuffer.generalMap.get(0).fragmentContainer.get(i);
            //System.out.println("TestFillMap: now working with Chunk #"+i+" : "+(theMapChunk==null ? "null!":"not null"));
                for (int j=0; j<(GeneralCoreData.ChunkHeight); j++) {
                    ArrayList<MapTile> singleChunkRow = new ArrayList<>();
                    singleChunkRow.add(new MapTile(1));
                    for (int k=1; k<GeneralCoreData.ChunkWidth; k++) {
                        singleChunkRow.add(new MapTile(1));
                    }
                    theMapChunk.ChunkMapContainer.set(j, singleChunkRow);
                }
        }
        
    }
}
