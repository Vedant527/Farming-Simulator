package farmsim;

public abstract class UIUpdateable {
    private int index = 0;

    public UIUpdateable(int ind) {
        this.index = ind;
    }

    public void initialize() {
        boolean hasBeenInited = GameState.getHasInited()[this.index];
        if (!hasBeenInited) {
            firstInit();
            GameState.setHasInited(this.index, true);
        } else {
            updateUI();
        }
    }

    public abstract void firstInit();

    public abstract void updateUI();
}
