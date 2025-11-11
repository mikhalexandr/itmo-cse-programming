package pokemons.first_team;

import ru.ifmo.se.pokemon.*;
import moves.status.*;

public final Vaporeon extends Eevee {
    public Vaporeon(String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setStats(130, 65, 60, 110, 95, 65);
        setMove(new Swagger(), new BabyDollEyes(), new DoubleTeam(), new Haze());
    }
}
