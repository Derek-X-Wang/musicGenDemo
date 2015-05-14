package com.dxw.musicGenDemo;

/**
 * Created by Derek on 3/9/2015.
 */

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.TestFrame;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.toolbar.WhiteSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main implements ActionListener {
    public static void main(String[] args) {

        // You should work with UI (including installing L&F) inside Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                // Install WebLaF as application L&F
                WebLookAndFeel.install();

                // Create you Swing application here
                // JFrame frame = ...

                JFrame myFrame = new JFrame("musicGenDemo");
                myFrame.setLocationRelativeTo(null);
                myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                autoMGD(myFrame);
                myFrame.setVisible(true);
//                final JTextArea textArea = new JTextArea ( "Simple text area" );
//                final JScrollPane scrollPane = new JScrollPane ( textArea );
//                scrollPane.setPreferredSize ( new Dimension ( 300, 150 ) );
//                scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//                scrollPane.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
//
//                final JProgressBar progressBar = new JProgressBar ();
//                progressBar.setIndeterminate ( true );
//
//                final JButton ok = new JButton ( "Ok" );
//                final JButton cancel = new JButton ( "Cancel" );
//
//                TestFrame.show(new GroupPanel(GroupingType.fillFirst, 5, false, scrollPane, progressBar,
//                        new GroupPanel(GroupingType.fillFirst, 5, new WhiteSpace(), ok, cancel)), 5);
            }
        } );

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
        myFrame.setSize(350,400);
        myFrame.setLayout(new FlowLayout());
        MenuPanel menuPanel = new MenuPanel();
        myFrame.add(menuPanel);

        //Player player = new Player();
        //player.play("Cq. Cq. Gq. Gq. Aq. Aq. Gh. Fq. Fq. Eq. Eq. Dq. Dq. Ch. Gq. Gq. Fq. Fq. Eq. Eq. Dh. Gq. Gq. Fq. Fq. Eq. Eq. Dh. Cq. Cq. Gq. Gq. Aq. Aq. Gh. Fq. Fq. Eq. Eq. Dq. Dq. Ch.");
        //MGDBeepGenerator beepGenerator =  new MGDBeepGenerator();
        //beepGenerator.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
