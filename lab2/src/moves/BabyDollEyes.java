package moves;

import ru.ifmo.se.pokemon.*;

public final class BabyDollEyes extends StatusMove {
    public BabyDollEyes() {
        super(Type.FAIRY, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        opp.setMod(Stat.ATTACK, -1);
    }

    @Override
    protected String describe() {
        return "использует Baby-Doll Eyes";
    }
}
