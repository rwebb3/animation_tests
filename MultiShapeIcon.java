import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Track Multiple ShapeIcons
 */
public class MultiShapeIcon
{
	public MultiShapeIcon()
	{
		this.shapeList = new ArrayList<MoveableShape>();
	}

	public void add(MoveableShape shape)
	{
		shapeList.add(shape);
	}

	public void translateAll()
	{
		Iterator<MoveableShape> it = shapeList.iterator();
		while(it.hasNext())
		{
			MoveableShape theLabel = it.next();
		}
	}

	private ArrayList<MoveableShape> shapeList;
}
