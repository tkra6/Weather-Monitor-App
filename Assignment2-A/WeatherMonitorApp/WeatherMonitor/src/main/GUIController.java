/**
 * 
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author Tom
 *
 *
 some code used from: http://stackoverflow.com/a/14031877
 */
public class GUIController {

	String content;
	
    public GUIController(String content) {
	    this.content = content;
        createAndShowUI();
    }

    private void createAndShowUI() {
        JFrame frame = new JFrame("Melbourne Weather Application");
        frame.setLayout(new GridLayout(2, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(frame);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents(final JFrame frame) {

        final JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
            
        JLabel _lbl = new JLabel(this.content);//make label and assign text in 1 line
        panel.add(_lbl);
//
//        JButton button = new JButton("Add label");
//
//        button.addActionListener(new ActionListener() {
//            int count = 1;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JLabel _lbl = new JLabel("Label " + count);//make label and assign text in 1 line
//
//                panel.add(_lbl);//add label we made
//
//                panel.revalidate();
//                panel.repaint();
//
//                frame.pack();//so our frame resizes to compensate for new components
//
//                count++;
//            }
//        });
//
        frame.add(panel, BorderLayout.CENTER);
//        frame.add(button, BorderLayout.SOUTH);
    }
}

