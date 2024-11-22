package game.racers;

/**
 * This class represents the racer in a Banished state
 */
public class RacerBanishedState extends RacerState{

    private final String STATE_MESSAGE = "Racer Banished";

    public RacerBanishedState(Racer context) {
        super(context);
        setStateMessage(STATE_MESSAGE);
        System.out.println("[RACER " + STATE_MESSAGE + "]\t" +getContext().describeRacer() + "");
    }
    /**
     * This method is invoked in all states, implements the move logic for this state
     */
    @Override
    public void move() {
        getContext().setFinish(getContext().getLocation());
        getContext().notifyObservers();
    }
}
