import java.awt.Color;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
	private static final long serialVersionUID = 1L;
	
	private static final int totalWidth = 500;
	private static final int totalHeight = 300;
	
	private static PText header;
	private static TextBoxNode[] myTextBoxes = new TextBoxNode[2];

	@Override
	public void addInitialNodes () {
		this.setBounds(0, 0, totalWidth, totalHeight);
		
		// add background box
		addColouredBox (0, 0, totalWidth, totalHeight, Color.LIGHT_GRAY);

		// add header text
		header = addText (0, 0, "Sorting is cool!");
		header.setTextPaint (Color.DARK_GRAY);
		
		// put in a text box (part of an array or list?)
		myTextBoxes[0] = addTextBox (0, 0, 30, 30, "13");
		// set the initial position by animating this with time of zero
		myTextBoxes[0].animateToPositionScaleRotation(-30, -30, 1, 0, 0);
		myTextBoxes[0].setPaint (Color.GREEN);
		myTextBoxes[0].setTextPaint (Color.RED);

		myTextBoxes[1] = addTextBox (0, 0, 30, 30, "42");
		// set the initial position by animating this with time of zero
		myTextBoxes[1].animateToPositionScaleRotation(-50, -50, 1, 0, 0);
		myTextBoxes[1].setPaint (Color.GREEN);
		myTextBoxes[1].setTextPaint (Color.RED);
	}
	
	public static void main (String[] args) {
		SortingAnimation screen = new SortingAnimation ();
		
		// wait for initialization before animating
		screen.waitForInitialization ();
		
		// perform animation steps...
		
		// parameters are x, y, scale, rotation (in radians), and time (in ms)
		waitForActivity (myTextBoxes[0].animateToPositionScaleRotation (10, 30, 0.5, 0, 3000));
		
		myTextBoxes[0].animateToColor(Color.BLUE, 4000);
		waitForActivity (myTextBoxes[0].animateToPositionScaleRotation (100, 30, 1.5, Math.toRadians (110), 4000));
		
		// Two animations can go at the same time.  You just need to give them the
		// same duration and then wait for the last one (or the slowest one)
		header.animateToPositionScaleRotation(0, 0, 2.0, 0, 3000);
		myTextBoxes[1].animateToPositionScaleRotation (150, 110, 1, 0, 5000);
		waitForActivity (myTextBoxes[0].animateToPositionScaleRotation (200, 110, 1, 0, 5000));
	}

	private static void waitForActivity (PActivity activity) {
		long endTime = activity.getStartTime() + activity.getDuration();
		while (System.currentTimeMillis() < endTime) {
			try {
				Thread.sleep (100);
			} catch (InterruptedException e) {
				// Whatever, I do what I want.
			}
		}
	}
}
