package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;

abstract public class Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char state;
	private int xpos_1;
	private int ypos_1;
	private int xpos_2;
	private int ypos_2;
	
	private int rotate_center_x;
	private int rotate_center_y;
	private double degree=0;
	
	private int mousePoint_x;
	private int mousePoint_y;
	
	int[] xPos;
	int[] yPos;

	private Color color_line;
	private Color color_fill;
	private int lineThickness = 1;
	private int selectedPoint;
	
	public Shape() {
	}
	
	public Shape(int x1, int y1, char state, Color color_line, Color color_fill,int lineThickness) {
		this.xpos_1 = x1;
		this.ypos_1 = y1;
		this.xpos_2 = x1 + 10;
		this.ypos_2 = y1 + 10;
		this.color_line = color_line;
		this.color_fill = color_fill;
		this.setState(state);
		this.lineThickness = lineThickness;
	}
	
	public abstract void draw(Graphics g);
	public abstract boolean isInside(int x, int y);

	public void move(int mousePoint_x2, int mousePoint_y2) {
		int modif_x = mousePoint_x2 - mousePoint_x;
		int modif_y = mousePoint_y2 - mousePoint_y;
		
		mousePoint_x = mousePoint_x2;
		mousePoint_y = mousePoint_y2;
		
		xpos_1 += modif_x ;
		xpos_2 += modif_x ;
		ypos_1 += modif_y ;
		ypos_2 += modif_y ;
		
		rotate_center_x += modif_x;
		rotate_center_y += modif_y;
	}
	public void selectedPoint(int x, int y) {
		selectedPoint = 0;
		
			if (y < ypos_1 + 6 && y> ypos_1 - 6)
				selectedPoint= 1;
			else if (y > ypos_2 -6)
				selectedPoint= 2;
			else if (y < ypos_1 - 6)
				selectedPoint = 11;
			if(x < xpos_1 + 6) 
				selectedPoint += 4;
			else if(x > xpos_2 - 6)
				selectedPoint += 8;
	}
	public int rotateMousePointer_x(int x, int y) {
		double sx = x - rotate_center_x;
		double sy = y - rotate_center_y;
		double cos = Math.cos(-degree);
		double sin = Math.sin(-degree);
		double rx = sx*cos-sy*sin+rotate_center_x;
		return (int)rx;
	}
	
	public int rotateMousePointer_y(int x, int y) {
		double sx = x - rotate_center_x;
		double sy = y - rotate_center_y;
		double cos = Math.cos(-degree);
		double sin = Math.sin(-degree);
		double ry = sx*sin+sy*cos+rotate_center_y;
		return (int)ry;		
	}
	
	public int rotatePoint_x(int x, int y) {
		double sx = x - rotate_center_x;
		double sy = y - rotate_center_y;
		double cos = Math.cos(degree);
		double sin = Math.sin(degree);
		double rx = sx*cos-sy*sin+rotate_center_x;
		return (int)rx;
	}
	
	public int rotatePoint_y(int x, int y) {
		double sx = x - rotate_center_x;
		double sy = y - rotate_center_y;
		double cos = Math.cos(degree);
		double sin = Math.sin(degree);
		double ry = sx*sin+sy*cos+rotate_center_y;
		return (int)ry;		
	}
	public int getXpos_1() {
		return xpos_1;
	}
	public void setXpos_1(int xpos_1) {
		this.xpos_1 = xpos_1;
	}
	public int getYpos_1() {
		return ypos_1;
	}
	public void setYpos_1(int ypos_1) {
		this.ypos_1 = ypos_1;
	}
	public int getXpos_2() {
		return xpos_2;
	}
	public void setXpos_2(int xpos_2) {
		this.xpos_2 = xpos_2;
	}
	public int getYpos_2() {
		return ypos_2;
	}
	public void setYpos_2(int ypos_2) {
		this.ypos_2 = ypos_2;
	}
	public int getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
	}
	public int getMousePoint_x() {
		return mousePoint_x;
	}
	public int getMousePoint_y() {

		return mousePoint_y;
	}
	public void setMousePoint_x(int x) {
		mousePoint_x = x;
	}

	public void setMousePoint_y(int y) {
		// TODO Auto-generated method stub
		mousePoint_y = y;
	}
	public void swap() {
		int temp = 0;
		
		temp = Math.max(xpos_1, xpos_2);
		xpos_1 = Math.min(xpos_1, xpos_2);
		xpos_2 = temp;
		
		temp = Math.max(ypos_1, ypos_2);
		ypos_1 = Math.min(ypos_1, ypos_2);
		ypos_2 = temp;
		
	}
	public void resize(int x, int y) {	
		if (mousePoint_y < ypos_1 + 6) {
			ypos_1 = y;
			mousePoint_y = y;
		}
		else if (mousePoint_y > ypos_2 -6) {
			ypos_2 = y;
			mousePoint_y = y;
		}
		if(mousePoint_x < xpos_1 + 6) {
			xpos_1 = x;
			mousePoint_x = x;
		}
		else if(mousePoint_x > xpos_2 - 6){
			xpos_2 = x;
			mousePoint_x = x;
		}
		
		swap();
	}

	public int getSelectedPoint() {
		return selectedPoint;
	}

	public void setSelectedPoint(int selectedPoint) {
		this.selectedPoint = selectedPoint;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}
	public Color getColor_fill() {
		return color_fill;
	}
	public void setColor_fill(Color color_fill) {
		this.color_fill = color_fill;
	}

	public Color getColor_line() {
		return color_line;
	}

	public void setColor_line(Color color_line) {
		this.color_line = color_line;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public void rotate(int x, int y) {
		double theta;
		if(x == (xpos_1 + xpos_2)/2) {
			if(y < (ypos_1 + ypos_2) / 2)
				theta = 1.5708;
			else
				theta = -1.5708;
		}
		else
			theta = Math.atan((double)((double)(ypos_1 + ypos_2)/2 - y)/(x - (double)(xpos_1 + xpos_2)/2));
		
		if(x >= (xpos_1 + xpos_2)/2) {
			degree = 1.5708 - theta;
		}
		else
			degree = 4.71239 - theta;
		
	}

	abstract boolean getComplete();
	abstract void setComplete();
	abstract boolean getPointEdit();
	abstract void setPointEdit(boolean pointEdit);
	abstract void remove_xy();
	abstract ArrayList<Integer> getXpolyPoints();
	abstract ArrayList<Integer> getYpolyPoints();

	public int getRotate_center_x() {
		return rotate_center_x;
	}

	public void setRotate_center_x(int i) {
		this.rotate_center_x = i;
	}
	public void setRotate_center_y(int i) {
		this.rotate_center_y = i;
	}

	public int getRotate_center_y() {
		return rotate_center_y;
	}
	public void setRotate_center_xy() {	
		int dif_x = 0;
		int dif_y = 0;
		int temp = rotatePoint_y((xpos_1+xpos_2)/2, (ypos_1+ypos_2)/2);
		this.rotate_center_x = rotatePoint_x((xpos_1+xpos_2)/2, (ypos_1+ypos_2)/2);
		this.rotate_center_y = temp;
		
		dif_x = (xpos_1 + xpos_2)/2 - rotate_center_x;
		dif_y = (ypos_1 + ypos_2)/2 - rotate_center_y;
		
		xpos_1 -= dif_x;
		xpos_2 -= dif_x;
		ypos_1 -= dif_y;
		ypos_2 -= dif_y;
	}
}

