package interfaces;

import data_types.Taste;

public interface Edible {
    Taste getTaste(boolean isCooked);
    boolean isEdibleRaw();
}
