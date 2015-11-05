package sciroguelike2.algodata.triggers;

/**
 * Used to choose proper action for tile trigger. Contains all possible actions - trigger processors
 * 
 * @author ivan
 */
public class ProcessTrigger {
    private static ProcessTrigger instance;
    private ProcessTrigger() {
        
    }
    /**
     * Select the right trigger processing utility
     * @param inpTrigger - the trigger
     * @param context - some structures with context of caller class. That data is not publically available
     */
    public void processTrigerRoutine(GameTriggers inpTrigger, Object context) {
        if (inpTrigger.equals( GameTriggers.SIMPLEMOVE) ) {
            moveCharTrigger(context);
        }
    }
    /**
     * a trigger processing routine for character movement
     * @param contextPass direction of player's movement (used as Integer)
     */
    private void moveCharTrigger (Object contextPass) {
        sciroguelike2.datastructs.Player.generalizeMovement((Integer) contextPass);
    }
    
    /**
     * this class has a singleton structure. An entry point for this class functionality
     * @return instance of class
     */
    public static ProcessTrigger getInstance() {
        if (instance==null) {
            instance = new ProcessTrigger();
        }
        return instance;
    }
    
}
