package sciroguelike2;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import sciroguelike2.general.KbConfig;
import sciroguelike2.general.MyKbLowlvlProcessor;
import squidpony.SColor;
import squidpony.squidgrid.gui.SquidKey;
import squidpony.squidgrid.gui.SquidPanel;
import squidpony.squidgrid.gui.TextCellFactory;
import squidpony.squidmath.RNG;
/**
 * Take two of my SciRoguelike. 
 * @author ivan
 */
public class SciRoguelike2 {
    
    public static /*MyKbLowlvlProcessor*/SquidKey  keyListener;

    public static void main(String[] args) {
        
        /*
        RNG ran = new RNG();
        SColor color;
        for (int x=0; x<panelWidth; x++) {
            for (int y=0; y<panelHeight; y++) {
                color = ran.nextBoolean() ? SColor.BLACK : SColor.WHITE;
                char c = (char) ran.between(10, 128);
                foregroundpane.put(x, y, c, color);
                color = ran.nextBoolean() ? SColor.GREEN : SColor.RED;
                backgroundpane.put(x,y,color);
            }
        }
        */
        sciroguelike2.algodata.GeneralCoreData.defineChunkSize(127, 127);
        keyListener = new MyKbLowlvlProcessor(false, SquidKey.CaptureType.DOWN,Boolean.TRUE);
        sciroguelike2.algodata.GeneralCoreData.initGraphics();
        
        JFrame frame = new JFrame("SquidLib Example");
        JLayeredPane layer = new JLayeredPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(SColor.BLACK);
        layer.setLayer(sciroguelike2.algodata.GeneralCoreData.backgroundpane, JLayeredPane.DEFAULT_LAYER);
        layer.setLayer(sciroguelike2.algodata.GeneralCoreData.foregroundpane, JLayeredPane.PALETTE_LAYER);
        layer.add(sciroguelike2.algodata.GeneralCoreData.backgroundpane);
        layer.add(sciroguelike2.algodata.GeneralCoreData.foregroundpane);
        layer.setPreferredSize(sciroguelike2.algodata.GeneralCoreData.backgroundpane.getPreferredSize());
        layer.setSize(sciroguelike2.algodata.GeneralCoreData.backgroundpane.getPreferredSize());
        frame.add(layer);
        frame.addKeyListener(keyListener);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        sciroguelike2.algodata.GeneralCoreData.drawing = false;
        
        //collect Kb handling configuration
        KbConfig.getKbConfig().defineKeysInCode();
        
        sciroguelike2.menugame.MainMenu.displayMainMenu();
        //main game cycle
        
        (new Thread(new Runnable() {
            // upd is used to determine if enough time has passed between attempts to redraw. This tries to update at
            // about 15 frames per second, which is more than enough for a text-based game.
                //private int upd = (int) ((System.currentTimeMillis() / 65.0) % 64000);
            @Override
            public void run() {
                // this runs in a non-UI thread, and doesn't directly change the UI -- only the lightness array.
                // this thread can be interrupted by the application closing, so we should terminate the loop then.
                while ( (!Thread.interrupted())&&(sciroguelike2.algodata.GameStateResolver.getCurrGameState()!=sciroguelike2.algodata.GameStates.QUITGAME) ) {
                    // upd gets assigned a value every iteration of the loop, but if very little time has passed, then
                    // the value won't be any different. this is important because upd needs to be different from
                    // demo.counter for enough time to be considered to have passed (you get graphical glitches from
                    // trying to render too frequently, or while something is being changed, with Swing).
                        //upd = (int) ((System.currentTimeMillis() / 65.0) % 64000);
                    // if we are currently drawing, we do not want to suddenly jump in and render again.
                    // if there is input queued in the keyListener, then we want to resolve that and not go into the
                    // else if block below.
                    //System.out.println(keyListener.hasNext());
                    if (keyListener.hasNext()) {
                        // again, we want to make sure the times are different enough. demo.counter is updated in the
                        // else if block below, and nowhere else, so if input is queued, the counter won't change, but
                        // upd will. it won't be longer than 65 milliseconds before this runs.
                        /*
                        if (demo.counter != upd) {
                            // this gets the next queued input, and uses it to process movement or quitting.
                            demo.handle(demo.keyListener.next());
                            // redraw with the changed player position (if the player moved) and FOV.
                            demo.redraw();
                        }
                        */
                        keyListener.setIgnoreInput(true);
                        //oldschool kb handling
                        //sciroguelike2.general.ContextKbProcessor.performAndChooseAction(keyListener.next());
                        sciroguelike2.general.ContextKbProcessor.fancyPerformAndChooseAction(keyListener.next());
                        keyListener.setIgnoreInput(false);
                    }
                    // this needs the times to be different enough as well.
                    //else if (!demo.drawing && demo.counter != upd) {
                        // counter is set to the current value of upd so we won't redraw again too soon.
                            //demo.counter = upd;
                        // redraw with the new lightness.
                        //demo.redraw();
                    //}
                }
                System.out.println("That's all folks!");
                System.exit(0); //hit 'em with the biggest hammer
            }
        // threads need to be started.
        })).start();
        
    }
    
}
