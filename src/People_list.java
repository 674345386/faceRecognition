import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class People_list extends DefaultListModel<String> implements ListSelectionListener {

	private String jsonString;

	private String face_id;
	private String gender_value, race_value ;
	private double gender_confi, race_confi,age_value,age_range;

	People_list(String jString) {
		this.jsonString = jString;
		try {
			getInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void getInfo() throws JSONException {
		JSONTokener jsonTokener = new JSONTokener(jsonString);
		// JSONObject jsonObject = new JSONObject(jsonString);
		JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
		// picWidth = jsonObject.getInt("img_width");
		// System.out.println("the pic width is  " + picWidth);
		// picHeight = jsonObject.getInt("img_height");
		// System.out.println("the pic height is   " + picHeight);

		JSONArray faceArray = jsonObject.getJSONArray("face");
		for (int i = 0; i < faceArray.length(); i++) {
			JSONObject personjObject = faceArray.getJSONObject(i);
			face_id = personjObject.getString("face_id");
			this.addElement(face_id);
			System.out.println(face_id);
			gender_value = personjObject.getJSONObject("attribute").getJSONObject("gender")
					.getString("value");
			gender_confi = personjObject.getJSONObject("attribute").getJSONObject("gender")
					.getDouble("confidence");
			race_value = personjObject.getJSONObject("attribute").getJSONObject("race")
					.getString("value");
			race_confi = personjObject.getJSONObject("attribute").getJSONObject("race")
					.getDouble("confidence");
			age_value = personjObject.getJSONObject("attribute").getJSONObject("age")
					.getDouble("value");
			age_range = personjObject.getJSONObject("attribute").getJSONObject("age")
					.getDouble("range");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultListModel#addElement(java.lang.Object)
	 */
	// @Override
	// public void addElement(String element) {
	// // TODO Auto-generated method stub
	// super.addElement(element);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultListModel#getElementAt(int)
	 */

}
