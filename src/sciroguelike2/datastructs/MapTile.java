/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sciroguelike2.datastructs;

/**
 * a single tile of map
 * @author ivan
 */
public class MapTile {
    /**
     * each map tile has certain common characteristics, such as character,
     * color, passable and so on. I've an idea: why to store this data in each mapTileObject
     * let's store this constant data ("knowledge") in static array and give here only index
     */
    public Integer prototypeIndex;
    public Double durability=-1.0; //current durability. indestructible by default.
    
    public MapTile(Integer initialPrototype) {
        prototypeIndex = initialPrototype;
    }
}
