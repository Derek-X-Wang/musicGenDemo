package com.dxw.musicGenDemo;

/**
 * Created by Derek on 3/9/2015.
 */

import org.jfugue.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("musicGenDemo");
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        autoMGD(myFrame);
        myFrame.setVisible(true);
    }

    // use this function to set up manual version(1) of Music Generation Demonstration(MGD)
    // Classes "DemoListener" and "DemoPlayer" are used for this manual MGD
    private static void manualMGD(JFrame myFrame){
        myFrame.setSize(500,100);
        myFrame.setLayout(new FlowLayout());
        DemoListener listener = new DemoListener();
        listener.setButtons(myFrame);
        listener.setPlayer();
    }

    // use this function to set up automatic version(2) of Music Generation Demonstration(MGD)
    private static void autoMGD(JFrame myFrame){

    }

}
