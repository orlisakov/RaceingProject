package game.arenas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.MyObservable;
import utilities.MyObserver;
import utilities.Point;

public abstract class Arena extends MyObservable implements MyObserver {

	private final static int MIN_Y_GAP = 60;
	private ArrayList<Racer> activeRacers;
	private ArrayList<Racer> compleatedRacers;

	private double length;
	private final int MAX_RACERS;
	private final double FRICTION;

	/**
	 * Save the current timeStamp of the race
	 */
	private LocalDateTime start;
	/**
	 * 
	 * @param length
	 *            the x value for the finish line
	 * @param maxRacers
	 *            Maximum number of racers
	 * @param friction
	 *            Coefficient of friction
	 * 
	 */
	protected Arena(double length, int maxRacers, double friction) {
		this.length = length;
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.activeRacers = new ArrayList<Racer>();
		this.compleatedRacers = new ArrayList<Racer>();
	}

	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		newRacer.addObserver(this);
		if (this.activeRacers.size() == this.MAX_RACERS) {
			throw new RacerLimitException(this.MAX_RACERS, newRacer.getSerialNumber());
		}
		this.activeRacers.add(newRacer);
	}

	@Deprecated
	public void crossFinishLine(Racer racer) {
		this.compleatedRacers.add(racer);
		this.activeRacers.remove(racer);
		if (this.activeRacers.size() == 0) {

		}
	}

	public ArrayList<Racer> getActiveRacers() {
		return activeRacers;
	}

	public ArrayList<Racer> getCompleatedRacers() {
		return compleatedRacers;
	}

	public boolean hasActiveRacers() {
		return this.activeRacers.size() > 0;
	}

	public void initRace() {
		int y = 0;
		for (Racer racer : this.activeRacers) {
			Point s = new Point(0, y);
			Point f = new Point(this.length, y);
			racer.initRace(this, s, f, this.FRICTION);
			y += Arena.MIN_Y_GAP;
		}
	}

	@Deprecated
	public void playTurn() {
		for (Racer racer : this.activeRacers) {
			// racer.move(this.FRICTION);
		}

		for (Racer r : this.compleatedRacers)
			this.activeRacers.remove(r);
	}

	@Deprecated
	public void showResults() {
		for (Racer r : this.compleatedRacers) {
			String s = "#" + this.compleatedRacers.indexOf(r) + " -> ";
			s += r.describeRacer();
			System.out.println(s);
		}
		// for (int i = 0; i < this.activeRacers.size(); i++) {
		// System.out.println("#" + (i + 1) + ": " + this.activeRacers.get(i));
		// }
	}

	public void startRace() throws InterruptedException {
		start = LocalDateTime.now();
		initRace();
		ExecutorService e = Executors.newFixedThreadPool(this.activeRacers.size());
		for (Racer racer : activeRacers) {
			e.execute(racer);
		}
		e.shutdown();
		//e.awaitTermination(10, TimeUnit.MINUTES);
	}

	/**
	 * Receive the object that sends the update, notifies its observer
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(MyObservable o, Object arg) {

		if(!((Racer) o).raceInProgress()) {
			this.compleatedRacers.add((Racer) o);
			this.activeRacers.remove((Racer) o);
		}
		this.setChanged();
		this.notifyObservers(null);
	}

	/**
	 * Reset the active racers
	 */
	public void resetActiveRacers(){
		this.activeRacers = new ArrayList<>();
	}

	public double getLength() {
		return length;
	}

	public LocalDateTime getStart() {
		return start;
	}
}
