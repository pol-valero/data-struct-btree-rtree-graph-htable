package GraphsF1.Entities;

public class Swallow {
    private final Climate notClimate;
    private final boolean hasCoco;
    private double totalDist;
    private double totalTime;
    private final String type;
    public final static String EUROPEA = "Europea";
    public final static String AFRICANA = "Africana";

    public Swallow(String type, boolean hasCoco) {
        this.hasCoco = hasCoco;
        this.totalDist = 0;
        this.type = type;

        if (type.equals(EUROPEA)) {
            this.notClimate = Climate.TROPICAL;
        } else {
            this.notClimate = Climate.POLAR;
        }
    }

    public void updateDist(double newDist) {
        totalDist = newDist;
    }
    public void updateTime(double newTime) {
        totalTime = newTime;
    }

    public double getTotalDist() {
        return totalDist;
    }

    public Climate getNotClimate(){
        return notClimate;
    }

    public boolean getHasCoco(){
        return hasCoco;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String getType() {
        return type;
    }
}
