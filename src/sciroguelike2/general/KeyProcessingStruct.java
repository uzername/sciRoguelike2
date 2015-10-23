package sciroguelike2.general;

/**
 * a single structure for key processing logic configuration. 
 * Each game action depends on key codes and is defined like:
 * [key code, [Shift state, Ctrl state, Alt state], GameState] -> [ActionCode]
 * And by action code we determine the action to perform at the moment.
 * So we need the associative array which is another class
 * @see sciroguelike2.general.KbConfig
 * @author ivan
 */
public class KeyProcessingStruct {
    public Integer keyCode;
    public Boolean shiftState=false;
    public Boolean controlState=false;
    public Boolean altState=false;
    public sciroguelike2.algodata.GameStates contextGameState;

    public KeyProcessingStruct(Integer inpKeyCode, 
                               Boolean inpShift, Boolean inpControl, Boolean inpAlt,
                               sciroguelike2.algodata.GameStates inpGameState) {
        this.keyCode = inpKeyCode;
        this.controlState = inpControl;
        this.altState = inpAlt;
        this.shiftState = inpShift;
        contextGameState = inpGameState;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof KeyProcessingStruct == false) {throw new IllegalArgumentException();}
        if ( (this.keyCode == ((KeyProcessingStruct)(obj)).keyCode) && 
             (this.shiftState == ((KeyProcessingStruct)(obj)).shiftState) &&
             (this.controlState == ((KeyProcessingStruct)(obj)).controlState) &&
             (this.altState == ((KeyProcessingStruct)(obj)).altState) &&
             (this.contextGameState== ((KeyProcessingStruct) (obj)).contextGameState))
            {return true;} else {return false;}
        
    }
    
}
