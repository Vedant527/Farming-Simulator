package farmsim;

import java.util.Objects;

public class Crop {
    private boolean organic;
    private GameState.CropType type;
    private State state;
    private final int pestReduction = -5;

    public enum State {
        EMPTY, //no crop currently planted, this means available to plant
        SEED,
        IMMATURE,
        MATURE,
        DEAD;
    }

    public Crop(GameState.CropType type, State state, boolean org) {
        this.type = type;
        this.state = state;
        this.organic = org;
    }

    public int price(int[] prices) {
        return prices[this.state.ordinal()] + ((organic) ? pestReduction : 0);
    }

    public void grow() {
        if (this.state == State.EMPTY || this.state == State.DEAD) {
            return;
        }
        this.state = State.values()[(this.state.ordinal() + 1) % State.values().length];
    }

    public void kill() {
        this.state = State.DEAD;
    }

    @Override
    public String toString() {
        return ((this.organic) ? "Organic " : "") + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return organic == crop.organic &&
                type == crop.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(organic, type);
    }

/*
----------------------------------------------------------------------------------------------------
VARIABLE GETTERS AND SETTERS
----------------------------------------------------------------------------------------------------
*/

    public Crop(GameState.CropType type, boolean org) {
        this(type, State.EMPTY, org);
    }

    public Crop(GameState.CropType type) {
        this(type, State.EMPTY, false);
    }

    public State getState() {
        return state;
    }

    public void setState(State s) {
        this.state = s;
    }

    public GameState.CropType getType() {
        return this.type;
    }

    public void setType(GameState.CropType t) {
        this.type = t;
    }

    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean org) {
        this.organic = org;
    }
}