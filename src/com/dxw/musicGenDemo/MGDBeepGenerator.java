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
    private int delay;
    private int delayVarianceLimit;
    private MGDNoteComputingUnit ncu;
    private List<Integer> readyReaderList;
    private Map<Integer,Long> coolingReaderDict;
    private Random random;
    private double failure;
    // order: normal, discount, under 100 dollar
    private double[] userType = {0.58,0.34,0.08};

    MGDBeepGenerator(){
        delay = 500;
        delayVarianceLimit = 20;
        // Maybe better to use util.Timer
        timer = new Timer(delay,new TimerListener());
        ncu = new MGDNoteComputingUnit();
        setReadyReaderList(8);
        coolingReaderDict = new HashMap<Integer, Long>();
        random = new Random();
        failure = 0.2;
    }

    private void delayRandomAdjustment(){
        double r = Math.random();
        int randomVariance = random.nextInt(delayVarianceLimit);
        if(r<0.5){
            delay = delay - randomVariance;
        }else{
            delay = delay + randomVariance;
        }
        if(delay<=150){
            delay = 500;
        }
        if(delay>=5000){
            delay = 500;
        }
        timer.setDelay(delay);
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
        int listSize = readyReaderList.size();
        if (listSize == 0){
            return -1;
        }
        int rand = random.nextInt(readyReaderList.size());
        int readerId = readyReaderList.get(rand);
        readyReaderList.remove(rand);
        coolingReaderDict.put(readerId,(new Date()).getTime());
        return readerId;
    }

    private boolean randomGenerateValidation(){
        double r = Math.random();
        if(r<failure){
            return false;
        }else{
            return true;
        }
    }

    private int randomGenerateUserType(){
        double r = Math.random();
        double cumulation = 0;
        for(int i=0;i<userType.length;i++){
            cumulation = cumulation + userType[i];
            if(r<cumulation){
                return i;
            }
        }
        return -1;
    }

    public void setReadyReaderList(int readers){
        readyReaderList = new ArrayList<Integer>();
        for(int i = 0; i < readers; i++){
            readyReaderList.add(i);
        }
    }

    public void setFailureRate(double f){
        failure = f;
    }

    public void setUserTypeRate(double first,double second, double third){
        userType[0] = first;
        userType[1] = second;
        userType[2] = third;
    }

    public void setDelay(int newDelay){
        delay = newDelay;
    }

    public void setDelayImmediately(int newDelay){
        timer.setDelay(newDelay);
    }

    public void setDelayVarianceLimit(int delayVarianceLimit) {
        this.delayVarianceLimit = delayVarianceLimit;
    }

    public void setHarmoniousTimeOptionInNCU(int harmoniousTimeOption){
        ncu.setHarmoniousTimeOption(harmoniousTimeOption);
    }

    public void setDefaultInstrument(int newDefaultInstrument){
        ncu.setDefaultInstrument(newDefaultInstrument);
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
            int reederId = randomGenerateReaderId();
            if (reederId==-1){
            }else {
                ncu.triggerBeep(reederId, randomGenerateValidation(),randomGenerateUserType(),(new Date()).getTime());
                delayRandomAdjustment();
            }
        }
    }
}
