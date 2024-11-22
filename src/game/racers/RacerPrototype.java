package game.racers;

import utilities.EnumContainer;

/**
 * This interface represents the RacerPrototype according to the Prototype pattern
 */
public interface RacerPrototype extends Cloneable{
    /**
     * This method will receive a color and will create a clone of an racer with the new color
     * @param newColor
     * @return
     */
    public RacerPrototype makeCopy(EnumContainer.Color newColor);
}
