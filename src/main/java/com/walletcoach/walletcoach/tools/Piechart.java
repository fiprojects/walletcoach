/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walletcoach.walletcoach.tools;

/**
 *
 * @author x390496
 */
public class Piechart {
    
    private String category;
    private double percent;
    private double degrees;
    private Point point;
    public static final double RADIUS = 180;
    private double radians;
    

    public Piechart(String category, double percent) {
        this.category = category;
        this.percent = percent;
        this.degrees = percent * 360;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Point calculateCoordinates(double degrees){
        radians = Math.toRadians(degrees);
        double x = RADIUS * Math.cos(radians);
        double y = RADIUS * Math.sin(radians);
        point = new Point(x,y);
        return point;
    }
    
    
    
    
    
}
