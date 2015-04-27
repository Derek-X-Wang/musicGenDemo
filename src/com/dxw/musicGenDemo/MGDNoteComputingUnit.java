package com.dxw.musicGenDemo;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Derek on 4/20/2015.
 */
public class MGDNoteComputingUnit {

    private MGDPlayer player;
    MGDNoteComputingUnit(){
        try {
            player = new MGDPlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.out.println("error from realtime player");
        }
    }

    public void trigerBeep(int readId){

    }
}
