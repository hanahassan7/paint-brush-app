/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectfinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hana_
 */
public class MyPanel extends JPanel{
       
    //Declaration for the buttons
    JCheckBox fill;
    JButton linebtn;
    JButton rectbtn;
    JButton ovalbtn;   
    JButton freehandbtn;
    JButton eraser, clearall;
    Button green, red, black;
    JButton callbackbtn;
    JButton save;
    private Color currentcolor = Color.BLACK;
    
    //variables for is the buttons is pressed or not
    public boolean filled;
    public boolean isrectangle;
    public boolean isoval;
    public boolean isline;
    public boolean isfree;
    public boolean dragging;
    public boolean iseraser;
    
    //variables for the starting and ending points for the shape
    public Point startpoint;
    public Point endpoint;
    
    //Variable of the current shape 1.line 2.Rectangle 3.Oval 4.freehand
    int currentshape=0;
    //Variable for the clearall
    int count=1;
    //variable for undo
    int callback =1;
   //arraylist for the shape to store the shape's cooridnates
    ArrayList<Cooridnates> rectangle = new ArrayList<>();
    ArrayList<Cooridnates> line = new ArrayList<>();
    ArrayList<Cooridnates> oval = new ArrayList<>();
    ArrayList<Cooridnates> eraserarray = new ArrayList<>();
    //arraylist for the shapetype to store the shape's cooridnates
    ArrayList<Integer> shapetype=new ArrayList<>();
    

