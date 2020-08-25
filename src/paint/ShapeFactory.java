package paint;

import java.awt.Color;

public class ShapeFactory {
	private static Shape shape;
	private static Color color_line, color_fill;
	private static int lineThickness;
	
	public ShapeFactory() {

	}
	
	public static void setColor_line(Color color_line) {
		ShapeFactory.color_line = color_line;
	}
	public static void setColor_fill(Color color_fill) {
		ShapeFactory.color_fill = color_fill;
	}
	public static void setLineThickness(int lineThickness) {
		ShapeFactory.lineThickness = lineThickness;
	}
	
	public static Shape createShape(Type type, int mouse_x, int mouse_y) {
		switch (type) {
		case RECTANGLE:
			shape = new Rectangle(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case TRIANGLE:
			shape = new Triangle(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case ELLIPSE:
			shape = new Ellipse(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case DIAMOND:
			shape = new Diamond(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case LINE:
			shape = new Line(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case PEN:
			shape = new Pen(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		case POLYGON:
			shape = new Polygon(mouse_x, mouse_y, color_line, color_fill, lineThickness);
			break;
		default:
			break;
		}
		shape.setMousePoint_x(mouse_x);
		shape.setMousePoint_y(mouse_y);
		return shape;
	}
}
