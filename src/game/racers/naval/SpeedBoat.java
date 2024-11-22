package game.racers.naval;

import game.racers.Racer;
import game.racers.RacerPrototype;
import utilities.EnumContainer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;

public class SpeedBoat extends Racer implements INavalRacer {
	private static final String CLASS_NAME = "SpeedBoat";

	private static final double DEFAULT_MAX_SPEED = 170;
	private static final double DEFAULT_ACCELERATION = 5;
	private static final Color DEFAULT_color = Color.RED;

	private EnumContainer.BoatType type;
	private EnumContainer.Team team;

	public SpeedBoat() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
	}

	public SpeedBoat(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
		this.type = BoatType.SKULLING;
		this.team = Team.SINGLE;
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Type: " + this.type;
		s += ", Team: " + this.team;

		return s;
	}


	/**
	 * returns a copy of the current object
	 * @param newColor
	 * @return
	 */
	@Override
	public RacerPrototype makeCopy(Color newColor) {
		SpeedBoat clone = null;
		try {
			clone = (SpeedBoat) this.clone();
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
