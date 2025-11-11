import ru.ifmo.se.pokemon.Battle;
import pokemons.first_team.*;
import pokemons.second_team.*;

public class Main {
    public static void main(String[] args) {
        Battle battle = new Battle();

        // 1-я команда
        Magearna magearna = new Magearna("Магеарна", 50);
        Eevee eevee = new Eevee("Иви", 20);
        Vaporeon vaporeon = new Vaporeon("Вапореон", 36);

        battle.addAlly(magearna);
        battle.addAlly(eevee);
        battle.addAlly(vaporeon);

        // 2-я команда
        Cleffa cleffa = new Cleffa("Клеффа", 14);
        Clefairy clefairy = new Clefairy("Клефейри", 25);
        Clefable clefable = new Clefable("Клефейбл", 40);

        battle.addFoe(cleffa);
        battle.addFoe(clefairy);
        battle.addFoe(clefable);

        battle.go();
    }
}
