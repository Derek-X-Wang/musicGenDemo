package com.dxw.musicGenDemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

/**
 * Created by Derek on 4/20/2015.
 */
public class MGDBeepGenerator {

    private Timer timer;
    private MGDNoteComputingUnit ncu;
    private List<Integer> readyReaderList;
    private Map<Integer,Long> coolingReaderDict;
    private Random random;

    MGDBeepGenerator(){
        // Maybe better to use util.Timer
        timer = new Timer(200,new TimerListener());
        ncu = new MGDNoteComputingUnit();
        setReadyReaderList(6);
        coolingReaderDict = new HashMap<Integer, Long>();
        random = new Random();
    }

    private int randomGenerateReaderId(){
        for(Iterator<Map.Entry<Integer,Long>> it = coolingReaderDict.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer,Long> entry = it.next();
            long time = entry.getValue();
            if((new Date()).getTime()-time>=800){
                readyReaderList.add(entry.getKey());
                it.remove();
            }
        }
        int rand = random.nextInt(readyReaderList.size());
        int readerId = readyReaderList.get(rand);
        readyReaderList.remove(rand);
        coolingReaderDict.put(readerId,(new Date()).getTime());
        return readerId;
    }

    public void setReadyReaderList(int readers){
        readyReaderList = new ArrayList<Integer>();
        for(int i = 0; i < readers; i++){
            readyReaderList.add(i);
        }
    }

    public void setDelay(int newDelay){
        timer.setDelay(newDelay);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    private class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ncu.trigerBeep(randomGenerateReaderId());
        }
    }
}
