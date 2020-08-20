package paint;

import java.util.ArrayList;

public class MVCpaint {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Shape> theModel = new ArrayList<Shape>();
		paintView theView = new paintView(theModel);
		paintController theController = new paintController(theView, theModel);
	}
}
