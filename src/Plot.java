//probably this can handle the logic for each plot
public class Plot {
    private CropType cropType;
    private int bushels;
    private boolean ready;

    public Plot() {
        this(CropType.NULL,  0, false);
    }

    public Plot(CropType cropType) {
        this(cropType, 0, false);
    }

    public Plot(CropType cropType, int bushels, boolean ready) {
        this.cropType = cropType;
        this.bushels = bushels;
        this.ready = ready;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public int getBushels() {
        return bushels;
    }

    public void setBushels(int bushels) {
        this.bushels = bushels;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }


}
