package FarmingSim;

public abstract class UIUpdateable {
    private int index = 0;

    public UIUpdateable(int ind) {
        this.index = ind;
    }

    public void initialize() {
        boolean hasBeenInited = GameState.hasInited[this.index];
        if (!hasBeenInited) {
            firstInit();
            GameState.hasInited[this.index] = true;
        } else {
            updateUI();
        }
    }

    public abstract void firstInit();
    public abstract void updateUI();
}
