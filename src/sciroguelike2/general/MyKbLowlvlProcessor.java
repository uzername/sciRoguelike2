package sciroguelike2.general;

import java.awt.event.KeyEvent;
import squidpony.squidgrid.gui.SquidKey;
/**
 * extends the squidlib's keyboard processor. Adds some 'reject' feature.
 * @author ivan
 */
public class MyKbLowlvlProcessor extends SquidKey{
    /**
     * if set to true - will reject/accept keyboard input taking in account Busy state
     * if set to false - will behave as normal SquidKey listener, always accepting input
     */
    private Boolean rejectMode;
    private Boolean queueBusy;
    /**
     * 
     * @param blockOnEmpty - the same as Squidkey
     * @param type - the same as in Squidkey
     * @param inpReject - is it OK to throw away the input if queue is busy.
     */
    public MyKbLowlvlProcessor(boolean blockOnEmpty, CaptureType type, Boolean inpReject) {
        super(blockOnEmpty, type);
        rejectMode = inpReject;
        queueBusy = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if (rejectMode) {
            if (!getQueueBusy()) {
                super.keyTyped(e);
            }
        } else {
            super.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (rejectMode) {
            if (!getQueueBusy()) {
                super.keyPressed(e);
            }
        } else {
            super.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (rejectMode) {
            if (!getQueueBusy()) {
                super.keyReleased(e);
            }
        } else {
            super.keyReleased(e);
        }
    }

    /**
     * @return the queueBusy
     */
    public Boolean getQueueBusy() {
        return queueBusy;
    }

    /**
     * @param queueBusy the queueBusy to set
     */
    public void setQueueBusy(Boolean queueBusy) {
        this.queueBusy = queueBusy;
    }
    
}
