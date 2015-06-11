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

    public Piechart(String category, double percent) {
        this.category = category;
        this.percent = percent;
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

    public double calculateX(double degrees){
        
    }
    
    public double calculateY(double degrees){
        
    }
    
    
    
}
