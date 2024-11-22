package game.racers;


import game.arenas.Arena;
import utilities.EnumContainer;
import utilities.Mishap;
import utilities.MyObservable;
import utilities.Point;

import java.time.LocalDateTime;

public abstract class Racer extends MyObservable implements Runnable , RacerPrototype{
	protected static int lastSerialNumber = 1;

	private int serialNumber; // Each racer has an unique number, assigned by arena in addRacer() method
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	@SuppressWarnings("unused")
	private double failureProbability; // Chance to break down
	private EnumContainer.Color color; // (RED,GREEN,BLUE,BLACK,YELLOW)
	private Mishap mishap;
	private double arenaFriction;
	private RacerState state;


	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		this.serialNumber = Racer.lastSerialNumber++;
		this.name = name;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.color = color;
	}

	public abstract String className();

	public String describeRacer() {
		String s = "";
		s += "name: " + this.name + ", ";
		s += "SerialNumber: " + this.serialNumber + ", ";
		s += "maxSpeed: " + this.maxSpeed + ", ";
		s += "acceleration: " + this.acceleration + ", ";
		s += "color: " + this.color + ", ";
		s = s.substring(0, s.length() - 2);
		// returns a string representation of the racer, including: general attributes
		// (color name, number) and specific ones (numberOfWheels, etc.)
		s += this.describeSpecific();
		return s;
	}

	public abstract String describeSpecific();

	public int getSerialNumber() {
		return serialNumber;
	}

	public boolean hasMishap() {
		if (this.mishap != null && this.mishap.getTurnsToFix() == 0)
			this.mishap = null;
		return this.mishap != null;
	}

	public void initRace(Arena arena, Point start, Point finish, double friction) {
		this.arena = arena;
		this.state = new RacerActiveState(this);
		this.currentLocation = new Point(start);
		this.finish = new Point(finish);
		this.setArenaFriction(friction);
	}

	public void introduce() {
		// Prints a line, obtained from describeRacer(), with its type
		System.out.println("[" + this.className() + "] " + this.describeRacer());
	}

	public void setState(RacerState state) {
		this.state = state;
	}

	/**
	 * Move, according to the state
	 */
	public void move() {
		state.move();
	}


	/**
	 * Moves according to the reduction factor, invoked from state
	 * @param reductionFactor
	 */
	public void moveAccordingToState(double reductionFactor){
		if (this.currentSpeed < this.maxSpeed) {
			this.currentSpeed += this.acceleration * this.arenaFriction * reductionFactor;
		}
		if (this.currentSpeed > this.maxSpeed) {
			this.currentSpeed = this.maxSpeed;
		}
		double newX = (this.currentLocation.getX() + (this.currentSpeed));
		if (newX>=this.finish.getX()) {
			newX = this.finish.getX();
			state = new RacerCompletedState(this);
		}
		Point newLocation = new Point(newX, this.currentLocation.getY());
		this.currentLocation = newLocation;

		setChanged();
		notifyObservers();
	}


	public boolean raceInProgress() {
		return this.currentLocation.getX() < this.finish.getX();
	}

	@Override
	public void run() {
		while (this.raceInProgress()) {
			this.move();
                        try { 
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
		}
		setChanged();
		this.notifyObservers();
		//this.arena.crossFinishLine(this);
	}

	private void setArenaFriction(double friction) {
		this.arenaFriction = friction;
	}
        
        public Point getLocation(){
            return this.currentLocation;
        }
        
        public String getName(){
            return this.name;
        }
        
        public double getMaxSpeed(){
            return this.maxSpeed;
        }
        
        public double getCurrentSpeed(){
            return this.currentSpeed;
        }


	public EnumContainer.Color getColor() {
		return color;
	}

	public boolean setColor(EnumContainer.Color color) {
		this.color = color;
		return true;
	}

	public Point getFinish() {
		return finish;
	}

	public boolean setFinish(Point finish) {
		this.finish = finish;
		return true;
	}

	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * This method is used to update a cloned racer's serial number to the latest one
	 * ensuring all serial numbers are unique.
	 */
	public void updateCloneSerialNumber(){
			this.serialNumber = lastSerialNumber++;
		}

	public Arena getArena(){
		return this.arena;
	}

	public RacerState getState(){
		return this.state;
	}

	public Mishap getMishap(){
		return this.mishap;
	}

	public boolean setMishap(Mishap mishap) {
		this.mishap = mishap;
		return true;
	}
}
