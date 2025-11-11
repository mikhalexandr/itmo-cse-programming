package moves;

import ru.ifmo.se.pokemon.*;

public final class DarkPulse extends SpecialMove {
    public DarkPulse() {
        super(Type.DARK, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        if (Math.random() < 0.2) {
            Effect.flinch(opp);
        }
    }

    @Override
    protected String describe() {
        return "использует Dark Pulse";
    }
}
