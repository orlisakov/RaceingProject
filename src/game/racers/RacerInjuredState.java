package game.racers;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class represents an injured racer state
 * A racer that is injured can either become active again, or go to disabled, or completed
 */
public class RacerInjuredState extends RacerState{
    private final String STATE_MESSAGE = "Racer Injured";

    /**
     * Constructor for this class
     * @param context
     */
    public RacerInjuredState(Racer context) {
        super(context);
        setStateMessage(STATE_MESSAGE);
        LocalDateTime timeOfBreakdown = LocalDateTime.now();

        Duration duration = Duration.between(getContext().getArena().getStart(), timeOfBreakdown);
        double timeDiffSeconds = duration.getSeconds();
        double timeDiffMinutes = duration.toMinutes();
        String mishapString = "{" + timeDiffMinutes + ":" + timeDiffSeconds + "}";
        setStateMessage(getStateMessage() + " " + mishapString);
        System.out.println("[RACER " + getStateMessage() + "]\t" +getContext().describeRacer() + " " );
    }

    /**
     * Implements the move logic of the state - if can be fixed, lowers the time to be fixed, if can be fixed now
     * will set the state of the racer to an active state and tell it to move
     */
    @Override
    public void move() {
        /**
         * If the racer's mishap cannot be repaired and his speed is 0, he is DISABLED
         */
        if(!this.getContext().getMishap().isFixable() && getContext().getCurrentSpeed() == 0 && true){

                this.getContext().setState(new RacerBanishedState(getContext()));
        }
        else{
            if(getContext().getMishap().getTurnsToFix() == 0){
                getContext().setMishap(null);
                getContext().setState(new RacerActiveState(getContext()));
                getContext().move();
            }
            else {
                double reductionFactor = this.getContext().getMishap().getReductionFactor();
                this.getContext().getMishap().nextTurn();
                this.getContext().moveAccordingToState(reductionFactor);
            }

//            double reductionFactor = this.getContext().getMishap().getReductionFactor();
//            this.getContext().getMishap().nextTurn();
//            this.getContext().moveAccordingToState(reductionFactor);
//            if (getContext().hasMishap()) {


//            }
        }

    }
}
