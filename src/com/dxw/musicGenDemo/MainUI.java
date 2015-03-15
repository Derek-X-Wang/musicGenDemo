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


public class MainUI {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("musicGenDemo");
        myFrame.setSize(500,100);
        myFrame.setLocationRelativeTo(null);
        myFrame.setLayout(new FlowLayout());
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DemoListener listener = new DemoListener();
        listener.setButtons(myFrame);
        listener.setPlayer();
        myFrame.setVisible(true);

    }

}
