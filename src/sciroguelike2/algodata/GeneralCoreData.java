package sciroguelike2.algodata;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sciroguelike2.SciRoguelike2;
import squidpony.squidgrid.gui.SquidPanel;
import squidpony.squidgrid.gui.TextCellFactory;

/**
 * Contains general parameters in static class, available in whole project
 * also has some initialization
 * @author ivan
 */
public class GeneralCoreData {
    //by now storing current map chunk here. later it should be moved to Player class
    public static Integer currentMapChunkIndex=0;    
    public static Integer ChunkWidth;
    public static Integer ChunkHeight;
    public static void defineChunkSize(Integer inpWidth, Integer inpHeight) {
        ChunkWidth = inpWidth;
        ChunkHeight = inpHeight;
    }
    //ChunkWidth and ChunkHeight are the sizes of internal array for storing mapdata
    //screenWidth, screenHeight are the sizes of our actual display, passed to ConsoleInterface
    public static Integer screenWidth = 80;
    public static Integer screenHeight = 25;
    /**
     * number of chunks in FragmentMap
     */
    public static Integer numberChunks = 9;
    /**
     * number of fragments in AllMaps
     */
    public static Integer numberFragments = 9;
    //===Graphics===
    public static String currentFontName = "resources/TerminalVector.ttf";
    public static SquidPanel foregroundpane; //pane where drawing all symbols is done
    public static SquidPanel backgroundpane;
    
    private static Font getFont() throws FontFormatException, IOException {
            Font myFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, GeneralCoreData.class.getClassLoader().getResourceAsStream(currentFontName)); 
            return myFont;
    }
    
    public static void initGraphics () {
        Font usedFont = null;
        try {
            usedFont = getFont();
        } catch (FontFormatException ex) {
            Logger.getLogger(GeneralCoreData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneralCoreData.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(usedFont);
        Font derivedFont = new Font("TerminalVector", java.awt.Font.PLAIN, 12);
        TextCellFactory textLayerFF = new TextCellFactory();
        textLayerFF.font(derivedFont);
        textLayerFF.leftPadding(1);
        foregroundpane = new SquidPanel(screenWidth, screenHeight, textLayerFF, null);
        backgroundpane = new SquidPanel(screenWidth, screenHeight, textLayerFF, null);
        
        foregroundpane.refresh(); backgroundpane.refresh();
    }
    
}
