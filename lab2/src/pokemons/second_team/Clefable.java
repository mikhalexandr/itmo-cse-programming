package pokemons.second_team;

import ru.ifmo.se.pokemon.*;
import moves.special.*;
import moves.status.*;

public final class Clefable extends Clefairy {
    public Clefable(String name, int level) {
        super(name, level);
        setStats(95, 70, 73, 95, 90, 60);
        setMove(new ThunderWave(), new Flamethrower(), new Growl(), new CalmMind());
    }
}
