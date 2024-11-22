package game.racers;

import utilities.Fate;

/**
 * This state represents the Racer in an Active state where he can move freely
 */
public class RacerActiveState extends RacerState{

    private final String STATE_MESSAGE = "Racer Active";

    /**
     * Constructor for this class
     * @param context
     */
    public RacerActiveState(Racer context) {
        super(context);
        setStateMessage(STATE_MESSAGE);
        System.out.println("[RACER " + STATE_MESSAGE + "]\t" +getContext().describeRacer() + "");
    }

    /**
     * This method is invoked in all states, implements the move logic for this state
     */
    @Override
    public void move() {
        double reductionFactor = 1;
        if (!(getContext().hasMishap()) && Fate.breakDown()) {
            getContext().setMishap(Fate.generateMishap());
            getContext().setState(new RacerInjuredState(getContext()));
            this.getContext().move();
        }
        else {
            this.getContext().moveAccordingToState(reductionFactor);
        }

    }
}
