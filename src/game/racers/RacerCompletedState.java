package game.racers;

/**
 * This class represents the racer in a Completed race state
 */
public class RacerCompletedState extends RacerState{

    private final String STATE_MESSAGE = "Racer Finished";

    /**
     * Constructor for this class
     * @param context
     */
    public RacerCompletedState(Racer context) {
        super(context);
        setStateMessage(STATE_MESSAGE);
        System.out.println("[RACER " + STATE_MESSAGE + "]\t" +getContext().describeRacer() + "");
    }

    /**
     * This method is invoked in all states, implements the move logic for this state
     */
    @Override
    public void move() {
        getContext().notifyObservers();
    }
}
