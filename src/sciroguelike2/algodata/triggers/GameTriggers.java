package sciroguelike2.algodata.triggers;

/**
 * collection of all game triggers. When a character steps on any tile a trigger is fired.
 * There might be a trigger that does nothing (regular wall), or just moves player forward.
 * The whole assortment of triggers should be located in a single class.
 * If one seeks extendability, one may use a Reflection API.
 * @author ivan
 */
public enum GameTriggers {
    SIMPLEMOVE, SIMPLESTOP
}
