package farmsim;

public class FarmHand {
    private int level; // 0-2
    private final int[] productivity = {3, 6, 10}; // Moves based on level
    private final int[] pay = {2, 4, 6};

    public FarmHand(int level) {
        this.level = level;
    }

    public int getPay() {
        return this.pay[this.level];
    }

    public void work() {
        int moves = this.productivity[this.level];
        for (int i = 0; i < GameState.getPlots().length; i++) {
            Plot curr = GameState.getPlots(i);
            if (curr.getCrop().getState() != Crop.State.MATURE) {
                continue;
            }
            if (moves <= 0) {
                return;
            }
            curr.harvest();
            moves--;
        }
        for (int i = 0; i < GameState.CropType.size(); i++) {
            GameState.CropType type = GameState.CropType.values()[i];
            while (GameState.getInventory().hasCrop(type, true)) {
                if (moves <= 0) {
                    return;
                }
                GameState.getInventory().sellImpl(type, true);
                moves--;
            }
            while (GameState.getInventory().hasCrop(type, false)) {
                if (moves <= 0) {
                    return;
                }
                GameState.getInventory().sellImpl(type, false);
                moves--;
            }
        }
    }
}
