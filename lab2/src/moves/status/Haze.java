package moves.status;

import ru.ifmo.se.pokemon.*;

public final class Haze extends StatusMove {
    public Haze() {
        super(Type.ICE, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        for (Stat s : Stat.values()) {
            self.setMod(s, 0);
        }
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        for (Stat s : Stat.values()) {
            opp.setMod(s, 0);
        }
    }

    @Override
    protected String describe() {
        return "использует Haze";
    }
}
