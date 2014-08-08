import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * A Boat that can be moved around.
 */
public class BoatShape implements MoveableShape
{
	/**
	 * constructs a boat item.
	 * @param x the left of the bounding rectangle
	 * @param y the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */
	public BoatShape(int x, int y, int width)
	{
		this.x = x;
		this.y = y;
		this.width = width;
	}

	/**
	 * set the seepd at which a shape should move
    	 * @param dx how much the shape should move on the x axis
         * @param dy how much the shape should move on the y axis
         */
	public void setDeltas(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	/**
         Moves the boat by a given amount.
         @param dx the amount to translate in x-direction
         @param dy the amount to translate in y-direction
         */
	public void translate(int dx, int dy)
	{
		x += dx;
		y += dy;
	}

	/*updates x and y based on own deltas*/
	public void selfTranslate()
	{
		x += this.dx;
		y += this.dy;
	}


	/*create a label for displaying shape*/
	private void makeLabel()
	{
	        boatIcon = new ShapeIcon(this, width, width);
	        boatLabel.setIcon(boatIcon);
	}
	
	/*return the label to be displayed*/
	public JLabel getLabel()
	{
		if(boatLabel.getIcon() == null)
			makeLabel();
		return boatLabel;
	}

	/*get the X coord*/
	public int getX()
	{
		return x;
	}

	/*get the Y coord*/
	public int getY()
	{
		return y;
	}

	public void autoWrap(int width, int height)
   	{
	   if (x>width)
		   x=0-this.width;
	   if (x<0-this.width)
		   x=width;
	   if (y>height)
		   y=0-this.width;
	   if (y<0-this.width)
		   y=height;
   	}

	public void draw(Graphics2D g2)
	{
		
		/** POLE */
		double poleHeight = width/2;//set the height of the pole relative
		double poleWidth = width/15;
		double poleXstart = x+(width/2);
		Rectangle2D.Double pole
		   = new Rectangle2D.Double(poleXstart, y, poleWidth, poleHeight);
		
		/** SAIL */
		int sailXstart = (int)(poleXstart+poleWidth+(width/25));
		int sailYstart = (int)(y+poleHeight/20);
		int[] xSailPoints //x values for sail polygon
		   = {sailXstart, sailXstart, sailXstart+(width/4)};
	        int[] ySailPoints //y values for sail polygon	
		   = {sailYstart, sailYstart+((int)poleHeight-(int)poleHeight/10), sailYstart+((int)poleHeight-(int)poleHeight/10)};

		//make a polygon for the sail (a triangle)
		Polygon sail = new Polygon(xSailPoints, ySailPoints, 3);

		/** BODY */
		int bodyYstart = y + (int)poleHeight;//used to draw body below pole
		double slopeDiff = width/4;
		int[] xBodyPoints //x values for body polygon 
		   = {x, x+width, (int)((x+width)-(slopeDiff)), (int)(x+(slopeDiff))};
		int[] yBodyPoints //y values for body polygon
		   = {bodyYstart,  bodyYstart, (int)(bodyYstart+slopeDiff), (int)(bodyYstart+slopeDiff)};
		//make a polygon for the body (a trapezoid)
		Polygon body = new Polygon(xBodyPoints, yBodyPoints, 4);

		g2.setColor(new Color(139,69,19));
		g2.draw(pole);
		g2.fill(pole);
		g2.setColor(Color.WHITE);
		g2.fillPolygon(sail);
		g2.setColor(Color.BLACK);
		g2.draw(sail);
		g2.setColor(Color.RED);
		g2.draw(body);
		g2.fillPolygon(body);
	}

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private ShapeIcon boatIcon;
	private JLabel boatLabel = new JLabel();
	
}
