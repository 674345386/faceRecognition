import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DrawRec {

	private String jsonString; // 返回的json result
	private String picPath;

	private int picWidth;
	private int picHeight;

	private long faceWidth;
	private long faceHeight;

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// }

	DrawRec() {

	}

	public void setJsonString(String jsString) {
		this.jsonString = jsString;
	}

	public void setPicPath(String picString) {
		this.picPath = picString;
	}

	public ImageIcon draw(String picPath) {
		BufferedImage bImage = null;
		try {
			bImage = ImageIO.read(new File(picPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D graphics2d = (Graphics2D) bImage.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawRect(50, 50, 480, 480);

		ImageIcon imgIcon = new ImageIcon(bImage);
		return imgIcon;
	}

//	public ImageIcon draw(String picPath, double x, double y, double width, double height,
//			int picWidth, int picHeight) {
//		// BufferedImage bImage = null;
//		// try {
//		// File file = new File(picPath);
//		// bImage = ImageIO.read(file);
//		// } catch (Exception e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		// Graphics2D graphics2d = (Graphics2D) bImage.getGraphics();
//		// graphics2d.setColor(Color.WHITE);
//		graphics2d.drawRect((int) (x * picWidth / 100), (int) (y * picHeight / 100), (int) (1.5
//				* width * picWidth / 100), (int) (height * picHeight / 100));
//		// graphics2d.drawRect( x * picWidth/ 100, y * picHeight/100, width,
//		// height);
//		System.out.println(x * picWidth / 100.0);
//		System.out.println(y * picHeight / 100.0);
//		System.out.println(width * picWidth / 100.0);
//		System.out.println(height * picHeight / 100.0);
//		// graphics2d.drawRect(50, 50, 50, 50);
//
//		ImageIcon imgIcon = new ImageIcon(bImage);
//		return imgIcon;
//	}

	// public void draw(BufferedImage bImage){
	// Graphics2D graphics2d = (Graphics2D) bImage.getGraphics();
	// graphics2d.setColor(Color.WHITE);
	// graphics2d.drawRect(50, 50, 150, 150);
	// }

	class Face_jason {

		private double eye_left_X;
		private double eye_left_Y;
		private double eye_right_X;
		private double eye_right_Y;
		private double mouth_left_X;
		private double mouth_left_Y;
		private double mouth_right_X;
		private double mouth_right_Y;
		private String innerPath;

		BufferedImage bImage = null;
		Graphics2D graphics2d;

		Face_jason() {
			// this.innerPath = DrawRec.this.picPath;
			//

			try {
				File file = new File(picPath);
				bImage = ImageIO.read(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			graphics2d = (Graphics2D) bImage.getGraphics();
			
			graphics2d.setColor(new Color(51,153,255));
			graphics2d.setStroke(new BasicStroke(2f));
			}
				
			
		

		public ImageIcon detectFace() throws JSONException {

			JSONTokener jsonTokener = new JSONTokener(jsonString);
			// JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
			picWidth = jsonObject.getInt("img_width");
			System.out.println("the pic width is  " + picWidth);
			picHeight = jsonObject.getInt("img_height");
			System.out.println("the pic height is   " + picHeight);

			JSONArray faceArray = jsonObject.getJSONArray("face");
			int length =faceArray.length();
			if (length<=0) JOptionPane.showMessageDialog(null, "照片识别不出人脸，需要重新拍摄");
			for (int i = 0; i < faceArray.length(); i++) {
				JSONObject personjObject = faceArray.getJSONObject(i);

				faceWidth = personjObject.getJSONObject("position").getLong("width");
				System.out.println("faceWidth is " + faceWidth);
				faceHeight = personjObject.getJSONObject("position").getLong("height");
				System.out.println("faceHeight is " + faceHeight);
				eye_left_X = personjObject.getJSONObject("position").getJSONObject("eye_left")
						.getLong("x");
				System.out.println("eye_left_x is " + eye_left_X);
				eye_left_Y = personjObject.getJSONObject("position").getJSONObject("eye_left")
						.getLong("y");
				eye_right_X = personjObject.getJSONObject("position").getJSONObject("eye_right")
						.getLong("x");
				eye_right_Y = personjObject.getJSONObject("position").getJSONObject("eye_right")
						.getLong("y");
				//
				mouth_left_X = personjObject.getJSONObject("position").getJSONObject("mouth_left")
						.getLong("x");
				mouth_left_Y = personjObject.getJSONObject("position").getJSONObject("mouth_left")
						.getLong("y");
				mouth_right_X = personjObject.getJSONObject("position")
						.getJSONObject("mouth_right").getLong("x");
				mouth_right_Y = personjObject.getJSONObject("position")
						.getJSONObject("mouth_right").getLong("y");

				draw_face();
			}
			
			ImageIcon imgIcon = new ImageIcon(bImage);
			return imgIcon;

		}

		public void draw_face() {
			translate_and_draw((0.9 * eye_left_X), 0.83*(eye_left_Y + eye_right_Y) / 2,
					1.05 * eye_right_X - 0.95*eye_left_X,
					2.15 * ((mouth_left_Y + mouth_right_Y) / 2 - (eye_left_Y + eye_right_Y) / 2),
					picWidth, picHeight);
			// return draw(innerPath, (eye_left_X), (eye_left_Y + eye_right_Y) /
			// 2, faceWidth,
			// faceHeight ,
			// picWidth,
			// picHeight);

		}

		public void translate_and_draw(double x, double y, double width, double height,
				int picWidth, int picHeight) {
			graphics2d.drawRect((int) (x * picWidth / 100), (int) (y * picHeight / 100), (int) (1.5
					* width * picWidth / 100), (int) (height * picHeight / 100));

		}

	}

}
