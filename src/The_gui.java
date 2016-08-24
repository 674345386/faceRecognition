import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.bytedeco.javacv.CanvasFrame;

public class The_gui extends JFrame {

	public void go() {
		CanvasFrame canvasFrame = new CanvasFrame("摄像头画面");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Camera_picture().grabPicture();
	}

	
}
