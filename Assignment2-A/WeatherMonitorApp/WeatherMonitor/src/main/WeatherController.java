/**
 * 
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author Tom
 *
 */
public class WeatherController {
	/**
	 * @param args
	 */
		
	public static void main(String[] args) throws Exception {
		
		// Create the WeatherWebService object
		WeatherWebService webService = new MelbourneWeather2();
		
		// Create a LocationList object that contains keys for each of the locations (all objects defaulted to null)
		LocationList locationList = constructLocationList(webService);
		
		JFrame window = createAndShowUI();
		
	}
	
	private static LocationList constructLocationList(WeatherWebService webService) {
		
		String[] locations = webService.getAllLocations();
		return new LocationList(locations);
		
	}
	
	private static JFrame createAndShowUI() {
        JFrame frame = new JFrame("Melbourne Weather Application");
        frame.setLayout(new GridLayout(0, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(frame);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        
        return frame;
    }

    private static void initComponents(final JFrame frame) {

        final JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
            
        JLabel _lbl = new JLabel("Testing");//make label and assign text in 1 line
        panel.add(_lbl);

        JButton button = new JButton("Add label");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel _lbl = new JLabel("Label ");//make label and assign text in 1 line

                panel.add(_lbl);//add label we made

                panel.revalidate();
                panel.repaint();

                frame.pack();//so our frame resizes to compensate for new components

            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
    }

}
