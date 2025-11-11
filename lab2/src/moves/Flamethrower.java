package moves;

import ru.ifmo.se.pokemon.*;

public final class Flamethrower extends SpecialMove {
    public Flamethrower() {
        super(Type.FIRE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        if (Math.random() < 0.1) {
            Effect.burn(opp);
        }
    }

    @Override
    protected String describe() {
        return "использует Flamethrower";
    }
}
