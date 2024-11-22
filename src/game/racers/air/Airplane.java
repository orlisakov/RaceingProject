package game.racers.air;

import game.racers.Racer;
import game.racers.RacerPrototype;
import game.racers.Wheeled;
import utilities.EnumContainer;
import utilities.EnumContainer.Color;

public class Airplane extends Racer implements IAerialRacer {
	private static final String CLASS_NAME = "Airplane";

	private static final int DEFAULT_WHEELS = 3;
	private static final double DEFAULT_MAX_SPEED = 885;
	private static final double DEFAULT_ACCELERATION = 100;
	private static final Color DEFAULT_color = Color.BLACK;
	private Wheeled wheeled;

	public Airplane() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,
				DEFAULT_WHEELS);
	}

	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Airplane(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(numOfWheels);
	}
        
        public Airplane(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(3);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		return this.wheeled.describeSpecific();
	}


	/**
	 * returns a clone of the current object
	 * @param newColor
	 * @return
	 */
	@Override
	public RacerPrototype makeCopy(Color newColor) {
		Airplane clone = null;
		try {
			clone = (Airplane) this.clone();
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
