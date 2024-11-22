package game.racers;

/**
 * This is the base class for a Racer State, all Racer states will extend from it
 */
public abstract class RacerState {
    private Racer context;
    private String stateMessage;

    /**
     * Constructor that puts in the context for the state, the message will automatically be initialized by each state
     * @param context
     */
    public RacerState(Racer context){
        this.context = context;
    }

    /**
     * This method will be implemented by each state seperately
     */
    public abstract void move();

    /**
     * Getter for the state message
     * @return
     */
    public String getStateMessage() {
        return stateMessage;
    }

    /**
     * Setter for the state message
     * @param stateMessage
     * @return
     */
    public boolean setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
        return true;
    }

    /**
     * Getter for the context
     * @return
     */
    public Racer getContext() {
        return context;
    }

    /**
     * Setter for the context
     * @param context
     * @return
     */
    public boolean setContext(Racer context) {
        this.context = context;
        return true;
    }
}
