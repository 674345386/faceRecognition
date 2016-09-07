import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class People_list_model extends DefaultTableModel {

	private String jsonString;

	private String people;
	private String face_id;
	private String gender_value, race_value;
	private double gender_confi, race_confi, age_value, age_range;
	private String[] infoStrings;

	static Object[] columnNames = { "检测到的人", "face_id", "性别", "性别准确率",// 列名最好用final修饰
			"年龄", "年龄误差范围", "种族", "种族准确率" };
	static Object[][] rowData = { { "检测到的人", "face_id", "性别", "性别准确率",// 列名最好用final修饰
			"年龄", "年龄误差范围", "种族", "种族准确率" } };
	
	static Object[] columnNames_2 = { "参与检测的人", "相似的人人名", "相似的人ID", "相似率",// 列名最好用final修饰
		};
    static Object[][] rowData_2 = { { "参与检测的人", "相似的人人名", "相似的人ID", "相似率",// 列名最好用final修饰
	} };

	People_list_model(String jString,int i) {
		super(rowData, columnNames);
		this.jsonString = jString;
		infoStrings = new String[8];
		try {
			getInfo_detect();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 用于检测人是否在group中的构造函数
	 * @param jString
	 */
	People_list_model(String jString) {
		super(rowData_2, columnNames_2);
		this.jsonString = jString;
		infoStrings = new String[4];
		try {
			getInfo_identify();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getInfo_detect() throws JSONException {
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
			// this.addElement(face_id);
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

			infoStrings[0] = "people_" + i;
			infoStrings[1] = face_id;
			infoStrings[2] = gender_value;
			infoStrings[3] = String.valueOf(gender_confi) + "%";
			infoStrings[4] = String.valueOf(age_value);
			infoStrings[5] = "±" + String.valueOf(age_range);
			infoStrings[6] = race_value;
			infoStrings[7] = String.valueOf(race_confi) + "%";

			this.addRow(infoStrings);
		}
	}
	
	
	/**
	 * 用于检测face是否在一个group中的结果表示,注意用到的json不一样
	 * @throws JSONException
	 */
	
	public void getInfo_identify() throws JSONException {
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
			JSONArray candidateArray = personjObject.getJSONArray("candidate");
			// this.addElement(face_id);
			
			for (int j = 0; j < candidateArray.length(); j++) {
				JSONObject j1 =candidateArray.getJSONObject(j);
				double confi=j1.getDouble("confidence");
				String person_name =j1.getString("person_name");
				String person_ID=j1.getString("person_id");
				
				infoStrings[0] = "要检测的人"+i;
				infoStrings[1] = person_name;   //相似的人名
				infoStrings[2] = person_ID;
				infoStrings[3] = String.valueOf(confi);
				
				System.out.println(infoStrings);
				this.addRow(infoStrings);
				
			}
			
			
			
//			this.addRow(infoStrings);
		}
	}
}
