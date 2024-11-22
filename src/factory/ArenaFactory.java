package factory;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

/**
 * This class represents a factory to create arenas
 */
public class ArenaFactory {

    /**
     * This mehtod will return an arena, based on the key word given to it
     * @param type
     * @return
     */
    public Arena makeArena(String type){
        Arena result = null;
        switch (type){
            case "Air": result = new AerialArena(); break;
            case "Land": result = new LandArena(); break;
            case "Navy": result = new NavalArena(); break;
        }
        return result;
    }

}
