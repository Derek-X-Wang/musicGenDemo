package com.dxw.musicGenDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Derek on 5/15/2015.
 */
public class MenuPanel extends JPanel implements ActionListener {

    protected JLabel actionLabel;
    private MGDBeepGenerator beepGenerator;
    MenuPanel(MGDBeepGenerator bg){
        beepGenerator = bg;

        setLayout(new BorderLayout());

        //讀卡器數量
        JTextField readerNumber = new JTextField(10);
        readerNumber.setActionCommand("readerNumber");
        readerNumber.addActionListener(this);
        JLabel readerNumberLabel = new JLabel("Number of Readers: ");
        readerNumberLabel.setLabelFor(readerNumber);
        //人流量
        JTextField arrivingRate = new JTextField(10);
        arrivingRate.setActionCommand("arrivingRate");
        arrivingRate.addActionListener(this);
        JLabel arrivingRateLabel = new JLabel("Arriving Rate: ");
        arrivingRateLabel.setLabelFor(arrivingRate);
        //人流量方差
        JTextField arrivingRateVariance = new JTextField(10);
        arrivingRateVariance.setActionCommand("arrivingRateVariance");
        arrivingRateVariance.addActionListener(this);
        JLabel arrivingRateVarianceLabel = new JLabel("Arriving Rate Variance: ");
        arrivingRateVarianceLabel.setLabelFor(arrivingRateVariance);
        //失敗概率
        JTextField failureRate = new JTextField(10);
        failureRate.setActionCommand("failureRate");
        failureRate.addActionListener(this);
        JLabel failureRateLabel = new JLabel("Failure Rate: ");
        failureRateLabel.setLabelFor(failureRate);
        //和諧時限
        JTextField harmoniousTimeOption = new JTextField(10);
        harmoniousTimeOption.setActionCommand("harmoniousTimeOption");
        harmoniousTimeOption.addActionListener(this);
        JLabel harmoniousTimeOptionLabel = new JLabel("Harmonious Time Option: ");
        harmoniousTimeOptionLabel.setLabelFor(harmoniousTimeOption);
        //默認樂器
        JTextField defaultInstrument = new JTextField(10);
        defaultInstrument.setActionCommand("defaultInstrument");
        defaultInstrument.addActionListener(this);
        JLabel defaultInstrumentLabel = new JLabel("Default Instrument: ");
        defaultInstrumentLabel.setLabelFor(defaultInstrument);

        //Create a label to put messages during an action event.
        actionLabel = new JLabel("...Waiting for your action");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {readerNumberLabel, arrivingRateLabel, arrivingRateVarianceLabel,failureRateLabel,harmoniousTimeOptionLabel,defaultInstrumentLabel};
        JTextField[] textFields = {readerNumber, arrivingRate, arrivingRateVariance,failureRate,harmoniousTimeOption,defaultInstrument};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Variables"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        add(textControlsPane);
    }

    private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, GridBagLayout gridbag, Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }

        //Temp simple control for demo
        JButton start = new JButton("Start");
        start.setActionCommand("start");
        JButton stop = new JButton("Stop");
        stop.setActionCommand("stop");

        container.add(start,c);
        container.add(stop,c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("start".equals(e.getActionCommand())) {
            actionLabel.setText("Starttttttttting");
            beepGenerator.start();
        } else if ("stop".equals(e.getActionCommand())) {
            actionLabel.setText("Stopppppppppping");
            beepGenerator.stop();
        }else {

            String prefix = "Change ";
            String input = ((JTextField) e.getSource()).getText();
            if ("readerNumber".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "reader number to " + input);
            } else if ("arrivingRate".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "arriving rate to " + input);
                beepGenerator.setDelay(Integer.parseInt(input));
            } else if ("arrivingRateVariance".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "arriving rate variance to " + input);
                beepGenerator.setDelayVarianceLimit(Integer.parseInt(input));
            } else if ("failureRate".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "failure rate to " + input);
                beepGenerator.setFailureRate(Double.parseDouble(input));
            } else if ("harmoniousTimeOption".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "harmonious time option to " + input);
                beepGenerator.setHarmoniousTimeOptionInNCU(Integer.parseInt(input));
            } else if ("defaultInstrument".equals(e.getActionCommand())) {
                actionLabel.setText(prefix + "default instrument to " + input);
                beepGenerator.setDefaultInstrument(Integer.parseInt(input));
                //Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
