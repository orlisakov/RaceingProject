package game.racers.land;

import game.racers.Racer;
import game.racers.RacerPrototype;
import game.racers.Wheeled;
import utilities.EnumContainer;
import utilities.EnumContainer.BicycleType;
import utilities.EnumContainer.Color;

public class Bicycle extends Racer implements ILandRacer {

	private static final String CLASS_NAME = "Bicycle";
	private static final String DEFUALT_NAME = CLASS_NAME + " #" + lastSerialNumber;

	private static final int DEFAULT_WHEELS = 2;
	private static final double DEFAULT_MAX_SPEED = 270;
	private static final double DEFAULT_ACCELERATION = 10;
	private static final Color DEFAULT_color = Color.GREEN;

	private EnumContainer.BicycleType type;
	private Wheeled wheeled;

	public Bicycle() {
		this(DEFUALT_NAME, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color, DEFAULT_WHEELS);
	}

	public Bicycle(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(numOfWheels);
		this.type = BicycleType.MOUNTAIN;
	}
        
        public Bicycle(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled = new Wheeled(2);
		this.type = BicycleType.MOUNTAIN;
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += this.wheeled.describeSpecific();
		s += ", Bicycle Type: " + this.type;
		return s;
	}

	/**
	 * returns a copy of the current object
	 * @param newColor
	 * @return
	 */
	@Override
	public RacerPrototype makeCopy(Color newColor) {
		Bicycle clone = null;
		try {
			clone = (Bicycle) this.clone();
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
