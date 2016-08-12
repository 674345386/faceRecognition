import java.io.File;
import java.nio.charset.Charset;

import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// replace api_key and api_secret here (note)
		HttpRequests httpRequests = new HttpRequests("dfcee6cac9ee7014a4835d5e45609db5",
				"OkSg46DKZka1ciFEt8ow6b-PFUVlTSbn", true, true);

		JSONObject result = null;

		try {

			System.out.println(Charset.forName("UTF-8").name());

			System.out.println("FacePlusPlus API Test:");

			// detection/detect
			// result = httpRequests
			// .detectionDetect(new PostParameters()
			// .setUrl("http://h.hiphotos.baidu.com/zhidao/pic/item/eac4b74543a98226984cb9f88b82b9014b90eb64.jpg"));

			// 使用file传输
			// File imgFile = new
			// File("C:\\Users\\cp\\Desktop\\abcd.jpg");
			// result = httpRequests.detectionDetect(new
			// PostParameters().setImg(imgFile));

			// 使用byte[]传输
			byte[] imgbyte = FileToByte.convert("C:\\Users\\cp\\Desktop\\abcd.jpg");
			result = httpRequests.detectionDetect(new PostParameters().setImg(imgbyte));

			for (int i = 0; i < result.getJSONArray("face").length(); ++i) {
				System.out.println(result.getJSONArray("face").getJSONObject(i)
						.getString("face_id")
						+ "\n");
			}
			System.out.println("-----------------------");
			// System.out.println(result.getJSONArray("face").getJSONObject(10)
			// .getJSONObject("position").getJSONObject("center"));
			System.out.println(result);
			System.out.println("-----------------------");

			// 输出表单MultiEnrty信息
			// -----------------------
			// --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t
			// Content-Disposition: form-data;
			// name="person_name"
			// Content-Type: text/plain; charset=UTF-8
			// Content-Transfer-Encoding: 8bit
			//
			// person_0
			// --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t
			// Content-Disposition: form-data; name="face_id"
			// Content-Type: text/plain; charset=UTF-8
			// Content-Transfer-Encoding: 8bit
			//
			// f9e5f80931f71dcbaaf475f72d06b89f
			// --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t--
			new PostParameters().setPersonName("person_" + 0)
					.setFaceId(result.getJSONArray("face").getJSONObject(0).getString("face_id"))
					.getMultiPart().writeTo(System.out);

			// --------------------Person------------------
			// person/create
			System.out.println("-----------------------");
			System.out.println("create person");
			for (int i = 0; i < result.getJSONArray("face").length(); i++) {
				System.out.println(httpRequests.personCreate(new PostParameters()
						.setPersonName("person_" + i)));

			}

			// person/add face(face_id) into each person
			// if success ,will return
			// {
			// "added": 2,
			// "success": true
			// }
			// person/add_face
			System.out.println("\nperson/add_face");
			for (int i = 0; i < result.getJSONArray("face").length(); ++i)
				System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName(
						"person_" + i).setFaceId(
						result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
			// person/set_info
			System.out.println("-----------------------");
			System.out.println("\nperson/set_info");
			for (int i = 0; i < result.getJSONArray("face").length(); ++i) {
				new PostParameters().setPersonName("person_" + i).setTag("中文 tag_" + i)
						.getMultiPart().writeTo(System.out);
				System.out.println(httpRequests.personSetInfo(new PostParameters().setPersonName(
						"person_" + i).setTag("中文 tag_" + i))+"\n");
			}
		} catch (FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
