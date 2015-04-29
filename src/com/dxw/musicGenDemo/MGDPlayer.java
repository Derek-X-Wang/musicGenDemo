package com.dxw.musicGenDemo;

import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Derek on 4/20/2015.
 */
public class MGDPlayer {

    private RealtimePlayer player;
    private int defaultInstrument;

    MGDPlayer() throws MidiUnavailableException{
        defaultInstrument = 0;
        player = new RealtimePlayer();
    }
    MGDPlayer(int instrument) throws MidiUnavailableException{
        player = new RealtimePlayer();
        defaultInstrument = instrument;
        player.changeInstrument(instrument);
    }

    public void changeInstrument(int instrument){
        player.changeInstrument(instrument);
    }

    public  void playNote(String note){
        player.startNote(new Note(note));
    }

    public void playNoteWithInstrumentChanged(String note, int instrument){
        player.changeInstrument(instrument);
        player.startNote(new Note(note));
        player.changeInstrument(defaultInstrument);
    }
}
