package factory;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.Car;

/**
 * This class represents a default builder for a land race with cars
 */
public class CarRaceBuilder {
    /**
     * Builds a default land race with a given number of Car participants
     * @param racerAmount
     * @return
     */
    public Arena buildDefault(int racerAmount){
        ArenaFactory factory = new ArenaFactory();
        Arena arena = factory.makeArena("Land");
        Car car = new Car();

        for(int i = 0; i < racerAmount; i++){
            try {
                arena.addRacer((Racer) car.makeCopy(car.getColor()));
            } catch (RacerLimitException e) {
                e.printStackTrace();
            } catch (RacerTypeException e) {
                e.printStackTrace();
            }
        }
        return arena;
    }
}
