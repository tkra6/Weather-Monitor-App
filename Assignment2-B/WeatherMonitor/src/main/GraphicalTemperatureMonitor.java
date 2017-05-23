package main;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 * 
 * @author douglas
 * A graphical monitor that displays and stores information about Temperature
 *
 */
public class GraphicalTemperatureMonitor extends WeatherMonitor {
	
	// References to the display and labels of the monitor
	private JFrame frame;
	private JLabel dataLabel;
	private JLabel locationLabel;
	// References to what the Monitor is storing
	private Location location;
	private WeatherData temperature;
	
	private SimpleDateFormat dateTimeFormat;
	private final TimeSeries temperatureSeries;
	
	
	public GraphicalTemperatureMonitor(Location subject) {
		
		this.dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
		
		this.temperatureSeries = new TimeSeries("Temperature", Minute.class);
		
		this.requiredData = new DataType[]{DataType.temperature};
		this.location = subject;
		this.location.attach(this);
	    this.update();
	    
	}
	
	/**
	 * Gets the latest data from its location and then updates the window to display the new data
	 */
	@Override
	public void update() {
		
		this.temperature = this.location.getState(DataType.temperature);
		
		try {
			// Add the new data to the data sets
			this.temperatureSeries.addOrUpdate(new Minute(this.dateTimeFormat.parse(this.temperature.getTimestamp())), this.getTemperature());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.displayWindow();
	}
	
	public float getTemperature() {
		
		return this.convertToCelsius(this.temperature.getData(), this.temperature.getFormat());
		
	}
	
	public String getLocation() {
		
		return this.location.getLocation();
		
	}
	
	public XYDataset createTemperatureDataSet() throws ParseException {
        
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(this.temperatureSeries);
        
        return dataset;
		
	}
	
	/**
	 * Handles the initial creation of the window
	 */
	public void createWindow() {
		
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(0, 1));
		// This ensures that closing this monitor will not shut down the application
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		
		// Creates instance of graph
		XYDataset dataset;
		try {
			dataset = createTemperatureDataSet();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String chartTitle = this.getLocation() + " - Temperature Over Time";
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            chartTitle, 
	            "Time", 
	            "Temperature (C)",
	            dataset, // DATA SET 1
	            true, 
	            true, 
	            false
	        );
		
		final XYPlot plot = chart.getXYPlot();
		
        plot.mapDatasetToRangeAxis(1, 1);
        
        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        if (renderer instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            rr.setBaseShapesVisible(true);
            rr.setShapesFilled(true);
        }
        
        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(this.dateTimeFormat);
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(650, 400));
        
		
	    this.frame.add(chartPanel);
 	   	this.frame.pack();
 	   	this.frame.setVisible(true);
		
 	   	WeatherMonitor mon = this;
 	   	// Listens for the monitor to be closed and handles the detaching of it when it happens
 	   	this.frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
	    	  location.detach(mon);
	          frame.dispose();
    	  }
 	   	});
		
	}

	/**
	 * Creates the window if it isn't already created, otherwise updates it with the current value of getRenderContent()
	 */
	public void displayWindow() {
		
		if (this.frame == null) {
			
			this.createWindow();
			
		} else {
			
			frame.pack();
			
		}
		
	}
	
	/**
	 * Handles the logic for generating what to display in the GUI
	 * @return The string that will be displayed in the GUI
	 */
	private String getRenderContent() {
		
		if (this.temperature == null || this.temperature.getFormat() == null) {
			
			return ("Temperature data is not currently available.");
			
		} else {
			
			return ("Temperature is currently " + this.getTemperature() + " degrees Celsius.");
			
		}
		
	}

}
