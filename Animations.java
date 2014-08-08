import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

	/**
	   This program implements an animation that moves shapes.
	*/
	public class Animations{

		private final int ICON_WIDTH = 600;
		private final int ICON_HEIGHT = 600;
		private final int CAR_WIDTH = 100;
		private final int BOAT_WIDTH = 100;
		private final int PLANE_WIDTH = 100;
		private final int CLOCK_WIDTH = 100;
		private int SCREEN_WIDTH = 600;
		private int SCREEN_HEIGHT = 600;
		private Random rand = new Random();
		private int randomX;
		private int randomY;
		private ArrayList<MoveableShape> shapeList = new ArrayList<MoveableShape>();
		private JFrame frame = new JFrame("Shapes");
		private javax.swing.Timer T;

		private boolean frameSetup = false; 
		private boolean running = false;
		
		private JButton hideButton = new JButton("Hide");
		private JButton exitButton = new JButton("Exit");
		private int buttonWidth = 20; //the width that the buttons should be

		private JPanel mainPanel = new JPanel();
		private JPanel buttonPanel = new JPanel();
	
		
	   /*create buttons with functions to hide or close window*/
	   private void createButtons(){
		   buttonPanel.setLayout(new FlowLayout());
		   hideButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent event){
				   frame.setVisible(false);
				   stop();
			   }
		   });

		   exitButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent event){
				Iterator<MoveableShape> it //for shapes
					= shapeList.iterator();
				while(it.hasNext()){
					MoveableShape theShape = it.next();
					frame.remove(theShape.getLabel());
				}
				
				   frame.dispose();
				   shapeList.clear();
			   }
		   });


		   buttonPanel.add(hideButton);
		   buttonPanel.add(exitButton);
	   }

	   /*setup the frame with buttons and a place for shapes*/
	   private void setupFrame(){	
	      frame.setLayout(new BorderLayout());
	      frame.pack();
	      frame.setVisible(true);
	      frame.setLocationRelativeTo(null);
	      
	      frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
	      frame.setResizable(false);

	      createButtons();
	      frame.add(buttonPanel, BorderLayout.SOUTH);
	     
	   }

	   /*stop the shapes from moving */
	   private void stop(){
		   if (running){
			   T.stop();
			   running = false;
		   }
	   }

	   /**startes the shapes moving with a timer*/
	   public void run(){
		if (!frameSetup)
			setupFrame();
              
		/*Timer for all shapes.*/
		final int DELAY = 10; // Milliseconds between timer ticks
	        T= new javax.swing.Timer(DELAY, new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Iterator<MoveableShape> it //for shapes
					= shapeList.iterator();
				while(it.hasNext()){
					MoveableShape theShape = it.next();
					theShape.selfTranslate();
					theShape.autoWrap(SCREEN_WIDTH, SCREEN_HEIGHT);
					theShape.getLabel();
					theShape.getLabel().repaint();
					//System.out.println(theShape.getX()+" "+theShape.getY());
				}
		        }
		});
		T.start();
		running = true;

	   }

	   /**add a shape to the frame
	    * @param shapeType string to determine the shape to add currently accepts "boat", "car", "clock", or "plane"
	    */
	   public void addShape(String shapeType){
	      
	      if (!frame.isShowing())
		      frame.setVisible(true);
	      if (!running)
		      run();

	      MoveableShape theShape = null;
	      /*create at a random location*/
	      randomX = rand.nextInt(SCREEN_WIDTH);
	      randomY = rand.nextInt(SCREEN_HEIGHT);

	      /*create the shape*/
	      if (shapeType.equals("boat")){
	          theShape = new BoatShape(randomX, randomY, BOAT_WIDTH);
	      }
	      else if (shapeType.equals("car")){
		  theShape = new CarShape(randomX, randomY, CAR_WIDTH);
	      }
	      else if (shapeType.equals("plane")){
		  theShape = new PlaneShape(randomX, randomY, PLANE_WIDTH);
	      }
	      else if (shapeType.equals("clock")){
		  theShape = new ClockShape(randomX, randomY, CLOCK_WIDTH);
	      }
	      else
	      	  System.out.println("invalid shape");

	      if (theShape != null){
		      /*set random movement*/
		      int randomDX = 0;
		      int randomDY = 0;
		      while (randomDX == 0 && randomDY == 0){
			 randomDX = (rand.nextInt(5)-2);
			 randomDY = (rand.nextInt(5)-2);
		      }

		      theShape.setDeltas(randomDX, randomDY);

		      shapeList.add(theShape);//track shape

		      /*add the shape to the frame*/
		      frame.add(theShape.getLabel());
		      frame.revalidate();
	      }
	   }

	   /*remove the last shape that was added*/
	   public void removeLast(){
		   if (shapeList.size() > 0){
			frame.remove(shapeList.get(shapeList.size()-1).getLabel());
			frame.revalidate();
			frame.repaint();
		   	shapeList.remove(shapeList.size()-1);
		   }
	   }

}
	
