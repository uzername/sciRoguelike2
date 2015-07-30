package sciroguelike2.algomaps;

import sciroguelike2.datastructs.AllMaps;
import sciroguelike2.datastructs.FragmentMap;
import sciroguelike2.datastructs.MapChunk;
import sciroguelike2.datastructs.MapTile;
import sciroguelike2.algodata.GeneralCoreData;
import sciroguelike2.algodata.PrototypeCollector;
import java.util.ArrayList;
//import org.boon.*;

//see http://tutorials.jenkov.com/java-json/index.html
/**
 * this class is used to load map data from files
 * logical components: 
 * load single chunk (append/replace), 
 * load partial (small fragment of the chunk, replace only),
 * load several chunks (append)
 * @author ivan
 */
public class MapLoader {
    /**
     * load single chunk from file and append it to map
     * @param filename - name of the file to look up
     * @param chunkID - there might be several chunks declared in file. 
     */
    public static void appendSingleChunk(String filename, Integer chunkID) {
        
    }
    /**
     * load all chunks from file and append them to map (ordered by their "chunk_id" inside file)
     * @param filename 
     */
    public static void appendSeveralChunks(String filename) {
        
    }
    /**
     * (re)place tiles with previously defined fragment of map - Partial. 
     * Partial may be spanned across the several map chunks 
     * @param filename
     * @param partialID
     * @param chunkNum
     * @param fragmentNum
     */
    public static void setPartial (String filename, Integer partialID, 
            Integer chunkNum, Integer fragmentNum) {
        
    }
}
