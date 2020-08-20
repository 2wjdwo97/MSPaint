package paint;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class paintController {
	private ArrayList<Shape> theModel;
	private paintView theView;
	
	public static final byte ELLIPSE = 0;
    public static final byte RECTANGLE = 1;
    public static final byte TRIANGLE = 2;
    public static final byte DIAMOND = 3;
    public static final byte LINE = 4;
    public static final byte PEN = 5;
    public static final byte POLYGON = 6;
    
    public static final byte SELECT = -1;
    public static final byte DELETE = -2;
    public static final byte ROTATE = -3;
    public static final byte MOVE = -4;
    public static final byte RESIZE = -5;
    public static final byte SAVE = -6;
    public static final byte LOAD = -7;
    
    private int focus = -1;
    private byte drawMode = PEN;
    private int lineThickness = 1;
    private Color color_line = Color.black;
    private Color color_fill = Color.white;
    
	public paintController(paintView theView, ArrayList<Shape> theModel) {
		this.theModel = theModel;
		this.theView = theView;

		this.theView.addSaveListener(new saveListener());
		this.theView.addLoadListener(new loadListener());
		this.theView.addRectListener(new rectListener());
		this.theView.addEllipseListener(new ellipseListener());
		this.theView.addTriListener(new triListener());
		this.theView.addLineListener(new lineListener());
		this.theView.addPolygonListener(new polygonListener());
		this.theView.addDiamondListener(new diamondListener());
		this.theView.addPenListener(new penListener());
		this.theView.addClickListener(new clickListener());
		this.theView.addMouseWheelListener(new mouseWheelListener());
		this.theView.addDragListener(new dragListener());
		this.theView.addDeleteListener(new deleteListener());
		this.theView.addDeleteAllListener(new deleteAllListener());
		this.theView.addColorLineListener(new colorLineListener());
		this.theView.addColorFillListener(new colorFillListener());
		this.theView.addEditPointListener(new editPointListener());
		this.theView.addCreateMenuListener(new createMenuListener());
		this.theView.addDeleteMenuListener(new deleteMenuListener());
		this.theView.addUpListener(new upListener());
		this.theView.addDownListener(new downListener());
	}
	
	class upListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			Shape temp;
			if(focus != -1 && focus != theModel.size() - 2) {
				temp = theModel.get(focus + 1);
				theModel.set(focus + 1, theModel.get(focus));
				theModel.set(focus, temp);
				focus += 1;
				theModel.remove(theModel.size() - 1);
				theModel.add(new SlctArea(theModel.get(focus).getXpos_1(),theModel.get(focus).getXpos_2(), theModel.get(focus).getYpos_1(), theModel.get(focus).getYpos_2(),theModel.get(focus).getState(), theModel.get(focus)));
				theView.repaint();
			}
		}
	}
	
	class downListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			Shape temp;
			if(focus != -1 && focus != 0) {
				temp = theModel.get(focus - 1);
				theModel.set(focus - 1, theModel.get(focus));
				theModel.set(focus, temp);
				focus -= 1;
				theModel.remove(theModel.size() - 1);
				theModel.add(new SlctArea(theModel.get(focus).getXpos_1(),theModel.get(focus).getXpos_2(), theModel.get(focus).getYpos_1(), theModel.get(focus).getYpos_2(),theModel.get(focus).getState(), theModel.get(focus)));
				theView.repaint();
			}
		}
	}
	
	class createMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(theModel.get(focus).getSelectedPoint() == theModel.get(focus).getXpolyPoints().size()-1){
				theModel.get(focus).getXpolyPoints().add(theModel.get(focus).getSelectedPoint()+1, (theModel.get(focus).getXpolyPoints().get(theModel.get(focus).getSelectedPoint())+theModel.get(focus).getXpolyPoints().get(0))/2);
				theModel.get(focus).getYpolyPoints().add(theModel.get(focus).getSelectedPoint()+1, (theModel.get(focus).getYpolyPoints().get(theModel.get(focus).getSelectedPoint())+theModel.get(focus).getYpolyPoints().get(0))/2);
				
			}
			else {
				theModel.get(focus).getXpolyPoints().add(theModel.get(focus).getSelectedPoint()+1, (theModel.get(focus).getXpolyPoints().get(theModel.get(focus).getSelectedPoint())+theModel.get(focus).getXpolyPoints().get(theModel.get(focus).getSelectedPoint()+1))/2);
				theModel.get(focus).getYpolyPoints().add(theModel.get(focus).getSelectedPoint()+1, (theModel.get(focus).getYpolyPoints().get(theModel.get(focus).getSelectedPoint())+theModel.get(focus).getYpolyPoints().get(theModel.get(focus).getSelectedPoint()+1))/2);	
			}
						theModel.get(focus).setSelectedPoint(-1);
			theModel.get(focus).resize(0, 0);
			theView.repaint();
		}
	}
	class deleteMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = theModel.get(focus).getSelectedPoint(); i < theModel.get(focus).getXpolyPoints().size() - 1; i++) {
				theModel.get(focus).getXpolyPoints().set(i, theModel.get(focus).getXpolyPoints().get(i + 1));
				theModel.get(focus).getYpolyPoints().set(i, theModel.get(focus).getYpolyPoints().get(i + 1));
			}	
			theModel.get(focus).getXpolyPoints().remove(theModel.get(focus).getXpolyPoints().size() - 1);
			theModel.get(focus).getYpolyPoints().remove(theModel.get(focus).getYpolyPoints().size() - 1);
			
			theModel.get(focus).setSelectedPoint(-1);
			theModel.get(focus).resize(0, 0);
			theView.repaint();
		}
	}
	class deleteAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			theView.setEditPointButton(false);
			focus = -1;
			theModel.clear();
			theView.repaint();
		}
	}
	
	class deleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (focus != -1){
				theView.setEditPointButton(false);
				theModel.remove(theModel.size()-1);
				
				for(int i = 0; focus + i < theModel.size()-1; i++)
					theModel.set(focus + i, theModel.get(focus + i + 1));
				theModel.remove(theModel.size()-1);
				focus = -1;
				drawMode = SELECT;
				theView.repaint();
			}
		}	
	}
	
	
	class colorFillListener implements ActionListener {
		JColorChooser chooser = new JColorChooser();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
	 		Color selectedColor = JColorChooser.showDialog(null,"Color",Color.white);
	 		
	 		if(selectedColor != null) {
	 			if(focus != -1)
	 				theModel.get(focus).setColor_fill(selectedColor);
	 				theView.repaint();
	 		} 
	 		theView.setColorFillButton(selectedColor);
	 		color_fill = selectedColor;
		}
	}
	
	class colorLineListener implements ActionListener {
		JColorChooser chooser = new JColorChooser();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
	 		Color selectedColor = JColorChooser.showDialog(null,"Color",Color.black);
	 		
	 		if(selectedColor != null) {
	 			if(focus != -1)
	 				theModel.get(focus).setColor_line(selectedColor);
	 				theView.repaint();
	 		}
	 		theView.setColorLineButton(selectedColor);
	 		color_line = selectedColor;
		}
	}
	
	class saveListener implements ActionListener {
		@Override	
		public void actionPerformed(ActionEvent e) {
			int size = 0;
	        JFileChooser chooser;
	        int ret;
	        String filePath;
	        
	        size = theModel.size();
	        chooser = new JFileChooser();
			ret=chooser.showSaveDialog(null);
			try {
				if(ret!=JFileChooser.APPROVE_OPTION){
	                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
	                return;
	            }
				
				filePath = chooser.getSelectedFile().toString();

				FileOutputStream fos = new FileOutputStream(filePath);
				ObjectOutputStream os = new ObjectOutputStream(fos);
				if(focus != -1)
					size -= 1;
				os.writeInt(size);
				for(int i =0; i < size; i++) {
					os.writeObject(theModel.get(i));
				}
				os.close();
				fos.close();
				
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}

	class loadListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			int size = 0;
	        JFileChooser chooser;
	        int ret;
	        String filePath;
	        
			try { 
				chooser = new JFileChooser();
				ret=chooser.showOpenDialog(null);

				if(ret!=JFileChooser.APPROVE_OPTION){
	                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            
	            filePath=chooser.getSelectedFile().getPath();

				FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream is = new ObjectInputStream(fis);
				theModel.clear();
				size = is.readInt();
				for(int i = 0; i < size; i++) {
					theModel.add((Shape)is.readObject());
				}
				is.close();
				fis.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}

			focus = -1;
			theView.repaint();
		}
	}

	class rectListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = RECTANGLE;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	
	class ellipseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = ELLIPSE;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	
	class triListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = TRIANGLE;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	
	class diamondListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = DIAMOND;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	
	class lineListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = LINE;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	
	class polygonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = POLYGON;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	class penListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawMode = PEN;
			if (focus!=-1) {
				theView.setEditPointButton(false);
				focus = -1;
				theModel.remove(theModel.size()-1);
				theView.repaint();
			}
		}
	}
	class editPointListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Shape focusModel = theModel.get(focus);
			theModel.remove(theModel.size()-1);
			focusModel.setPointEdit(true);
			theModel.add(new SlctArea(focusModel.getXpos_1(),focusModel.getXpos_2(), focusModel.getYpos_1(), focusModel.getYpos_2(),focusModel.getState(), focusModel));
			theView.repaint();
		}
	}
	class dragListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
	        }
			else {
				Shape selectedValue;
				int rotate_x;
				int rotate_y;
				
				if(focus != -1) {  //선택된 도형이 있을 때
					selectedValue = theModel.get(focus);
					
					rotate_x = selectedValue.rotateMousePointer_x(e.getX(), e.getY());
					rotate_y = selectedValue.rotateMousePointer_y(e.getX(), e.getY());				
					
					if(drawMode >= 0) { //그리기면
						if (drawMode == RECTANGLE || drawMode == ELLIPSE || drawMode == TRIANGLE || drawMode == DIAMOND){
							if(e.getX() < selectedValue.getMousePoint_x())
								selectedValue.setXpos_1(e.getX());
							else if(e.getX() > selectedValue.getMousePoint_x())
								selectedValue.setXpos_2(e.getX());
							if(e.getY() < selectedValue.getMousePoint_y())
								selectedValue.setYpos_1(e.getY());
							else if(e.getY() > selectedValue.getMousePoint_y())
								selectedValue.setYpos_2(e.getY());
						}
						else {
							selectedValue.setXpos_2(e.getX());
							selectedValue.setYpos_2(e.getY());
							
						}
					}
					else if (drawMode == RESIZE)
						selectedValue.resize(rotate_x, rotate_y);
					else if (drawMode == ROTATE)
						selectedValue.rotate(e.getX(), e.getY());
					else if (drawMode == MOVE) //그리기가 아니면, 리사이즈가 아니면, 회전이 아니면
						selectedValue.move(e.getX(), e.getY());
					theView.repaint();				
				}
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (drawMode == POLYGON && focus != -1) {
				if (theModel.get(focus).getState() == 'g' && !theModel.get(focus).getComplete()){
					theModel.get(focus).remove_xy();
					theModel.get(focus).setXpos_2(e.getX());
					theModel.get(focus).setYpos_2(e.getY());
					theView.repaint();
				}
			}

		}
	}
	class mouseWheelListener implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int n = 0;
			n = e.getWheelRotation();
		
			if (n>0 &&  lineThickness<20)
				lineThickness += 1;
			else if(n<0 && lineThickness>1)
				lineThickness -= 1;
			if(focus != -1)
				theModel.get(focus).setLineThickness(lineThickness);
			
			theView.setThicknessLabel(lineThickness);
			theView.repaint();
		}
		
	}
	class clickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && drawMode == POLYGON) {
				theModel.get(focus).setComplete();
				if(focus!=-1) {
					Shape focusModel = theModel.get(focus);
					theModel.add(new SlctArea(focusModel.getXpos_1(),focusModel.getXpos_2(), focusModel.getYpos_1(), focusModel.getYpos_2(),focusModel.getState(), focusModel));
				}			
				drawMode = SELECT;
				
				theView.setEditPointButton(true);
				theView.repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Shape lastValue;
			Shape selectedValue;
			int rotate_x;
			int rotate_y;

			if (e.getButton() == MouseEvent.BUTTON3 && focus != -1) {
				selectedValue = theModel.get(focus);
				
				rotate_x = selectedValue.rotateMousePointer_x(e.getX(), e.getY());
				rotate_y = selectedValue.rotateMousePointer_y(e.getX(), e.getY());	
				if(theModel.get(focus).getPointEdit() && theModel.get(theModel.size()-1).isInside(rotate_x, rotate_y))
					theView.getPopup().show(e.getComponent(), e.getX(), e.getY());
	        }
			else if (e.getButton() != MouseEvent.BUTTON3) {	
				if(focus != -1) {
					lastValue = theModel.get(theModel.size()-1);
					selectedValue = theModel.get(focus);
					
					rotate_x = selectedValue.rotateMousePointer_x(e.getX(), e.getY());
					rotate_y = selectedValue.rotateMousePointer_y(e.getX(), e.getY());				
					if(!(selectedValue.getState() == 'g' && !selectedValue.getComplete())) {
						if(lastValue.isInside(rotate_x, rotate_y)) {
							if(!selectedValue.getPointEdit()) {
								selectedValue.setMousePoint_x(rotate_x);
								selectedValue.setMousePoint_y(rotate_y);
								
								selectedValue.selectedPoint(rotate_x, rotate_y);
								if(selectedValue.getSelectedPoint()<11) 
									drawMode = RESIZE;
								else {
									selectedValue.setRotate_center_xy();
									drawMode = ROTATE;
								}
							}	
							else
								drawMode = RESIZE;
						}
					}
					if(drawMode!=POLYGON || selectedValue.getPointEdit())
						theModel.remove(theModel.size()-1);
				}
				
				switch (drawMode) {
					case RECTANGLE:
						theModel.add(new Rectangle(e.getX(), e.getY(), 'r',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						theModel.get(focus).setMousePoint_x(e.getX());
						theModel.get(focus).setMousePoint_y(e.getY());
						break;
					case ELLIPSE:
						theModel.add(new Ellipse(e.getX(), e.getY(), 'e',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						theModel.get(focus).setMousePoint_x(e.getX());
						theModel.get(focus).setMousePoint_y(e.getY());
						break;
					case TRIANGLE:
						theModel.add(new Triangle(e.getX(), e.getY(), 't',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						theModel.get(focus).setMousePoint_x(e.getX());
						theModel.get(focus).setMousePoint_y(e.getY());
						break;
					case DIAMOND:
						theModel.add(new Diamond(e.getX(), e.getY(), 'd',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						theModel.get(focus).setMousePoint_x(e.getX());
						theModel.get(focus).setMousePoint_y(e.getY());
						break;
					case PEN:
						theModel.add(new Pen(e.getX(), e.getY(), 'p',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						break;
					case LINE:
						theModel.add(new Line(e.getX(), e.getY(), 'l',color_line, color_fill, lineThickness));
						focus = theModel.size()-1;
						break;
					case POLYGON:
						if(focus==-1) {
							theModel.add(new Polygon(e.getX(), e.getY(), 'g',color_line, color_fill, lineThickness));
							focus = theModel.size()-1;
						}
						else {
							theModel.get(focus).setXpos_2(e.getX());
							theModel.get(focus).setYpos_2(e.getY());
						}
						break;
					case SELECT:
						int i = 0;
						do{
							i++;
							selectedValue = theModel.get(theModel.size()-i);
							rotate_x = selectedValue.rotateMousePointer_x(e.getX(), e.getY());
							rotate_y = selectedValue.rotateMousePointer_y(e.getX(), e.getY());
						}while(!selectedValue.isInside(rotate_x, rotate_y)&& theModel.size()-i>0);
						
						if(selectedValue.isInside(rotate_x, rotate_y)) { //클릭이 됐을 때 포커스 이동
							if(theModel.size()-i!=focus)
								selectedValue.setPointEdit(false);		
							focus = theModel.size()-i;
							selectedValue.setMousePoint_x(e.getX());
							selectedValue.setMousePoint_y(e.getY());
							
							drawMode = MOVE;
						}
						else {				//아무것도 클릭이 안 됐을 때
							selectedValue.setPointEdit(false);
							focus = -1; 
						}
						break;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
			}
			else {
				if(focus == -1) {
					theView.setEditPointButton(false);
				}
				else {
					if (theModel.get(focus).getState()=='g') {
						theView.setEditPointButton(true);
					}
					else {
						theView.setEditPointButton(false);
					}
				}
				if (drawMode != POLYGON){
					if(focus!=-1) {
						Shape focusModel = theModel.get(focus);
						if(drawMode == LINE) {
							focusModel.swap();
						}
						if(drawMode == RESIZE) {
							focusModel.swap();
						}
						theModel.add(new SlctArea(focusModel.getXpos_1(),focusModel.getXpos_2(), focusModel.getYpos_1(), focusModel.getYpos_2(),focusModel.getState(), focusModel));
					}			
					drawMode = SELECT;
					theView.repaint();
				}			
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {		
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}