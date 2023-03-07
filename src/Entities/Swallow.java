package Entities;

public class Swallow {
    private final Climate notClimate;
    private final boolean hasCoco;
    private double totalDist;
    private double totalTime;

    public Swallow(Climate notClimate, boolean hasCoco) {
        this.notClimate = notClimate;
        this.hasCoco = hasCoco;
        this.totalDist = 0;
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
}
