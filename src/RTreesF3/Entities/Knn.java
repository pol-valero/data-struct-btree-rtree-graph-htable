package RTreesF3.Entities;

import static java.lang.Math.sqrt;

public class Knn implements Comparable<Knn>{
    private Hedge hedge;
    private double distance;
    public Knn(Hedge hedge, Point p1) {
        this.hedge = hedge;
        calculateDistance(p1);
    }
    private void calculateDistance(Point p1){
        distance = sqrt((p1.x - hedge.getPoint().x) * (p1.x -  hedge.getPoint().x) + (p1.y -  hedge.getPoint().y) * (p1.y -  hedge.getPoint().y));
    }

    public Hedge getHedge() {
        return hedge;
    }

    @Override
    public int compareTo(Knn o) {
        if (this.distance > o.distance) {
            return 1;
        }else if (this.distance < o.distance) {
            return -1;
        }else {
            return 0;
        }
    }
}
