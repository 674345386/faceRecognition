import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Thread_for_camera implements Runnable {

	private OpenCVFrameGrabber grabber;
	private Frame image;
	private Java2DFrameConverter converter;
	private JLabel jLabel;
	private boolean timer_on;
	private Timer timer;

	/**
	 * 
	 */

//	public Thread_for_camera(OpenCVFrameGrabber grabber, Frame image,
//			Java2DFrameConverter converter, JLabel jLabel) {
//
//	}

	public Thread_for_camera(OpenCVFrameGrabber grabber2, Frame image2,
			Java2DFrameConverter converter2, JLabel jLabel2,boolean timer_on) {
		this.grabber=grabber2;
		this.image =image2;
		this.converter=converter2;
		this.jLabel=jLabel2;
		this.timer_on=timer_on;
	}

	
	public void action(BufferedImage saveImage){
		TimerAction timerAction = new TimerAction(640, 480, saveImage);
		Timer timer = new Timer(10, timerAction);
		timerAction.setTimer(timer);
		timer.start();		// stop在timerAction内
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			grabber.start();
			System.out.println("打开摄像头....");
			while ( (null != (image = grabber.grab()) ) ) {
				BufferedImage saveImage = converter.convert(image);
				ImageIcon imageIcon = new ImageIcon(saveImage);
				jLabel.setIcon(imageIcon);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
