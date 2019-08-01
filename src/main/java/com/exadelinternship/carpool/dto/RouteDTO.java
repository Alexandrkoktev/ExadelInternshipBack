package com.exadelinternship.carpool.dto;

public class RouteDTO {
    private double[] startPoint;
    private double[] finishPoint;
    private double[][] viaPoints;

    public double[] getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double[] startPoint) {
        this.startPoint = startPoint;
    }

    public double[] getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(double[] finishPoint) {
        this.finishPoint = finishPoint;
    }

    public double[][] getViaPoints() {
        return viaPoints;
    }

    public void setViaPoints(double[][] viaPoints) {
        this.viaPoints = viaPoints;
    }
}
