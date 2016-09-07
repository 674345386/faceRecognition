import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class Face_dect_info {

	HttpRequests httpRequests = new HttpRequests("dfcee6cac9ee7014a4835d5e45609db5",
			"OkSg46DKZka1ciFEt8ow6b-PFUVlTSbn", true, true);

	JSONObject result = null;

	private int arrayLength;
	private byte[] imgbyte;

	// public static void main(String[] args) {
	// try {
	// new Face_dect_info().the_whole();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // TODO Auto-generated method stub
	// // replace api_key and api_secret here (note)
	// // HttpRequests httpRequests = new
	// // HttpRequests("dfcee6cac9ee7014a4835d5e45609db5",
	// // "OkSg46DKZka1ciFEt8ow6b-PFUVlTSbn", true, true);
	//
	// // JSONObject result = null;
	// }

	public String the_whole(String file_path) throws Exception {
		System.out.println(Charset.forName("UTF-8").name());
		System.out.println("正在进行..");

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
		// byte[] imgbyte = FileToByte.convert(file_path);
		imgbyte = FileToByte.convert(file_path);
		result = httpRequests.detectionDetect(new PostParameters().setImg(imgbyte));

		for (int i = 0; i < result.getJSONArray("face").length(); ++i) {
			System.out.println("识别出的人脸的face_id :" + i);
			System.out.println(result.getJSONArray("face").getJSONObject(i).getString("face_id")
					+ "\n");
		}
		System.out.println("-----------------------");
		// System.out.println(result.getJSONArray("face").getJSONObject(10)
		// .getJSONObject("position").getJSONObject("center"));
		System.out.println("服务器返回的整个json");
		System.out.println(result);
		this.arrayLength = result.getJSONArray("face").length();
		System.out.println("array长度为   " + this.arrayLength);
		System.out.println("-----------------------");
		System.out.println("return result");
		return result.toString();
	}

	//
	// // 输出表单MultiEnrty信息
	// // -----------------------
	// // --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t
	// // Content-Disposition: form-data;
	// // name="person_name"
	// // Content-Type: text/plain; charset=UTF-8
	// // Content-Transfer-Encoding: 8bit
	// //
	// // person_0
	// // --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t
	// // Content-Disposition: form-data; name="face_id"
	// // Content-Type: text/plain; charset=UTF-8
	// // Content-Transfer-Encoding: 8bit
	// //
	// // f9e5f80931f71dcbaaf475f72d06b89f
	// // --UMPQovzg3Cu1PgQeLecfMdjJypemRv2t--
	// new PostParameters().setPersonName("person_" + 0)
	// .setFaceId(result.getJSONArray("face").getJSONObject(0).getString("face_id"))
	// .getMultiPart().writeTo(System.out);
	//
	// // --------------------Person------------------
	// // person/create
	// System.out.println("-----------------------");
	// System.out.println("create person");
	// for (int i = 0; i < result.getJSONArray("face").length(); i++) {
	// System.out.println(httpRequests.personCreate(new PostParameters()
	// .setPersonName("person_" + i)));
	//
	// }
	//
	// person/add face(face_id) into each person
	// if success ,will return
	// {
	// "added": 2,
	// "success": true
	// }
	// person/add_face
	// System.out.println("-----------------------");
	// System.out.println("\nperson/add_face");
	// for (int i = 0; i < result.getJSONArray("face").length(); ++i)
	// System.out.println(httpRequests.personAddFace(new
	// PostParameters().setPersonName(
	// "person_" + i).setFaceId(
	// result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
	//
	// // person/set_info
	// System.out.println("-----------------------");
	// System.out.println("\nperson/set_info");
	// for (int i = 0; i < result.getJSONArray("face").length(); ++i) {
	// new PostParameters().setPersonName("person_" + i).setTag("中文 tag_" +
	// i)
	// .getMultiPart().writeTo(System.out);
	// System.out.println(httpRequests.personSetInfo(new
	// PostParameters().setPersonName(
	// "person_" + i).setTag("中文 tag_" + i))+"\n");
	// }

	public void people_create(String name) {
		System.err.println("------------people_create--------------");
		for (int i = 0; i < arrayLength; i++) {
			try {
				System.out.println(httpRequests.personCreate(new PostParameters()
						.setPersonName(name)));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		people_add_face();
	}

	public void people_add_face() {
		System.err.println("------------people_add_face--------------");
		for (int i = 0; i < arrayLength; ++i)
			try {
				System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName(
						"people_" + i).setFaceId(
						result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 先识别一个人的脸，将其face添加到相应的person (name)
	 * @param result
	 */
	public void people_add_face_useFaceID(String result,String name) {
		System.err.println("------------people_add_face_useFaceID--------------");
		
			try {
				JSONObject jsonObject = new JSONObject(result);
				System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName(
						name).setFaceId(
								jsonObject.getJSONArray("face").getJSONObject(0).getString("face_id"))));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void people_delete(String name) {
		System.err.println("------------personDelete--------------");
		for (int i = 0; i < arrayLength; i++) {
			try {
				System.out.println(httpRequests.personDelete(new PostParameters()
						.setPersonName(name)));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void people_addIn_group() {
		System.err.println("------------people_addIn_group--------------");
		for (int i = 0; i < arrayLength; i++) {
			try {
				System.out.println(httpRequests.groupAddPerson(new PostParameters().setPersonName(
						"people_" + i).setGroupName("group_1")));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * 获取一个Person的信息, 包括name, id, tag, 相关的face, 以及groups等信息
	 */
	public void people_getInfo() {
		System.err.println("------------people_getInfo--------------");
		
			try {
				System.out.println(httpRequests.personGetInfo(new PostParameters().setPersonName(
						"people_" + 1)));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	// -----------------Group-----------------
	// group create
	// System.out.println("-----------------------");
	// System.out.println("create group");
	// System.out.println(httpRequests.groupCreate(new PostParameters()
	// .setGroupName("group_0")));
	// group add people
	// create people list
	// ArrayList<String> personArrayList = new ArrayList<String>();
	// for (int i = 0; i < result.getJSONArray("face").length(); i++) {
	// personArrayList.add("person_" + i);
	// }

	// 表单信息
	// new
	// PostParameters().setGroupName("group_0").setPersonName(personArrayList).getMultiPart()
	// .writeTo(System.out);
	// System.out.println("grouo 信息，里面包括的person");
	// System.out.println(httpRequests.groupGetInfo(new
	// PostParameters().setGroupName("group_0")));
	// System.out.println("把人物（person_id）加入到group中");
	// System.out.println(httpRequests.groupAddPerson(new
	// PostParameters().setGroupName("group_0").setPersonName(personArrayList)));
	public void group_create(String name) {
		System.err.println("------------group_create--------------");
		// for (int i = 0; i < arrayLength; i++) {
		try {
			System.out.println(httpRequests.groupCreate(new PostParameters()
					.setGroupName(name)));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

	public void group_delete(String name) {
		// System.err.println("------------group_delete--------------");
		// for (int i = 0; i < arrayLength; i++) {
		try {
			System.out.println(httpRequests.groupDelete(new PostParameters()
					.setGroupName(name)));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

	public void group_getinfo(String group) {
		System.err.println("------------group_getinfo--------------");
		try {
			System.out.println(httpRequests.groupGetInfo(new PostParameters()
					.setGroupName(group)));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 针对verify功能对一个person进行训练。请注意: 在一个person内进行verify之前，必须先对该person进行Train
	 * 当一个person内的数据被修改后(例如增删Person相关的Face等)，为使这些修改生效，person应当被重新Train
	 * Train所花费的时间较长, 因此该调用是异步的，仅返回session_id。
	 * 训练的结果可以通过/info/get_session查询。当训练完成时，返回值中将包含{"success": true}
	 */
	public void train_verify_for_people() {
		System.err.println("------------train_verify_for_people--------------");
		for (int i = 0; i < arrayLength; i++) {
			try {
				System.out.println(httpRequests.trainVerify(new PostParameters()
						.setPersonName("people_" + i)));
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 针对search功能对一个faceset进行训练。请注意:
	 * 
	 * 在一个faceset内进行search之前，必须先对该faceset进行Train
	 * 当一个faceset内的数据被修改后(例如增删Face等)，为使这些修改生效，faceset应当被重新Train Train所花费的时间较长,
	 * 因此该调用是异步的，仅返回session_id。
	 * 训练的结果可以通过/info/get_session查询。当训练完成时，返回值中将包含{"success": true}
	 */
	public void train_search_for_faceset() {
		System.err.println("------------train_search_for_faceset--------------");
		try {
			System.out.println(httpRequests.trainSearch(new PostParameters()
					.setFacesetName("faceset_name")));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 针对identify功能对一个Group进行训练。请注意:
	 * 
	 * 在一个Group内进行identify之前，必须先对该Group进行Train 当一个Group内的数据被修改后(例如增删Person,
	 * 增删Person相关的Face等)，为使这些修改生效，Group应当被重新Train Train所花费的时间较长,
	 * 因此该调用是异步的，仅返回session_id。 Train时需要保证group内的所有person均非空。
	 * 训练的结果可以通过/info/get_session查询。当训练完成时，返回值中将包含{"success": true}
	 */
	public void train_identify_for_group() {
		System.err.println("------------train_identify_for_group--------------");
		try {
			System.out.println(httpRequests.trainIdentify(new PostParameters()
					.setGroupName("group_1")));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------------------recognition----------------------

	/**
	 * 描述
	 * 
	 * 对于一个待查询的Face列表（或者对于给定的Image中所有的Face），在一个Group中查询最相似的Person。
	 * 
	 * 注意，当Group中的信息被修改之后（增加了Person, Face等），为了保证结果与最新数据一致，Group应当被重新train.
	 * 见/train/identify 。否则调用此API时将使用最后一次train时的数据。
	 * 先拍照识别，再检测
	 */
	public String recognition_identify_face_inGroup() {
		JSONObject result = null;
		try {
			 result =httpRequests.recognitionIdentify(new PostParameters().setGroupName(
					"group_1").setImg(imgbyte));
			System.out.println(result);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}

	public void recognition_verify_face_isSamePerson() {
		try {
			try {
				System.out.println(httpRequests.recognitionVerify(new PostParameters().setPersonName(
						"people_1").setFaceId(result.getJSONArray("face").getJSONObject(0).getString("face_id"))));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void info_getsession() {
		try {
			System.out.println(httpRequests.infoGetSession(new PostParameters()
					.setSessionId("added6106d1a12c200feed3f909c1b10")));
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
