package com.dxw.musicGenDemo;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

/**
 * Created by Derek on 3/11/2015.
 */
public class DemoListener implements KeyListener {

    private boolean enableTimer = true;
    private long timeStamp1 = -1;
    private long timeStamp2 = -1;
    private long timeStamp3 = -1;
    private long timeStamp4 = -1;

    private DemoPlayer player;

    private Color pressedColor = Color.RED;

    private JButton[] keyboardKeys = new JButton[4];

    private String[] letters = {"key 1","key 2","key 3","key 4"};

    public void setButtons(JFrame jf){
        for(int i = 0; i < letters.length; i++) {
            keyboardKeys[i] = new JButton(letters[i]); // make the button
            keyboardKeys[i].addKeyListener(this); // add the keylistener to this class
            jf.add(keyboardKeys[i]); // add the button to the your frame
        }
    }

    public void setPlayer(){
        try {
            player = new DemoPlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.out.println("error from realtime player");
        }
    }

    public void playNext(Boolean b){
        if(b){
            player.playNextNote();
        }else{
            player.playNode();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar(); // gets the key being pressed

        switch(key) {
            case '1':
                if (!enableTimer||timeStamp1<0||(new Date()).getTime()-timeStamp1>=1000) {
                    timeStamp1 = new Date().getTime();
                    keyboardKeys[0].setBackground(pressedColor);
                    if(Math.abs(timeStamp1-timeStamp2)<200||Math.abs(timeStamp1-timeStamp3)<200||Math.abs(timeStamp1-timeStamp4)<200){
                        playNext(false);
                    }else{
                        playNext(true);
                    }
                }
                break;
            case '2':
                if (!enableTimer||timeStamp2<0||(new Date()).getTime()-timeStamp2>=1000) {
                    timeStamp2 = new Date().getTime();
                    keyboardKeys[1].setBackground(pressedColor);
                    if(Math.abs(timeStamp2-timeStamp1)<200||Math.abs(timeStamp2-timeStamp3)<200||Math.abs(timeStamp2-timeStamp4)<200){
                        playNext(false);
                    }else{
                        playNext(true);
                    }
                }
                break;
            case '3':
                if (!enableTimer||timeStamp3<0||(new Date()).getTime()-timeStamp3>=1000) {
                    timeStamp3 = new Date().getTime();
                    keyboardKeys[2].setBackground(pressedColor);
                    if(Math.abs(timeStamp3-timeStamp1)<200||Math.abs(timeStamp3-timeStamp2)<200||Math.abs(timeStamp3-timeStamp4)<200){
                        playNext(false);
                    }else{
                        playNext(true);
                    }
                }
                break;
            case '4':
                if (!enableTimer||timeStamp4<0||(new Date()).getTime()-timeStamp4>=1000) {
                    timeStamp4 = new Date().getTime();
                    keyboardKeys[3].setBackground(pressedColor);
                    if(Math.abs(timeStamp4-timeStamp1)<200||Math.abs(timeStamp4-timeStamp2)<200||Math.abs(timeStamp4-timeStamp3)<200){
                        playNext(false);
                    }else{
                        playNext(true);
                    }
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar(); // gets the key being released

        switch(key) {
            case '1':
                keyboardKeys[0].setBackground(null); // sets the color to the default JButton color
                //player.endNote();
                break;
            case '2':
                keyboardKeys[1].setBackground(null);
                //player.endNote();
                break;
            case '3':
                keyboardKeys[2].setBackground(null);
                //player.endNote();
                break;
            case '4':
                keyboardKeys[3].setBackground(null);
                //player.endNote();
                break;
        }
    }
}
