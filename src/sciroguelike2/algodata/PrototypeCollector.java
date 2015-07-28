/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sciroguelike2.algodata;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Load all game object prototypes, store these in arrays
 * @author ivan
 */
public class PrototypeCollector {

    /**
     * an internal cache for all kinds of MapTiles. 
     * By design we are not sure what kinds of maptiles we'll have
     */
    public static ArrayList<MapTilePrototype> mapTilesData = new ArrayList<>();
    /**
     * Load statically data about map tiles
     */
    public static void loadMapTilesData() {
        mapTilesData.add(new MapTilePrototype(" ".charAt(0), Color.red, Color.blue, "empty"));
        mapTilesData.add(new MapTilePrototype(".".charAt(0), Color.red, Color.blue, "floor"));
        MapTilePrototype wallPrototype = new MapTilePrototype("#".charAt(0), Color.white, Color.blue, "wall");
        for (int i=0; i<wallPrototype.passSpeed.size(); i++) {
            wallPrototype.passSpeed.set(i, new Float(0.0)); 
        }
        for (int i=0; i<wallPrototype.transparency.size(); i++) {
            wallPrototype.transparency.set(i, new Float(0.0)); 
            }
        mapTilesData.add(wallPrototype);
    }
    public static void loadMapTilesDataFromFile(String filePath) {
        
    }
}
