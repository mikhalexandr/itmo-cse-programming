package pokemons.second_team;

import ru.ifmo.se.pokemon.*;
import moves.special.*;
import moves.status.*;

public class Cleffa extends Pokemon {
    public Cleffa(String name, int level) {
        super(name, level);
        setType(Type.FAIRY);
        setStats(50, 25, 28, 45, 55, 15);
        setMove(new ThunderWave(), new Flamethrower());
    }
}
