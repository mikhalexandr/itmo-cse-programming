package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public class Eevee extends Pokemon {
    public Eevee(String name, int level) {
        super(name, level);
        setType(Type.NORMAL);
        setStats(55, 55, 50, 45, 65, 55);
        setMove(new Swagger(), new BabyDollEyes(), new DoubleTeam());
    }
}