class SlctArea extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int[] slctXpos = new int[9];
	public int[] slctYpos = new int[9];	
	public Shape focus;

	public SlctArea(int xpos_1, int xpos_2, int ypos_1, int ypos_2, char state, Shape focus) {
		slctXpos[0] = xpos_1;
		slctXpos[1] = (xpos_1 + xpos_2) / 2;
		slctXpos[2] = xpos_2;
		slctXpos[3] = xpos_2;
		slctXpos[4] = xpos_2;
		slctXpos[5] = (xpos_1 + xpos_2) / 2;
		slctXpos[6] = xpos_1;
		slctXpos[7] = xpos_1;
				
		slctYpos[0] = ypos_1;
		slctYpos[1] = ypos_1;
		slctYpos[2] = ypos_1;
		slctYpos[3] = (ypos_1 + ypos_2) / 2;
		slctYpos[4] = ypos_2;
		slctYpos[5] = ypos_2;		
		slctYpos[6] = ypos_2;
		slctYpos[7] = (ypos_1 + ypos_2) / 2;
		
		slctYpos[8] = ypos_1-60;
		slctXpos[8] = (xpos_1 + xpos_2)/2;
		
		this.focus = focus;
		setState(state);
	}
	@Override
	public boolean isInside(int x, int y) {
		if (!focus.getPointEdit()) {
			for (int i = 0; i < 9; i++){
				if( x <= slctXpos[i] + 5 && x >= slctXpos[i] - 5 && y <= slctYpos[i] + 5 && y >= slctYpos[i] - 5)
					return true;
			}
		}
		else {
			for(int i = 0; i < focus.getXpolyPoints().size(); i++) {
				if(focus.getXpolyPoints().get(i) - 5 < x && focus.getXpolyPoints().get(i) + 5 > x && focus.getYpolyPoints().get(i) - 5 < y && focus.getYpolyPoints().get(i) + 5 > y) {
					focus.setSelectedPoint(i);
					return true;
				}
			}
				
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		ArrayList<Integer> Xdots;
		ArrayList<Integer> Ydots;
		
		((Graphics2D) g).setStroke(new BasicStroke(1));
		
		((Graphics2D)g).rotate(focus.getDegree(), focus.getRotate_center_x(), focus.getRotate_center_y());
		if(getState()=='l') {
			g.setColor(Color.white);
			g.fillOval(slctXpos[4]-5, slctYpos[4]-5, 11, 11);
			g.fillOval(slctXpos[0]-5, slctYpos[0]-5, 11, 11);
			
			g.setColor(Color.gray);
			g.drawOval(slctXpos[4]-5, slctYpos[4]-5, 11, 11);
			g.drawOval(slctXpos[0]-5, slctYpos[0]-5, 11, 11);
		}
		else{
			if(!focus.getPointEdit()) {
				g.setColor(Color.gray);
				g.drawRect(slctXpos[0], slctYpos[0], slctXpos[4] - slctXpos[0], slctYpos[4] - slctYpos[0]);
				g.drawLine(slctXpos[8], slctYpos[8], slctXpos[1], slctYpos[1]);

				for(int i =0; i < 9; i++) {
					g.setColor(Color.white);
					g.fillOval(slctXpos[i]-5, slctYpos[i]-5, 11, 11);
					g.setColor(Color.gray);
					g.drawOval(slctXpos[i]-5, slctYpos[i]-5, 11, 11);
				}
			}
			else {
				Xdots = focus.getXpolyPoints();
				Ydots = focus.getYpolyPoints();
				
				for(int i =0; i < Xdots.size(); i++) {
					g.setColor(Color.white);
					g.fillOval(Xdots.get(i)-5, Ydots.get(i)-5, 11, 11);
					g.setColor(Color.gray);
					g.drawOval(Xdots.get(i)-5, Ydots.get(i)-5, 11, 11);
				}
			}
		}
		((Graphics2D)g).rotate(-focus.getDegree(), focus.getRotate_center_x(), focus.getRotate_center_y());
	}
	@Override
	boolean getComplete() {
		return false;
	}
	@Override
	void setComplete() {
	}
	@Override
	void remove_xy() {
	}
	@Override
	boolean getPointEdit() {
		return false;
	}
	@Override
	void setPointEdit(boolean pointEdit) {
	}
	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}
	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Rectangle extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rectangle(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	}
	
	public boolean isInside(int x, int y) {
		if((x - getXpos_1())*(x - getXpos_2()) < 0 && (y - getYpos_1())*(y - getYpos_2()) < 0) {
				return true;
		}
		
		return false;
	}
	
	public void draw(Graphics g) {
		((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_fill());
        g.fillRect(getXpos_1(), getYpos_1(), getXpos_2()-getXpos_1(), getYpos_2()-getYpos_1());
        g.setColor(getColor_line());
		g.drawRect(getXpos_1(), getYpos_1(), getXpos_2()-getXpos_1(), getYpos_2()-getYpos_1());
		((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
	}
	@Override
	boolean getComplete() {
		return false;
	}
	@Override
	void setComplete() {
	}
	@Override
	void remove_xy() {
	}
	@Override
	boolean getPointEdit() {
		return false;
	}
	@Override
	void setPointEdit(boolean pointEdit) {		
	}
	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}
	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Ellipse extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ellipse(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	}
	
	public boolean isInside(int x, int y) {
		if(x <= getXpos_2() && x >= getXpos_1() && y<= Math.sqrt(Math.pow((getYpos_2()-getYpos_1())/2, 2)*(1-Math.pow(((double)x-(getXpos_1()+(getXpos_2()-getXpos_1())/2))/((getXpos_2()-getXpos_1())/2),2)))+getYpos_1()+(getYpos_2()-getYpos_1())/2 &&  y>= -Math.sqrt(Math.pow((getYpos_2()-getYpos_1())/2, 2)*(1-Math.pow(((double)x-(getXpos_1()+(getXpos_2()-getXpos_1())/2))/((getXpos_2()-getXpos_1())/2),2)))+getYpos_1()+(getYpos_2()-getYpos_1())/2 ) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_fill());
		g.fillOval(getXpos_1(), getYpos_1(), getXpos_2()-getXpos_1(), getYpos_2()-getYpos_1());
		g.setColor(getColor_line());
		g.drawOval(getXpos_1(), getYpos_1(), getXpos_2()-getXpos_1(), getYpos_2()-getYpos_1());
		((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
	}
	@Override
	boolean getComplete() {
		return false;
	}
	@Override
	void setComplete() {
	}
	@Override
	void remove_xy() {
	}
	@Override
	boolean getPointEdit() {
		return false;
	}
	@Override
	void setPointEdit(boolean pointEdit) {		
	}
	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}
	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Triangle extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Triangle(int x1, int y1, char state, Color color_line, Color color_fill,int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	
		xPos = new int[3];
		yPos = new int[3];
	}
	
	public boolean isInside(int x, int y) {
		if(x < xPos[0] && y < yPos[1]) {
			if (x == xPos[0] ||(double)(y - yPos[0])/(x - xPos[0]) < (double)(yPos[2] - yPos[0])/(xPos[2] - xPos[0]))
				return true;
		}
		
		else if(x > xPos[0] && y < yPos[1]) {
			if (x == xPos[0] || (double)(y - yPos[0])/(x - xPos[0]) > (double)(yPos[1] - yPos[0])/(xPos[1] - xPos[0]))
				return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		xPos[2] = getXpos_1();
		yPos[0] = getYpos_1();
		xPos[0] = (getXpos_1() + getXpos_2()) / 2;
		xPos[1] = getXpos_2();
		yPos[1] = getYpos_2();
		yPos[2] = getYpos_2();

		((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_fill());
		g.fillPolygon(xPos, yPos, 3);
		g.setColor(getColor_line());
		g.drawPolygon(xPos, yPos, 3);
		((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
	}

	@Override
	boolean getComplete() {
		return false;
	}

	@Override
	void setComplete() {
	}

	@Override
	void remove_xy() {
	}

	@Override
	boolean getPointEdit() {
		return false;
	}

	@Override
	void setPointEdit(boolean pointEdit) {
	}

	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}

	@Override
	ArrayList<Integer> getYpolyPoints() {
			return null;
	}

}

class Diamond extends Shape{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Diamond(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	
		xPos = new int[4];
		yPos = new int[4];
	}
	
	public boolean isInside(int x, int y) {
		if(x < xPos[0] && y < yPos[1]) {
			if (x == xPos[0] ||(double)(y - yPos[0])/(x - xPos[0]) < (double)(yPos[3] - yPos[0])/(xPos[3] - xPos[0]))
				return true;
		}
		
		else if(x > xPos[0] && y < yPos[1]) {
			if (x == xPos[0] || (double)(y - yPos[0])/(x - xPos[0]) > (double)(yPos[1] - yPos[0])/(xPos[1] - xPos[0]))
				return true;
		}
		else if(x < xPos[0] && y > yPos[1]) {
			if (x == xPos[0] ||(double)(y - yPos[2])/(x - xPos[2]) > (double)(yPos[3] - yPos[2])/(xPos[3] - xPos[2]))
				return true;
		}
		
		else if(x > xPos[0] && y > yPos[1]) {
			if (x == xPos[0] || (double)(y - yPos[2])/(x - xPos[2]) < (double)(yPos[1] - yPos[2])/(xPos[1] - xPos[2]))
				return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		xPos[3] = getXpos_1();
		yPos[0] = getYpos_1();		
		yPos[1] = (getYpos_1() + getYpos_2())/2;
		yPos[3] = (getYpos_1() + getYpos_2())/2;
		yPos[2] = getYpos_2();		
		xPos[0] = (getXpos_1()+getXpos_2())/2;
		xPos[2] = (getXpos_1()+getXpos_2())/2;
		xPos[1] = getXpos_2();
		
		((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_fill());
		g.fillPolygon(xPos, yPos, 4);
		g.setColor(getColor_line());
		g.drawPolygon(xPos, yPos, 4);
		((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
	}

	@Override
	boolean getComplete() {
		return false;
	}

	@Override
	void setComplete() {
	}

	@Override
	void remove_xy() {
	}

	@Override
	boolean getPointEdit() {
		return false;
	}

	@Override
	void setPointEdit(boolean pointEdit) {
	}

	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}

	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Pen extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> XpenPoints = new ArrayList<Integer>();
	private ArrayList<Integer> YpenPoints = new ArrayList<Integer>();

	private ArrayList<Double> Xprops = new ArrayList<Double>();
	private ArrayList<Double> Yprops = new ArrayList<Double>();
	public int temp = 0;
	
	public Pen(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	
		XpenPoints.add(x1);
		XpenPoints.add(x1);
		YpenPoints.add(y1);
		YpenPoints.add(y1);
	}
	
	public boolean isInside(int x, int y) {
		for(int i=0; i < XpenPoints.size()-1; i++) {
			if(x <= XpenPoints.get(i) + getLineThickness() + 10  && x > XpenPoints.get(i) - (getLineThickness() + 10) && y <= YpenPoints.get(i) + getLineThickness() + 10 && y>YpenPoints.get(i) - (getLineThickness() + 10)) {
				return true;
			}
		}
		return false;
	}
	public void setRotate_center_xy() {	
		int dif_x = 0;
		int dif_y = 0;
		int temp = rotatePoint_y((getXpos_1()+getXpos_2())/2, (getYpos_1()+getYpos_2())/2);
		setRotate_center_x(rotatePoint_x((getXpos_1()+getXpos_2())/2, (getYpos_1()+getYpos_2())/2));
		setRotate_center_y(temp);
		
		dif_x = (getXpos_1() + getXpos_2())/2 - getRotate_center_x();
		dif_y = (getYpos_1() + getYpos_2())/2 - getRotate_center_y();
		
		setXpos_1(getXpos_1() - dif_x);
		super.setXpos_2(getXpos_2() - dif_x);
		setYpos_1(getYpos_1() - dif_y);
		super.setYpos_2(getYpos_2() - dif_y);
		
		for(int i = 0; i < XpenPoints.size(); i++) {
			XpenPoints.set(i, XpenPoints.get(i) - dif_x);
			YpenPoints.set(i, YpenPoints.get(i) - dif_y);			
		}
	}
	public void resize(int x, int y) {
		if ((getSelectedPoint()&1)==1) {
			setYpos_1(y);
			setMousePoint_y(y);
		}
		else if ((getSelectedPoint()&2)==2) {
			super.setYpos_2(y);
			setMousePoint_y(y);
		}
		if((getSelectedPoint()&4)==4) {
			setXpos_1(x);
			setMousePoint_x(x);
		}
		else if((getSelectedPoint()&8)==8){
			super.setXpos_2(x);
			setMousePoint_x(x);
		}

		for (int i = 0; i < XpenPoints.size() ; i++) {
			XpenPoints.set(i, (int) ((getXpos_2()-getXpos_1()) * Xprops.get(i) + getXpos_1()));
			YpenPoints.set(i, (int) ((getYpos_2()-getYpos_1()) * Yprops.get(i) + getYpos_1()));
		}
	}
	public void draw(Graphics g) {
		((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_line());
		for(int i=0; i < XpenPoints.size()-1; i++) {
			g.drawLine(XpenPoints.get(i), YpenPoints.get(i), XpenPoints.get(i + 1), YpenPoints.get(i + 1));
		}
		((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
	}
	
	public void move(int mousePoint_x2, int mousePoint_y2) {
		int modif_x = mousePoint_x2 - getMousePoint_x();
		int modif_y = mousePoint_y2 - getMousePoint_y();
		
		setMousePoint_x(mousePoint_x2);
		setMousePoint_y(mousePoint_y2);
		
		setXpos_1(getXpos_1() + modif_x);
		setYpos_1(getYpos_1() + modif_y);
		super.setXpos_2(getXpos_2() + modif_x);
		super.setYpos_2(getYpos_2() + modif_y);
		
		setRotate_center_x(getRotate_center_x() + modif_x);
		setRotate_center_y(getRotate_center_y() + modif_y);
		
		for(int i = 0; i < XpenPoints.size(); i++) {
			XpenPoints.set(i, XpenPoints.get(i) + modif_x);
			YpenPoints.set(i, YpenPoints.get(i) + modif_y);
		}
	}
	
	public void swap() {
		int temp = 0;
		
		temp = Math.max(getXpos_1(), getXpos_2());
		if (temp != getXpos_2()){
			setXpos_1(Math.min(getXpos_1(), getXpos_2()));
			super.setXpos_2(temp);
			
			for(int i=0; i < XpenPoints.size(); i++) {
				Xprops.set(i,1 - Xprops.get(i));
			}
		}
		
		temp = Math.max(getYpos_1(), getYpos_2());
		
		if (temp != getYpos_2()){	
			setYpos_1(Math.min(getYpos_1(), getYpos_2()));
			super.setYpos_2(temp);
			
			for(int i=0; i < XpenPoints.size(); i++) {
				Yprops.set(i, 1 - Yprops.get(i));
			}
		}
	}
	
	public void setXpos_2(int xpos_2) {
		if(xpos_2 < getXpos_1())
			super.setXpos_1(xpos_2);
		if(xpos_2 > getXpos_2())
			super.setXpos_2(xpos_2);

		XpenPoints.add(xpos_2);
		Xprops.clear();
		for(int i=0; i < XpenPoints.size(); i++) {
			Xprops.add((double)(XpenPoints.get(i)-getXpos_1())/(double)(getXpos_2()-getXpos_1()));
		}
	}

	public void setYpos_2(int ypos_2) {
		if(ypos_2 < getYpos_1())
			super.setYpos_1(ypos_2);
		if(ypos_2 > getYpos_2())
			super.setYpos_2(ypos_2);
		
		YpenPoints.add(ypos_2);
		Yprops.clear();		
		for(int i=0; i < XpenPoints.size(); i++) {
			Yprops.add((double)(YpenPoints.get(i)-getYpos_1())/(double)(getYpos_2()-getYpos_1()));
		}
	}

	@Override
	boolean getComplete() {
		return false;
	}

	@Override
	void setComplete() {
	}

	@Override
	void remove_xy() {
	}

	@Override
	boolean getPointEdit() {
		return false;
	}

	@Override
	void setPointEdit(boolean pointEdit) {	
	}

	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}

	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Line extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Line(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
	}
	
	public void resize(int x, int y){
		if (getSelectedPoint()==5) {
			setYpos_1(y);
			setXpos_1(x);
		}
		
		else if(getSelectedPoint()==10){
			setYpos_2(y);
			setXpos_2(x);
		}
	}
	public void selectedPoint(int x, int y) {
		if(x < getXpos_1()+6)
			setSelectedPoint(5);
		else
			setSelectedPoint(10);
	}
	public void swap() {
		int temp = 0;
		if(getXpos_1()>getXpos_2()) {
			temp = getXpos_1();
			setXpos_1(getXpos_2());
			setXpos_2(temp);
			
			temp = getYpos_1();
			setYpos_1(getYpos_2());
			setYpos_2(temp);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
		g.setColor(getColor_line());
		g.drawLine(getXpos_1(), getYpos_1(), getXpos_2(), getYpos_2());
	}

	@Override
	public boolean isInside(int x, int y) {
		double a = (double)(getYpos_2() - getYpos_1())/(getXpos_2() - getXpos_1());
		swap();
		if(x < getXpos_2()&& x>getXpos_1() && 15 > Math.abs(a*(x - getXpos_1())-(y - getYpos_1()))/(Math.sqrt(Math.pow(a, 2)+1)))
			return true;
		return false;
	}

	@Override
	boolean getComplete() {
		return false;
	}

	@Override
	void setComplete() {
	}

	@Override
	void remove_xy() {		
	}

	@Override
	boolean getPointEdit() {
		return false;
	}

	@Override
	void setPointEdit(boolean pointEdit) {
	}

	@Override
	ArrayList<Integer> getXpolyPoints() {
		return null;
	}

	@Override
	ArrayList<Integer> getYpolyPoints() {
		return null;
	}
}

class Polygon extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean complete = false;
	public boolean pointEdit = false;
	private ArrayList<Integer> XpolyPoints = new ArrayList<Integer>();
	private ArrayList<Integer> YpolyPoints = new ArrayList<Integer>();

	private ArrayList<Double> Xprops = new ArrayList<Double>();
	private ArrayList<Double> Yprops = new ArrayList<Double>();
	
	public Polygon(int x1, int y1, char state, Color color_line, Color color_fill, int lineThickness) {
		super(x1, y1, state, color_line, color_fill, lineThickness);
		
		XpolyPoints.add(x1);
		YpolyPoints.add(y1);
		XpolyPoints.add(x1);
		YpolyPoints.add(y1);
	}
	public ArrayList<Integer> getXpolyPoints(){
		return XpolyPoints;
	}
	public ArrayList<Integer> getYpolyPoints(){
		return YpolyPoints;
	}
	public void setRotate_center_xy() {	
		int dif_x = 0;
		int dif_y = 0;
		int temp = rotatePoint_y((getXpos_1()+getXpos_2())/2, (getYpos_1()+getYpos_2())/2);
		setRotate_center_x(rotatePoint_x((getXpos_1()+getXpos_2())/2, (getYpos_1()+getYpos_2())/2));
		setRotate_center_y(temp);
		
		dif_x = (getXpos_1() + getXpos_2())/2 - getRotate_center_x();
		dif_y = (getYpos_1() + getYpos_2())/2 - getRotate_center_y();
		
		setXpos_1(getXpos_1() - dif_x);
		super.setXpos_2(getXpos_2() - dif_x);
		setYpos_1(getYpos_1() - dif_y);
		super.setYpos_2(getYpos_2() - dif_y);
		
		for(int i = 0; i < XpolyPoints.size(); i++) {
			XpolyPoints.set(i, XpolyPoints.get(i) - dif_x);
			YpolyPoints.set(i, YpolyPoints.get(i) - dif_y);			
		}
	}
	public void resize(int x, int y) {
		if(!pointEdit) {
			if ((getSelectedPoint()&1)==1) {
				setYpos_1(y);
				setMousePoint_y(y);
			}
			else if ((getSelectedPoint()&2)==2) {
				super.setYpos_2(y);
				setMousePoint_y(y);
			}
			if((getSelectedPoint()&4)==4) {
				setXpos_1(x);
				setMousePoint_x(x);
			}
			else if((getSelectedPoint()&8)==8){
				super.setXpos_2(x);
				setMousePoint_x(x);
			}
	
			for (int i = 0; i < XpolyPoints.size() ; i++) {
				XpolyPoints.set(i, (int) ((getXpos_2()-getXpos_1()) * Xprops.get(i) + getXpos_1()));
				YpolyPoints.set(i, (int) ((getYpos_2()-getYpos_1()) * Yprops.get(i) + getYpos_1()));
			}
		}
		else {
			setXpos_1(XpolyPoints.get(0));
			super.setXpos_2(XpolyPoints.get(0));
			setYpos_1(YpolyPoints.get(0));
			super.setYpos_2(YpolyPoints.get(0));
			
			for(int i = 1; i<XpolyPoints.size(); i++) {
				if(getXpos_1() > XpolyPoints.get(i))
					setXpos_1(XpolyPoints.get(i));
				if(getXpos_2() < XpolyPoints.get(i))
					super.setXpos_2(XpolyPoints.get(i));
				if(getYpos_1() > YpolyPoints.get(i))
					setYpos_1(YpolyPoints.get(i));;
				if(getYpos_2() < YpolyPoints.get(i))
					super.setYpos_2(YpolyPoints.get(i));
			}
			
			if(getSelectedPoint() > -1) {
				XpolyPoints.set(getSelectedPoint(), x);
				YpolyPoints.set(getSelectedPoint(), y);
			}
			propsModify();
			
		}
	}
	
	public void move(int mousePoint_x2, int mousePoint_y2) {
		int modif_x = mousePoint_x2 - getMousePoint_x();
		int modif_y = mousePoint_y2 - getMousePoint_y();
		
		setMousePoint_x(mousePoint_x2);
		setMousePoint_y(mousePoint_y2);
		
		setXpos_1(getXpos_1() + modif_x);
		setYpos_1(getYpos_1() + modif_y);
		super.setXpos_2(getXpos_2() + modif_x);
		super.setYpos_2(getYpos_2() + modif_y);
		
		setRotate_center_x(getRotate_center_x() + modif_x);
		setRotate_center_y(getRotate_center_y() + modif_y);
		
		for(int i = 0; i < XpolyPoints.size(); i++) {
			XpolyPoints.set(i, XpolyPoints.get(i) + modif_x);
			YpolyPoints.set(i, YpolyPoints.get(i) + modif_y);
		}
	}
	
	public void swap() {
		int temp = 0;
		
		temp = Math.max(getXpos_1(), getXpos_2());
		if (temp != getXpos_2()){
			setXpos_1(Math.min(getXpos_1(), getXpos_2()));
			super.setXpos_2(temp);
			
			for(int i=0; i < XpolyPoints.size(); i++) {
				Xprops.set(i,1 - Xprops.get(i));
			}
		}
		
		temp = Math.max(getYpos_1(), getYpos_2());
		
		if (temp != getYpos_2()){	
			setYpos_1(Math.min(getYpos_1(), getYpos_2()));
			super.setYpos_2(temp);
			
			for(int i=0; i < XpolyPoints.size(); i++) {
				Yprops.set(i, 1 - Yprops.get(i));
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(!complete) {
			((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
			((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
			g.setColor(getColor_line());
			for(int i=0; i < XpolyPoints.size()-1; i++) {
				g.drawLine(XpolyPoints.get(i), YpolyPoints.get(i), XpolyPoints.get(i + 1), YpolyPoints.get(i + 1));
			}
			((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
		}
		else {
			((Graphics2D)g).rotate(getDegree(),getRotate_center_x(), getRotate_center_y());
			((Graphics2D) g).setStroke(new BasicStroke(getLineThickness()));
			
			xPos = new int[XpolyPoints.size()];
			yPos = new int[YpolyPoints.size()];
			
			for(int i = 0; i < XpolyPoints.size(); i++) {
				xPos[i] = XpolyPoints.get(i);
				yPos[i] = YpolyPoints.get(i);
			}
			g.setColor(getColor_fill());	
			g.fillPolygon(xPos, yPos, XpolyPoints.size());
			g.setColor(getColor_line());
			g.drawPolygon(xPos, yPos, XpolyPoints.size());
			((Graphics2D)g).rotate(-getDegree(),getRotate_center_x(), getRotate_center_y());
		}
	}

	@Override
	public boolean isInside(int x, int y) {
		double ua = 0;
		double ub = 0;
		double den = 0;
		int count = 0;
		int j = 0;
		
		for(int i = 0; i < XpolyPoints.size(); i++) {
			if ((i + 1) == XpolyPoints.size())
				j = 0;
			else
				j = i+1;
			
			den = (YpolyPoints.get(j) - YpolyPoints.get(i)) * (5000 - x);

			if(den != 0) {
				ua = (double)((XpolyPoints.get(j) - XpolyPoints.get(i)) * (y - YpolyPoints.get(i)) - (YpolyPoints.get(j) - YpolyPoints.get(i)) * (x - XpolyPoints.get(i))) / den;
				ub = (double)((5000 - x) * (y - YpolyPoints.get(i))) / den;
				
				if (ua<1 && ua>0 && ub<1 && ub>0)
					count ++;
			}
		}
			
		if(count%2 == 1)
			return true;
		else
			return false;
	}
	
	public void selectedPoint(int x, int y) {
		setSelectedPoint(0);
		if(!pointEdit) {
			if (y < getYpos_1() + 6 && y> getYpos_1() - 6)
				setSelectedPoint(1);
			else if (y > getYpos_2() -6)
				setSelectedPoint(2);
			else if (y < getYpos_1() - 6)
				setSelectedPoint(11);
			if(x < getXpos_1() + 6) 
				setSelectedPoint(getSelectedPoint()+4);
			else if(x > getXpos_2() - 6)
				setSelectedPoint(getSelectedPoint()+8);
		}
	}

	@Override
	public boolean getComplete() {
		return complete;
	}

	@Override
	public void setComplete() {
		complete = true;
	}
	
	public void setXpos_2(int xpos_2) {
		if(xpos_2 < getXpos_1())
			super.setXpos_1(xpos_2);
		if(xpos_2 > getXpos_2())
			super.setXpos_2(xpos_2);	
		XpolyPoints.add(xpos_2);
	}
	
	public void setYpos_2(int ypos_2) {
		if(ypos_2 < getYpos_1())
			super.setYpos_1(ypos_2);
		if(ypos_2 > getYpos_2())
			super.setYpos_2(ypos_2);
		
		YpolyPoints.add(ypos_2);
		propsModify();
	}
	
	public void propsModify() {
		Xprops.clear();
		for(int i=0; i < XpolyPoints.size(); i++) {
			Xprops.add((double)(XpolyPoints.get(i)-getXpos_1())/(double)(getXpos_2()-getXpos_1()));
		}
		Yprops.clear();		
		for(int i=0; i < YpolyPoints.size(); i++) {
			Yprops.add((double)(YpolyPoints.get(i)-getYpos_1())/(double)(getYpos_2()-getYpos_1()));
		}
	}

	@Override
	void remove_xy() {
		XpolyPoints.remove(XpolyPoints.size()-1);
		YpolyPoints.remove(YpolyPoints.size()-1);
	}

	@Override
	boolean getPointEdit() {
		return pointEdit;
	}

	@Override
	void setPointEdit(boolean pointEdit) {
		this.pointEdit = pointEdit;
	}
}
