package moves.status;

import ru.ifmo.se.pokemon.*;

public final class CalmMind extends StatusMove {
    public CalmMind() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        self.setMod(Stat.SPECIAL_ATTACK, +1);
        self.setMod(Stat.SPECIAL_DEFENSE, +1);
    }

    @Override
    protected String describe() {
        return "использует Calm Mind";
    }
}
