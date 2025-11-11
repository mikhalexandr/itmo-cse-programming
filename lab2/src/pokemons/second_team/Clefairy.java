package pokemons.second_team;

import ru.ifmo.se.pokemon.*;
import moves.special.*;
import moves.status.*;

public class Clefairy extends Cleffa {
    public Clefairy(String name, int level) {
        super(name, level);
        setStats(70, 45, 48, 60, 65, 35);
        setMove(new ThunderWave(), new Flamethrower(), new Growl());
    }
}
