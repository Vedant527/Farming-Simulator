package FarmingSim;

public class Farm {
    //this is a "code smell" it only holds plots and gets their method
    public Plot[] plots = new Plot[16];//16 is the number of buttons

    private void Farm() {
        for (int i = 1; i < 16; i++) {
            plots[i] = new Plot();
        }
    }

    public void harvest(int i) {
        plots[i].harvest();
    }

    public void plant(int i) {
        //we don't need this for this milestone
    }

}
