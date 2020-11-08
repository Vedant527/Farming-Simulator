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
        for (int i = 0; i < Crop.Type.size(); i++) {
            Crop.Type type = Crop.Type.values()[i];
            System.out.println(type);
            while (!GameState.getInventory().isEmpty(type, true)) {
                System.out.println("Org");
                if (moves <= 0) {
                    return;
                }
                GameState.getInventory().sellImpl(type, true);
                moves--;
            }
            while (!GameState.getInventory().isEmpty(type, false)) {
                System.out.println("Pest");
                if (moves <= 0) {
                    return;
                }
                GameState.getInventory().sellImpl(type, false);
                moves--;
            }
        }
    }

    public int getLevel() {
        return this.level;
    }
}
