import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
   A car that can be moved around.
*/
public class CarShape implements MoveableShape
{
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public CarShape(int x, int y, int width)
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
	carIcon = new ShapeIcon(this, width, width);
	carLabel.setIcon(carIcon);
   }
	
   /*return the label to be displayed*/
   public JLabel getLabel()
   {
	if(carLabel.getIcon() == null)
		makeLabel();
	return carLabel;
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
      Rectangle2D.Double body
            = new Rectangle2D.Double(x, y + width / 6, 
                  width - 1, width / 6);
      Ellipse2D.Double frontTire
            = new Ellipse2D.Double(x + width / 6, y + width / 3, 
                  width / 6, width / 6);
      Ellipse2D.Double rearTire
            = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,
                  width / 6, width / 6);

      // The bottom of the front windshield
      Point2D.Double r1
            = new Point2D.Double(x + width / 6, y + width / 6);
      // The front of the roof
      Point2D.Double r2
            = new Point2D.Double(x + width / 3, y);
      // The rear of the roof
      Point2D.Double r3
            = new Point2D.Double(x + width * 2 / 3, y);
      // The bottom of the rear windshield
      Point2D.Double r4
            = new Point2D.Double(x + width * 5 / 6, y + width / 6);
      Line2D.Double frontWindshield
            = new Line2D.Double(r1, r2);
      Line2D.Double roofTop
            = new Line2D.Double(r2, r3);
      Line2D.Double rearWindshield
            = new Line2D.Double(r3, r4);
      
      g2.draw(body);
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.draw(frontWindshield);
      g2.draw(roofTop);
      g2.draw(rearWindshield);
   }
   
   private int x;
   private int y;
   private int dx;
   private int dy;
   private int width;
   private ShapeIcon carIcon;
   private JLabel carLabel = new JLabel();
}
