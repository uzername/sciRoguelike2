package sciroguelike2.general;

import sciroguelike2.algomaps.MapDisplay;
import sciroguelike2.algomaps.MapProcessor;
import sciroguelike2.algomaps.MapGenerator;

/**
 * Another class with static-only routines, 
 * which handles:
 * <ol>
 *  <li>'new game' data generation (initial map generation, character generation); </li>
 *  <li>'save game' data dumping;</li>
 *  <li>'load game' data retrieving from save;</li>
 * </ol>
 * @author ivan
 */
public class AlgorithmicProcessor {
    public static void newGameDataGeneration() {
        //init (generate and load) map (in MapProcessor)
            MapProcessor.initMapProcessor();
            MapGenerator.testFillMap();
        //init player (in Player.java)
                //no need for this here, getPlayer initializes Player internal record by itself 
                //and it is called many times, especially in drawmap routines
                //it's good to make adjustments to Player entity here
            sciroguelike2.datastructs.Player.getPlayer(); 
        //draw map and everything in it.
            MapDisplay.clearAll();
            MapDisplay.renderMap();
            MapDisplay.renderCharacters();
            MapDisplay.refreshAll();
    }
    public static void loadGameDataRetrieval() {
        
    }
}
