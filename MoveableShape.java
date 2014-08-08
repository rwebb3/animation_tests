import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   A shape that can be moved around.
*/
public interface MoveableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
      Moves the shape by a given amount.
      @param dx the amount to translate in x-direction
      @param dy the amount to translate in y-direction
   */
   void translate(int dx, int dy);
   /**
    * set the seepd at which a shape should move
    * @param dx how much the shape should move on the x axis
    * @param dy how much the shape should move on the y axis
    */
   public void setDeltas(int dx, int dy);
   /**
    * the shape updates it's own x and y values based on it's dx and dy values
    */
   public void selfTranslate();

   /** shape detects when it's out of bounds 
    * and sets it's x and y values to the oposite extreme
    */
   public void autoWrap(int width, int height);
   /** returns a JLabel 
    * if none exists it will create one from the shapeIcon
    * @return a JLabel of the shape
    */
   public JLabel getLabel();

   public int getX();
   public int getY();
}


