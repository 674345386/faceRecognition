import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileToByte {

	public static byte[] convert(String filePathName) {

		FileInputStream fStream = null;
		File file = null;
		byte[] imgByte = null;

		try {
			file = new File(filePathName);
			fStream = new FileInputStream(file);

			imgByte = new byte[(int) file.length()];
			int offset = 0;
			int readNum = 0;
			final int length = (int) file.length();
			while (offset < length
					&& (readNum = fStream.read(imgByte, offset, length - offset)) != -1) {
				offset += readNum;

			}
			// 确保所有数据均被读取
			if (offset < length) {
				throw new IOException("file is not read completely");
			}

		} catch (IOException e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			// 关闭流
			if (fStream != null) {
				try {
					fStream.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return imgByte;
	}

}
