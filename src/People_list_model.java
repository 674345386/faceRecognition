import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class People_list_model extends DefaultTableModel {

    private String jsonString_1;
    private String jsonString_2;

    private String people;
    private String face_id;
    private String gender_value, race_value;
    private double gender_confi, race_confi, age_value, age_range;
    private String[] infoStrings_1;
    private String[] infoStrings_2;

    static Object[] columnNames = {"检测到的人", "face_id", "性别", "性别准确率",// 列名最好用final修饰
            "年龄", "年龄误差范围", "种族", "种族准确率"};
    static Object[][] rowData = {{"检测到的人", "face_id", "性别", "性别准确率",// 列名最好用final修饰
            "年龄", "年龄误差范围", "种族", "种族准确率"}};

    static Object[] columnNames_2 = {"参与检测的人", "相似的人人名", "相似的人ID", "相似率",// 列名最好用final修饰
    };
    static Object[][] rowData_2 = {{"参与检测的人", "相似的人人名", "相似的人ID", "相似率",// 列名最好用final修饰
    }};

    People_list_model(String jString, int i) {
        super(rowData, columnNames);
        this.jsonString_1 = jString;
        infoStrings_1 = new String[8];
        try {
            getInfo_detect();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 用于检测人是否在group中的构造函数
     *
     * @param jString
     */
    People_list_model(String jString) {
        super(rowData_2, columnNames_2);
        this.jsonString_2 = jString;
        infoStrings_2 = new String[4];
        try {
            getInfo_identify();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getInfo_detect() throws JSONException {
        JSONTokener jsonTokener = new JSONTokener(jsonString_1);
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

            infoStrings_1[0] = "people_" + i;
            infoStrings_1[1] = face_id;
            infoStrings_1[2] = gender_value;
            infoStrings_1[3] = String.valueOf(gender_confi) + "%";
            infoStrings_1[4] = String.valueOf(age_value);
            infoStrings_1[5] = "±" + String.valueOf(age_range);
            infoStrings_1[6] = race_value;
            infoStrings_1[7] = String.valueOf(race_confi) + "%";

            this.addRow(infoStrings_1);
        }
    }


    /**
     * 用于检测face是否在一个group中的结果表示,注意用到的json不一样
     *
     * @throws JSONException
     */

    public void getInfo_identify() throws JSONException {
        JSONTokener jsonTokener = new JSONTokener(jsonString_2);
        // JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
        // picWidth = jsonObject.getInt("img_width");
        // System.out.println("the pic width is  " + picWidth);
        // picHeight = jsonObject.getInt("img_height");
        // System.out.println("the pic height is   " + picHeight);
        int flag = 0;
        String person_ID;
        String person_name;
        double confi;
        JSONArray faceArray = jsonObject.getJSONArray("face");
        for (int i = 0; i < faceArray.length(); i++) {
            JSONObject personjObject = faceArray.getJSONObject(i);
            JSONArray candidateArray = personjObject.getJSONArray("candidate");
            // this.addElement(face_id);

            for (int j = 0; j < candidateArray.length(); j++) {
                JSONObject j1 = candidateArray.getJSONObject(j);
                confi = j1.getDouble("confidence");
                person_name = j1.getString("person_name");
                person_ID = j1.getString("person_id");

                infoStrings_2[0] = "要检测的人" + 0;
                infoStrings_2[1] = person_name;   //相似的人名
                infoStrings_2[2] = person_ID;
                infoStrings_2[3] = String.valueOf(confi);

                if (confi < 68) {
                    JOptionPane.showMessageDialog(null, "您不在该group中", null, ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "欢迎回来， " + person_name);
                }

                System.out.println(infoStrings_2);
                this.addRow(infoStrings_2);

            }
        }
    }


//			this.addRow(infoStrings);
//        }


    public String getSimuNmae() {
        return infoStrings_2[1];
    }
}
