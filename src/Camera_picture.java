import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.omg.CORBA.PUBLIC_MEMBER;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;

/**
 * 
 * @description Use JavaCV/OpenCV to capture camera images
 * @Date 2016/8
 * @author chen peng
 * 
 */
public class Camera_picture {

	public static Java2DFrameConverter converter = new Java2DFrameConverter();

	private Frame image;
	public JLabel jLabel = new JLabel();
	public JLabel jLabel_2 = new JLabel();
	public String savedImageFile = "H:\\xiang15.jpg";
	
	public boolean timer_on =false ;

	OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0); // 打开摄像头grabber

	public JLabel get_jlabel() {
		return jLabel;
	}

	/**
	 * @description 打开摄像头
	 * @throws Exception
	 */
	public void open_camera() throws Exception {
		Thread_for_camera thread_for_camera = new Thread_for_camera(grabber, image, converter,
				jLabel,timer_on);
		Thread thread = new Thread(thread_for_camera);
		thread.start();
	
		// grabber.start();
		// System.out.println("打开摄像头....");
		// while (null!= (image=grabber.grab())){
		// BufferedImage saveImage = converter.convert(image);
		// ImageIcon imageIcon = new ImageIcon(saveImage);
		// jLabel.setIcon(imageIcon);
		// }
	}

	public void grab_picture() {
		// TimerAction timerAction = new TimerAction(canvasFrame);
		// final Timer timer = new Timer(10, timerAction);
		// timerAction.setTimer(timer);
		// timer.start(); // start animation
		
		Frame imageFrame = null;
		timer_on =true;
		
		
		try {
			imageFrame = grabber.grab();
		} catch (org.bytedeco.javacv.FrameGrabber.Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// TimerAction timerAction = new TimerAction(640,480,imageFrame);
		// final Timer timer = new Timer(10, timerAction);
		// timerAction.setTimer(timer);
		// timer.start(); // start animation

		BufferedImage saveImage = converter.convert(imageFrame);
//		Graphics2D graphics2d = (Graphics2D) saveImage.getGraphics();
//		graphics2d.setColor(Color.RED);
//		graphics2d.drawRect(5, 5, 150, 150);
		// bGraphics.drawImage(saveImage, null, 0, 0); //bGraphics 才能写进
		// bufferedImage
		try {
			ImageIO.write(saveImage, "jpg", new File(savedImageFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ImageIcon image = new ImageIcon("C:/Users/cp/Desktop/lena.jpg");

		ImageIcon imageIcon = new ImageIcon(saveImage);
		// jLabel.setIcon(imageIcon);

		jLabel_2.setIcon(imageIcon);
		// picFrame.showImage(saveImage);
	}

	// public static void main(String[] args) throws Exception {
//	public void grabPicture() throws Exception {
//
//		//
//
//		// Client_jframe client_jframe = new Client_jframe();
//		// client_jframe.init_add(jLabel);
//		//
//		// final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0); //
//		// 打开摄像头grabber
//		// grabber.start();
//
//		CanvasFrame canvasFrame = new CanvasFrame("Camera"); // 初始化Frame
//		// canvasFrame.setSize(300, 100);
//		Button btnButton = new Button("123");
//		canvasFrame.setLayout(new GridLayout(1, 2, 10, 10));
//		// canvasFrame.setSize(200, 200);
//		//
//
//		final ImageIcon icon2 = new ImageIcon("C:/Users/cp/Desktop/lena.jpg");
//		// final JLabel jLabel = new JLabel();
//
//
//		// canvasFrame.add(jLabel);
//		btnButton.setSize(20, 20);
//		// canvasFrame.add(btnButton);
//
//		final CanvasFrame picFrame = new CanvasFrame("picture"); // 用于显示拍下的照片
//
//		// picFrame.setSize(500, 500);
//		// picFrame.setVisible(true);
//		// picFrame.setBounds(500, 500, 200, 200);
//
//		Frame image = grabber.grab(); // 开始拍摄一张照片，用来提取摄像图长宽
//		int width = image.imageWidth;
//		System.out.println(width);
//		int height = image.imageHeight;
//		System.out.println(height);
//		canvasFrame.setCanvasSize(width, height); // 设置长宽
//
//		final BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 保存照片的buffere
//		// final Graphics2D bGraphics = bImage.createGraphics();
//
//		// animation timer
//		// TimerAction timerAction = new TimerAction(canvasFrame);
//		// final Timer timer = new Timer(10, timerAction);
//		// timerAction.setTimer(timer);
//
//		// click the frame to capture an image
//		canvasFrame.getCanvas().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				// timer.start(); // start animation
//
//				Frame imageFrame = null;
//
//				try {
//					imageFrame = grabber.grab();
//				} catch (org.bytedeco.javacv.FrameGrabber.Exception e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//
//				BufferedImage saveImage = converter.convert(imageFrame);
//				// bGraphics.drawImage(saveImage, null, 0, 0); //bGraphics 才能写进
//				// bufferedImage
//				try {
//
//					Graphics2D graphics2d = (Graphics2D) saveImage.getGraphics();// 识别画出矩形
//					graphics2d.setColor(Color.RED);
//					graphics2d.drawRect(5, 5, 150, 150);
//					ImageIO.write(saveImage, "jpg", new File(savedImageFile));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//				//
//
//				//
//				// ImageIcon image = new
//				// ImageIcon("C:/Users/cp/Desktop/lena.jpg");
//
//				ImageIcon imageIcon = new ImageIcon(saveImage);
//				// jLabel.setIcon(imageIcon);
//
//				jLabel_2.setIcon(imageIcon);
//				// picFrame.showImage(saveImage);
//
//			}
//		});

		// real-time image display
		// while ((image = grabber.grab()) != null) {
		// if (!timer.isRunning()) { // when animation is on, pause real-time
		// // display
		// canvasFrame.showImage(image);
		// BufferedImage saveImage = converter.convert(image);
		// ImageIcon imageIcon = new ImageIcon(saveImage);
		// jLabel.setIcon(imageIcon);
		// break;
		//
		// // draw the onscreen image simutaneously
		// // BufferedImage saveImage = converter.convert(image);
		// // bGraphics.drawImage(saveImage, null, 0, 0);
		// }
		// }

		// release resources
//		grabber.stop();
//		canvasFrame.dispose();
//	}
}
