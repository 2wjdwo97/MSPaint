package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class paintView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel toolbar = new JPanel();
	JPanel shapeBox = new JPanel();
	viewPanel drawingPane;
	
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem item_create = new JMenuItem("Create");
	private JMenuItem item_delete = new JMenuItem("Delete");
	
	private JButton colorLineButton = new JButton();
	private JButton colorFillButton = new JButton();
	private JButton saveButton = new JButton("save");
	private JButton loadButton = new JButton("load");
	private JButton upButton = new JButton("up");
	private JButton downButton = new JButton("down");
	private JButton editPointButton = new JButton("edit");
	
	private JButton ellipseButton = new JButton("ellipse");
	private JButton rectButton = new JButton("rectangle");
	private JButton triButton = new JButton("tirangle");
	private JButton diamondButton = new JButton("diamond");
	private JButton penButton = new JButton("pen");
	private JButton lineButton = new JButton("line");
	private JButton polygonButton = new JButton("polygon");
	private JButton deleteButton = new JButton("delete");
	private JButton deleteAllButton = new JButton("new_page");
	private JLabel thicknessLabel = new JLabel("1");
	
	
	
	paintView(ArrayList<Shape> theModel) {
		drawingPane = new viewPanel(theModel);
		
		setTitle("paint program");
		setLayout(new BorderLayout());
		getContentPane().add(drawingPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,700);
		
		toolbar.add(colorLineButton);
		toolbar.add(colorFillButton);
		toolbar.add(deleteButton);
		toolbar.add(deleteAllButton);
		toolbar.add(saveButton);
		toolbar.add(loadButton);
		toolbar.add(upButton);
		toolbar.add(downButton);
		toolbar.add(editPointButton);
		toolbar.add(thicknessLabel);
		
		shapeBox.add(ellipseButton);
		shapeBox.add(rectButton);
		shapeBox.add(triButton);
		shapeBox.add(diamondButton);
		shapeBox.add(penButton);
		shapeBox.add(lineButton);
		shapeBox.add(polygonButton);
		
		popup.add(item_create);
		popup.add(item_delete);

		drawingPane.setBackground(Color.white);
		add(toolbar, BorderLayout.SOUTH);
		add(shapeBox, BorderLayout.NORTH);
		setFocusable(true);
		editPointButton.setVisible(false);
		setVisible(true);
		
	}
	public void addUpListener(ActionListener e) {
		upButton.addActionListener(e);
	}
	public void addDownListener(ActionListener e) {
		downButton.addActionListener(e);
	}
	public void addCreateMenuListener(ActionListener e) {
		item_create.addActionListener(e);
	}
	public void addDeleteMenuListener(ActionListener e) {
		item_delete.addActionListener(e);
	}
	public void addEditPointListener(ActionListener e) {
		editPointButton.addActionListener(e);
	}
	
	public void addColorLineListener(ActionListener e) {
		colorLineButton.addActionListener(e);
	}
	public void addColorFillListener(ActionListener e) {
		colorFillButton.addActionListener(e);
	}
	
	public void addClickListener(MouseListener e) {
		drawingPane.addMouseListener(e);
	}
	
	public void addMouseWheelListener(MouseWheelListener e) {
		drawingPane.addMouseWheelListener(e);
	}

	public void addDragListener(MouseMotionListener e) {
		drawingPane.addMouseMotionListener(e);
	}
	public void addDeleteListener(ActionListener e) {
		deleteButton.addActionListener(e);
	}
	public void addDeleteAllListener(ActionListener e) {
		deleteAllButton.addActionListener(e);
	}
	public void addSaveListener(ActionListener e) {
		saveButton.addActionListener(e);
	}
	
	public void addLoadListener(ActionListener e) {
		loadButton.addActionListener(e);
	}
	
	
	public void addRectListener(ActionListener e) {
		rectButton.addActionListener(e);
	}

	public void addEllipseListener(ActionListener e) {
		ellipseButton.addActionListener(e);
	}
	
	public void addTriListener(ActionListener e) {
		triButton.addActionListener(e);
	}
	
	public void addDiamondListener(ActionListener e) {
		diamondButton.addActionListener(e);
	}
	
	public void addLineListener(ActionListener e) {
		lineButton.addActionListener(e);
	}
	
	public void addPolygonListener(ActionListener e) {
		polygonButton.addActionListener(e);
	}
	
	public void addPenListener(ActionListener e) {
		penButton.addActionListener(e);
	}
	public void setThicknessLabel(int thickness) {
		this.thicknessLabel.setText(Integer.toString(thickness));
	}
	public void setColorFillButton(Color color) {
		this.colorFillButton.setBackground(color);
	}
	public void setColorLineButton(Color color) {
		this.colorLineButton.setBackground(color);
	}

	public void setEditPointButton(boolean state) {
		this.editPointButton.setVisible(state);
	}
	public JPopupMenu getPopup() {
		return popup;
	}
	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}
	public class viewPanel extends JPanel{	
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<Shape> mylist= null;
		
	    viewPanel(ArrayList<Shape> input){
	        mylist = input;
	    }	
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        if (!mylist.isEmpty()) {
	        	for(Shape x : mylist) {
		        	x.draw(g);
		        }
	        }
	    }
	}
}
