import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.Timer;

import org.bytedeco.javacv.CanvasFrame;


public class TimerAction implements ActionListener{
	private CanvasFrame canvasFrame;
	private Timer timer;
	private Graphics2D graphics2d;
	private int width;
	private int height;
	private int count_for_rect=0;
	private int delta =15;
	/**
	 * 构造函数 
	 */
	public TimerAction(int width,int height,BufferedImage bImage){
		this.width=width;
		this.height=height;
		this.graphics2d=(Graphics2D) bImage.getGraphics();
		graphics2d.setColor(Color.WHITE);
	}
	public void setTimer(Timer timer){
		this.timer=timer;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int offset =delta *count_for_rect;
		if(width-2*offset>=0 && height-2*offset>=0){
			graphics2d.drawRoundRect(offset, offset, width-2*offset, height-2*offset,50,50);
			count_for_rect++;
			canvasFrame.repaint();
		}
		else {
			timer.stop();
			count_for_rect=0;
		}
	}
//	private Graphics2D g;
//	private CanvasFrame canvasFrame;
//	private int width, height;
//
//	private int delta = 10;
//	private int count = 0;
//
//	private Timer timer;
//
//	public void setTimer(Timer timer) {
//		this.timer = timer;
////		this.timer.setRepeats(false);
//	}
//
//	public TimerAction(CanvasFrame canvasFrame) {
//		this.g = (Graphics2D) canvasFrame.getCanvas().getGraphics();
//		this.canvasFrame = canvasFrame;
//		this.width = canvasFrame.getWidth();
//		this.height = canvasFrame.getHeight();
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		int offset = delta * count;
//		if (width - offset >= offset && height - offset >= offset) {
//			g.draw3DRect(offset, offset, width - 2 * offset, height - 2 * offset,false);
//			canvasFrame.repaint();
//			count++;
//		} else {
//			// when animation is done, reset count and stop timer.
//			timer.stop();
//			count = 0;
//		}
//	}
}
