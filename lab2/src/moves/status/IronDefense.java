package moves.status;

import ru.ifmo.se.pokemon.*;

public final class IronDefense extends StatusMove {
    public IronDefense() {
        super(Type.STEEL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.DEFENSE, +2);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "использует Iron Defense";
    }
}
