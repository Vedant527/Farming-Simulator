package farmsim;

import java.util.Objects;

public class Crop {
    private boolean organic;
    private Type type;
    private State state;
    private final int pestReduction = -5;

    public enum State {
        EMPTY, //no crop currently planted, this means available to plant
        SEED,
        IMMATURE,
        MATURE,
        DEAD;
    }

    public enum Type {
        CORN,
        WHEAT,
        TOBACCO,
        HEMP;

        public static int size() {
            return Type.values().length;
        }
    }

    public Crop(Type type, State state, boolean org) {
        this.type = type;
        this.state = state;
        this.organic = org;
    }

    public Crop(Type type, State state) {
        this (type, state, false);
    }

    public Crop(Type type, boolean org) {
        this(type, State.MATURE, org);
    }

    public Crop(Type type) {
        this(type, State.SEED, false);
    }

    public int price(int[] prices) {
        return prices[type.ordinal()] + ((!organic) ? pestReduction : 0);
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
        return (this.organic ? "O. " : "")
             + type
             + (this.state == State.SEED ? " Seed" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return organic == crop.organic &&
                type == crop.type &&
                state == crop.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(organic, type) + (state == State.SEED ? 7 : 0);
    }

    /*
    ----------------------------------------------------------------------------------------------------
    VARIABLE GETTERS AND SETTERS
    ----------------------------------------------------------------------------------------------------
    */
    public State getState() {
        return state;
    }

    public void setState(State s) {
        this.state = s;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type t) {
        this.type = t;
    }

    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean org) {
        this.organic = org;
    }
}
