package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MonitorWindow extends JFrame {
	
	GridLayout windowLayout = new GridLayout(0,1);
	private String[] content;
	private JPanel windowContent;
	
	public MonitorWindow(String name, String[] content) {
        super(name);
        setResizable(false);
        this.content = content;
    }
	
	private void addComponentsToPane(final Container pane) {
        
		if (this.windowContent != null) {
			
			pane.remove(this.windowContent);
			
		}
		
		this.windowContent = new JPanel();
        windowContent.setLayout(windowLayout);
        
        //Add each label to the window
        for (String contentString : content) {
        	
        	windowContent.add(new Label(contentString));
        	
        }
        
        pane.add(windowContent, BorderLayout.NORTH);
    }
	
	private void updateContent(String[] content) {
		
		this.content = content;
		
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Set up the content pane.
        this.addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Modify the GUI data and redisplay the window
     * For thread safety, this method is invoked from the event dispatch thread
     */
    public void modifyAndRedisplayGUI(String[] content) {
    	
    	// Update the content of the window
    	this.updateContent(content);
    	// Re-setup the components of the window
    	this.addComponentsToPane(this.getContentPane());
    	// Display the window
    	this.pack();
    	
    	
    }
	
}
