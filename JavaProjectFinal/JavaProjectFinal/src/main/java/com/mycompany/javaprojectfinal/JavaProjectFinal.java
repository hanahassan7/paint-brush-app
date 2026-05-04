/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javaprojectfinal;

import javax.swing.JFrame;

/**
 *
 * @author Hana_
 */
public class JavaProjectFinal {

    public static void main(String[] args) {
        JFrame myframe = new JFrame();
        MyPanel mypanel = new MyPanel();
        myframe.setContentPane(mypanel);
        myframe.setTitle("Paint Brush");
        myframe.setSize(1000,800);
        myframe.setVisible(true);
    }
}