    //arraylist for the polylines and freehand
    ArrayList<Cooridnates> freehand = new ArrayList<>();

   
    //constructor 
    public MyPanel(){
        //Setting the background white 
        setBackground(Color.white);
        
        //Colors point 1
        //Green
        green = new Button("   ");
        green.setBackground(Color.GREEN);
        this.add(green);
        green.addActionListener(e -> currentcolor = Color.GREEN);
        
        //red
        red = new Button("      ");
        red.setBackground(Color.RED);
        this.add(red);
        red.addActionListener(e -> currentcolor = Color.RED);
        
        //black
        black = new Button("     ");
        black.setBackground(Color.BLACK);
        this.add(black);
        black.addActionListener(e -> currentcolor = Color.BLACK);
        
        //Creating an object from JCheckbox class(Source)
        fill= new JCheckBox("Fill Shape");
        
        //Creating object for the buttons
        linebtn = new JButton("Line");
        rectbtn = new JButton("Rectangle");       
        ovalbtn = new JButton("Oval");
        freehandbtn = new JButton("Free Hand");
        //Adding the all the buttons
        add(fill);
        add(linebtn);   
        add(rectbtn);
        add(ovalbtn);
        add(freehandbtn);
        
        //Fill 
        //Registering the listener with the source
          fill.addItemListener(new ItemListener(){
            @Override
            
            public void itemStateChanged(ItemEvent e) {
                // if the checkbox is checked
                filled = e.getStateChange()== ItemEvent.SELECTED;
                if(filled){
                    repaint(); 
                }
            }

        });
        //shapes
        //Creating the mouse event for drawing th shape
         this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startpoint=e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endpoint=e.getPoint();
                //storing the shape
                if(isline){
                    line.add(new Cooridnates(startpoint,endpoint,currentcolor));
                }
                if(isrectangle){
                    rectangle.add(new Cooridnates(startpoint,endpoint,currentcolor,filled));
                }
                if(isoval){
                    oval.add(new Cooridnates(startpoint,endpoint,currentcolor,filled));
                }
                shapetype.add(currentshape);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        } );
         //Free Hand Drawing point 3
         //creating mouse motion listener 
         this.addMouseMotionListener(new MouseMotionListener(){ 
            @Override
            public void mouseDragged(MouseEvent e) {

                if(isfree){
                    //storing the freehand
                    freehand.add(new Cooridnates(e.getX(),e.getY(),currentcolor));
                }
                if(iseraser){
                    //storing the eraser
                  eraserarray.add(new Cooridnates(e.getX(), e.getY(),45,50) );
                 
                    }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
             
         });

        //Line Button point 2
        linebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              currentshape = 1;
              isline=e.getSource()==linebtn;
              isrectangle=false;
              isoval=false;
              isfree=false;
              iseraser=false;
              
            }
        });
        
        //Rectangle Button point 2
        rectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            currentshape = 2;
            isrectangle=e.getSource()==rectbtn;
            isline=false;
            isoval=false;
            isfree=false;
            iseraser=false;
            
            }
            });
      
        //Oval Button point 2
        ovalbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentshape = 3;
                isoval=e.getSource()==ovalbtn;
                isline=false;
                isrectangle=false;  
                isfree=false;
                iseraser=false;
            }
            });
        //FreeHand Button point 3
        freehandbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentshape=4;
                isfree=e.getSource()==freehandbtn;
                isline=false;
                isrectangle=false;
                isoval=false;   
                iseraser=false;
            }  
        });
        
        //eraser point 4
        eraser = new JButton("Eraser");
        add(eraser);
        eraser.addActionListener(e -> {
            iseraser= e.getSource()==eraser;
            isline=false;
            isrectangle=false;
            isoval=false;
            isfree=false;
             });

        //clear all point 5
        clearall = new JButton("Clear All");
        add(clearall);
        clearall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count = 0; 
                repaint();
        }  
});
        //undo bouns point 1
    callbackbtn= new JButton("Return");
    add(callbackbtn);
    callbackbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callback=0;
                repaint();
            }
            
            
        
    });
    save = new JButton("Save");
        add(save);
        save.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        paint(g);
        g.dispose();

        try {
            //File file =new  File("Image.png");
            ImageIO.write(img, "png", new  File("Image.png"));
           // JOptionPane.showMessageDialog(null, "Save to"+file.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Save");
}
});
    }
    
    //paint method 
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        //storing the shapes in the array
            for(int type: shapetype){
                //check the shape type
            switch (type) {
                case 1:
                    //line
                    for(Cooridnates cooridnates : line){
                        g.setColor(cooridnates.color);
                        g.drawLine(cooridnates.start.x, cooridnates.start.y, cooridnates.end.x, cooridnates.end.y );
                    }
                    break;
                case 2:
                    //rectangle
                    for (Cooridnates cooridnates : rectangle) {
                        //declaring the variables for the width and the height
                        int height = Math.abs(cooridnates.end.y - cooridnates.start.y);
                        int width = Math.abs(cooridnates.end.x - cooridnates.start.x);
                        //if the the checkbox is checked
                        if (cooridnates.isfill) {
                            g.setColor(cooridnates.color);
                            g.fillRect(cooridnates.start.x, cooridnates.start.y, width, height);
                        }
                    else {
                             g.setColor(cooridnates.color);
                            g.drawRect(cooridnates.start.x, cooridnates.start.y, width, height);
                        }
                    }
                    break;
                case 3:
                    //oval
                    for(Cooridnates cooridnates : oval){
                        //declaring the variables for the width and the height
                        int height = Math.abs(cooridnates.end.y - cooridnates.start.y);
                        int width = Math.abs(cooridnates.end.x - cooridnates.start.x);
                        //if the the checkbox is checked
                        if (cooridnates.isfill) {
                            g.setColor(cooridnates.color);
                            g.fillOval(cooridnates.start.x, cooridnates.start.y, width, height);
                        }
                        else {
                             g.setColor(cooridnates.color);
                            g.drawOval(cooridnates.start.x, cooridnates.start.y, width, height);
                        }
                    }       break;
                case 4:
                    //freehand
                     //checking that the size of the arraylist is greater 1
                    if ( freehand.size()>1) {
                        //array for the polylines 
                        int[] arrayx = new int[freehand.size()];
                        int[] arrayy = new int[freehand.size()];
                        int n = Math.min(freehand.size(), freehand.size());
                        for (int i = 1; i < n; i++) {
                               arrayx[i] = freehand.get(i).x;
                               arrayy[i] = freehand.get(i).y;
                            }
                        for(Cooridnates y:freehand ){
                        g.setColor(y.color);
                        g.drawPolyline(arrayx, arrayy,n);
                        }
                        }
                       break; 
                    }
            }
            // for the eraser
            g.setColor(getBackground());
                for (Cooridnates er: eraserarray){
                g.fillRect(er.x, er.y, er.w, er.h);
            }
           
            //for clearall
           if (count == 0) {
            rectangle.clear();
            line.clear();
            oval.clear();
            freehand.clear();
            count = 1;
            }
           
           //bouns point 1 undo
        if (callback == 0 && !shapetype.isEmpty()) {

            switch (shapetype.getLast()) {
                case 1:
                    if (!line.isEmpty()) {
                        line.removeLast();
                        shapetype.removeLast();
                    }
                    callback = 1;
                    break;
                case 2:
                    if (!rectangle.isEmpty()) {
                        rectangle.removeLast();
                        shapetype.removeLast();

                    }
                    callback = 1;
                    break;
                case 3:
                    if (!oval.isEmpty()) {
                        oval.removeLast();
                        shapetype.removeLast();
                    }
                    callback = 1;
                    break;
                case 4:
                    if (!freehand.isEmpty()) {
                        freehand.removeLast();
                        shapetype.removeLast();
                    }
                    callback = 1;
                    break;
            }
        }


    }
}
            
            
                
            
            



