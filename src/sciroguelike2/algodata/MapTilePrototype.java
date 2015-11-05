package sciroguelike2.algodata;

import sciroguelike2.algodata.triggers.GameTriggers;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

/**
 * contains general info about each map tile
 * @author ivan
 */
public class MapTilePrototype {
    public Character mapSymbol;
    public Color symColor;
    public Color symBgColor;
    public String name;
    /**
     * each tile has its own trigger
     */
    public GameTriggers tileTrigger;
    /**
     * how fast the character is able to pass through this obstacle moving from different directions
     * (8 directions in total, in percents) TODO - add processing
     */
    public ArrayList<Float> passSpeed = new ArrayList<>();
    /**
     * Shows how this tile obstructs line of sight
     */
    public ArrayList<Float> transparency = new ArrayList<>();

    public MapTilePrototype(Character mapSymbol, Color symColor, Color symBgColor, String name, GameTriggers thisTrigger) {
        this.mapSymbol = mapSymbol;
        this.symColor = symColor;
        this.symBgColor = symBgColor;
        this.passSpeed.ensureCapacity(8);    //S, N, W, E; SW, NW, NE, SE 
        //make it fully passable by default
        this.tileTrigger = thisTrigger;
        /*
        this.passSpeed.set(0, new Float(100)); this.passSpeed.set(1, new Float(100)); this.passSpeed.set(2, new Float(100));
        this.passSpeed.set(3, new Float(100)); this.passSpeed.set(4, new Float(100)); this.passSpeed.set(5, new Float(100));
        this.passSpeed.set(6, new Float(100)); this.passSpeed.set(7, new Float(100));
        */
        for (int i=0; i<=7; i++) {
            this.passSpeed.add(new Float(100));
        }
        /*
        this.transparency.ensureCapacity(8); //S, N, W, E; SW, NW, NE, SE
        this.transparency.set(0, new Float(100)); this.transparency.set(1, new Float(100)); this.transparency.set(2, new Float(100));
        this.transparency.set(3, new Float(100)); this.transparency.set(4, new Float(100)); this.transparency.set(5, new Float(100));
        this.transparency.set(6, new Float(100)); this.transparency.set(7, new Float(100));
        */
         for (int i=0; i<=7; i++) {
            this.transparency.add(new Float(100));
        }
    }
    
}