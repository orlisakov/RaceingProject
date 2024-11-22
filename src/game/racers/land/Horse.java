package game.racers.land;

import game.racers.Racer;
import game.racers.RacerPrototype;
import utilities.EnumContainer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;

public class Horse extends Racer implements ILandRacer {

	private static final String CLASS_NAME = "Horse";

	private static final double DEFAULT_MAX_SPEED = 50;
	private static final double DEFAULT_ACCELERATION = 3;
	private static final Color DEFAULT_color = Color.BLACK;

	private EnumContainer.Breed breed;

	public Horse() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
	}

	public Horse(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
		this.breed = Breed.THOROUGHBRED;
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Breed: " + this.breed;
		return s;
	}

	/**
	 * returns a clone of the current object
	 * @param newColor
	 * @return
	 */
	@Override
	public RacerPrototype makeCopy(Color newColor) {
		Horse clone = null;
		try {
			clone = (Horse) this.clone();
			clone.setColor(newColor);
			clone.updateCloneSerialNumber();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			return clone;
		}
	}
}
