package com.dxw.musicGenDemo;




import javax.sound.midi.MidiUnavailableException;
import java.util.Date;

/**
 * Created by Derek on 4/20/2015.
 */
public class MGDNoteComputingUnit {

    private MGDPlayer player;
    private String prestoredHardcodedsong = "G5w A5w G5h A5h G5h F5h G5h A5h G5h F5h G5w F5h D5h C5w D5h F5h C5qa0d127 D5w A5w D5h G5h G5h D5h A5w A5h A5h D5h G5h G5h D5h C5h C5h C5h C5h D5h F5h D5h C5h A4w D5w C5w C5-w-";
    private String[] sounds;
    private int count = 0;
    private int harmoniousTimeOption = 200;
    private long lastTimeStamp;
    private String lastNote;

    MGDNoteComputingUnit(){
        sounds = prestoredHardcodedsong.split("\\s+");
        lastTimeStamp = (new Date()).getTime();
        lastNote = sounds[0];
        try {
            player = new MGDPlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.out.println("error from realtime player");
        }
    }

    private int findNoteInPrestoredHardcodedsong(){
        count++;//hehe, if count greater than the range of int, it may crash
        int index = count%sounds.length;
        if(index==0){
            count = 0;
        }
        return count;
    }

    public void triggerBeep(int readId, boolean validation, int userType, long timeStamp){
        String note;
        int noteIndex;
        if((timeStamp-lastTimeStamp)<harmoniousTimeOption){
            note = lastNote;
        }else{
            noteIndex = findNoteInPrestoredHardcodedsong();
            note = sounds[noteIndex];
            lastNote = note;
        }

        if(!validation){
            //failure
            player.playNoteWithInstrumentChanged(note,8);
        }else{
            //success
            switch (userType){
                case 0:
                    player.playNote(note);
                    break;
                case 1:
                    player.playNote(note.substring(0,1)+"#"+note.substring(1,note.length()));
                    break;
                case 2:
                    player.playNote(note);
                    //note = sounds[findNoteInPrestoredHardcodedsong()];
                    player.playNote(note);
                    break;
                default:
                    System.out.println("error from usertype");
            }
        }
    }
}
