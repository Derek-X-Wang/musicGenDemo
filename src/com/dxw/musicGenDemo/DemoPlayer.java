package com.dxw.musicGenDemo;

import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Derek on 3/11/2015.
 */
public class DemoPlayer {
    private RealtimePlayer player;
    private int count = -1;
    private String song = "Cmaj Amin Dmin Gmaj";//"G E D E C E D E";
    private String[] sounds;
    DemoPlayer() throws MidiUnavailableException{
        player = new RealtimePlayer();
        player.changeInstrument(26);
        sounds = song.split("\\s+");
    }

    public void playNextNote(){
        count++;//hehe, if count greater than the range of int, it may crash
        int index = count%sounds.length;
        player.startNote(new Note(sounds[index]));
    }

    public void playNode(){
        int index = count%sounds.length;
        player.startNote(new Note(sounds[index]));
    }

    public void endNote(){
        int index = count%sounds.length;
        player.stopNote(new Note(sounds[index]));
    }




}
