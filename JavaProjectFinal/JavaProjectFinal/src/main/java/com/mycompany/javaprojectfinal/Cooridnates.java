/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectfinal;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Hana_
 */
public class Cooridnates {
    public Point start, end;
    public Color color;
    public int x;
    public int y;
    public int w;
    public int h;
    public boolean isfill;
    public Cooridnates (Point start, Point end, Color color) {
        this.start=start;
        this.end=end;
        this.color=color;
    }
    public Cooridnates(int x, int y,Color color ){
        this.x=x;
        this.y=y;
        this.color=color;
                
                
    }
    
      public Cooridnates (Point start, Point end, Color color, boolean isfill) {
        this.start=start;
        this.end=end;
        this.color=color;
        this.isfill=isfill;
    }
      
      public Cooridnates(int x, int y, int w, int h ){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
                
    }
    
}
