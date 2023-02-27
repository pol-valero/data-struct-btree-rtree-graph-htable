package Entities;

public class Swallow {
    private final Climate notClimate;
    private final boolean hasCoco;
    private double totalDist;

    public Swallow(Climate notClimate, boolean hasCoco) {
        this.notClimate = notClimate;
        this.hasCoco = hasCoco;
        this.totalDist = 0;
    }
}
