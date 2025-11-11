package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public class Magearna extends Pokemon {
    public Magearna(String name, int level) {
        super(name, level);
        setType(Type.STEEL, Type.FAIRY);
        setStats(80, 95, 115, 130, 115, 65);
        setMove(new Facade(), new DarkPulse(), new IronDefense(), new Psychic());
    }
}
