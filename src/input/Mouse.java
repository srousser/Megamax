package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	public int mousex = 0;
	public boolean clicked = false;

	public void mouseClicked(MouseEvent e) {
		clicked = true;
	}

	public void mousePressed(MouseEvent e) {
		mousex = e.getX();
	}

	public void mouseReleased(MouseEvent e) {
		mousex = e.getX();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		mousex = e.getX();
	}

	public void mouseMoved(MouseEvent e) {
		mousex = e.getX();
	}

}