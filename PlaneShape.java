import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * A Boat that can be moved around.
 */
public class PlaneShape implements MoveableShape
{
	/**
	 * constructs a plane item.
	 * @param x the left of the bounding rectangle
	 * @param y the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */
	public PlaneShape(int x, int y, int width)
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
	        planeIcon = new ShapeIcon(this, width, width);
	        planeLabel.setIcon(planeIcon);
	}
	
	/*return the label to be displayed*/
	public JLabel getLabel()
	{
		if(planeLabel.getIcon() == null)
			makeLabel();
		return planeLabel;
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
		
		/* TAIL */
		int tailBottom = y+(width/4);
		int[] xTailPoints //x values for tail polygon
		   = {x, x, x+(width/4)};
		int[] yTailPoints //y values for tail polygon
		   = {y, tailBottom, tailBottom};
		Polygon tail = new Polygon(xTailPoints, yTailPoints, 3);

		/* BODY */
		int bodyLength = width - (width/4);
		int bodyHeight = width/4;
		Rectangle2D.Double body
			= new Rectangle2D.Double(x, tailBottom, 
					bodyLength, bodyHeight);

		/* NOSE */
		int noseXstart = x+bodyLength;
		int[] xNosePoints //x values for nose polygon
			= {noseXstart, noseXstart, noseXstart+(width/4)};
		int[] yNosePoints //y values for nose polygon
			= {tailBottom, tailBottom+bodyHeight,
			       	tailBottom+bodyHeight/2};
		Polygon nose = new Polygon(xNosePoints, yNosePoints, 3);

		/* WING */
		int planeMidXPoint = x+width/2;
		int bodyMidYPoint = tailBottom+bodyHeight/2;
		int[] xWingPoints //x values for wing polygon
			= {planeMidXPoint-width/8, planeMidXPoint, 
				planeMidXPoint+width/8};
		int[] yWingPoints //y values for wing polygon
			= {bodyMidYPoint, bodyMidYPoint+width/4, 
				bodyMidYPoint};
		Polygon wing = new Polygon(xWingPoints, yWingPoints, 3);

		g2.setColor(Color.BLUE);
		g2.draw(tail);
		g2.fillPolygon(tail);
		g2.draw(body);
		g2.fill(body);
		g2.draw(nose);
		g2.fillPolygon(nose);
		g2.setColor(Color.BLACK);
		g2.draw(wing);
		g2.fillPolygon(wing);
	}

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private ShapeIcon planeIcon;
	private JLabel planeLabel = new JLabel();
}
