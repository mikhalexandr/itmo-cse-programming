package moves.status;

import ru.ifmo.se.pokemon.*;

public final class ThunderWave extends StatusMove {
    public ThunderWave() {
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        Effect.paralyze(opp);
    }

    @Override
    protected String describe() {
        return "использует Thunder Wave";
    }
}
