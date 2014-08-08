import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * A Clock that can be moved around.
 */
public class ClockShape implements MoveableShape
{
	/**
	 * constructs a clock item.
	 * @param x the left of the bounding rectangle
	 * @param y the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */
	public ClockShape(int x, int y, int width)
	{
		this.x = x;
		this.y = y;
		this.width = width;
	}

        public void setDeltas(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

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
	        clockIcon = new ShapeIcon(this, width, width);
	        clockLabel.setIcon(clockIcon);
	}
	
	/*return the label to be displayed*/
	public JLabel getLabel()
	{
		if(clockLabel.getIcon() == null)
			makeLabel();
		return clockLabel;
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
		/* BODY */
		Ellipse2D.Double body
			= new Ellipse2D.Double(x,y,width,width);
		
		/* HANDS */
		Point2D.Double r1
			= new Point2D.Double(x+width/2,y+width/2);
		Point2D.Double r2
			= new Point2D.Double(x+width/2,y+width/10);
		Point2D.Double r3
			= new Point2D.Double((x+width)-width/4,y+width/2);
		Line2D.Double minHand
			= new Line2D.Double(r1, r2);
		Line2D.Double hourHand
			= new Line2D.Double(r1, r3);

		g2.setColor(Color.ORANGE);
		g2.fill(body);
		g2.setColor(Color.BLACK);
		g2.draw(body);
		g2.draw(minHand);
		g2.draw(hourHand);
	
	}

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private ShapeIcon clockIcon;
	private JLabel clockLabel = new JLabel();
}
