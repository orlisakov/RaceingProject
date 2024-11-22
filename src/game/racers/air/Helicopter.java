package game.racers.air;

import game.racers.Racer;
import game.racers.RacerPrototype;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements IAerialRacer {

	private static final String CLASS_NAME = "Helicopter";

	private static final double DEFAULT_MAX_SPEED = 400;
	private static final double DEFAULT_ACCELERATION = 50;
	private static final Color DEFAULT_color = Color.BLUE;

	public Helicopter() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
	}

	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Helicopter(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		return "";
	}



	/**
	 * returns a copy of the current object
	 * @param newColor
	 * @return
	 */
	@Override
	public RacerPrototype makeCopy(Color newColor) {
		Helicopter clone = null;
		try {
			clone = (Helicopter) this.clone();
			clone.setColor(newColor);
			clone.updateCloneSerialNumber();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		finally {
			return clone;
		}
	}
}
