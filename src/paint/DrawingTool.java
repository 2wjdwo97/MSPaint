package paint;

import java.util.ArrayList;

public interface DrawingTool {
	public abstract DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y);
	public abstract void dragged();
	public abstract void released();
}

abstract class DrawShape implements DrawingTool{	
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		create(theModel, mouse_x, mouse_y);
		return this;
	}
	public abstract void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y);
}

class DrawRectangle extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.RECTANGLE, mouse_x, mouse_y));
	}
}

class DrawTriangle extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.TRIANGLE, mouse_x, mouse_y));
	}
}

class DrawEllipse extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}
	
	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.ELLIPSE, mouse_x, mouse_y));
	}
}

class DrawDiamond extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.DIAMOND, mouse_x, mouse_y));
	}
}

class DrawLine extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.LINE, mouse_x, mouse_y));
	}
}

class DrawPen extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.PEN, mouse_x, mouse_y));
	}
}

class DrawPolygon extends DrawShape{
	public void dragged() {
	}

	public void released() {
	}

	public void create(ArrayList<Shape> theModel, int mouse_x, int mouse_y) {
		theModel.add(ShapeFactory.createShape(Type.POLYGON, mouse_x, mouse_y));
	}
}

//////////////////////////////////////////

class SelectShape implements DrawingTool{
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		return null;
	}
	public void dragged() {
	}
	public void released() {
	}
}

class DeleteShape implements DrawingTool{
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		return null;
	}
	public void dragged() {
	}
	public void released() {
	}
}

class RotateShape implements DrawingTool{
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		return null;
	}
	public void dragged() {
	}
	public void released() {
	}
}

class MoveShape implements DrawingTool{
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		return null;
	}
	public void dragged() {
	}
	public void released() {
	}
}

class ResizeShape implements DrawingTool{
	public DrawingTool pressed(ArrayList<Shape> theModel, int focus, int mouse_x, int mouse_y) {
		return null;
	}
	public void dragged() {
	}
	public void released() {
	}
}
