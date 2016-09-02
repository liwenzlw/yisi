package com.ethink.msgentry.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件工具类
 * 
 * @author liwen
 * @version 1.0
 */
public class UploadFileUtil {

	/**
	 * 保存图片，并返回图片路径
	 * 
	 * @param imgFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String saveImage(MultipartFile imgFile, String realPath) {
		/// ueditor/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}
		// 判断上传的文件是否是图片
		String contentType = imgFile.getContentType();
		
		// 如果不是图片
		if (!ConstantUtil.suffixs.contains(contentType)) {
			return "{\"errorMsg\":\"您选择的不是图片\"}";
		}
		// 保存图片
		Date now = new Date();
		// 图片存放的目录的名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = sdf.format(now);
		String fileName = String.valueOf(now.getTime())
				+ String.format("%06d", (int) (new Random().nextDouble() * 1000000)) + "." + contentType.split("/")[1];
		// 图片的全路径
		String fullPath = realPath + "ueditor" + File.separator + "upload" + File.separator + "image" + File.separator
				+ dir + File.separator + fileName;
		File image = new File(fullPath);
		if (!image.getParentFile().exists()) {
			image.getParentFile().mkdirs();
		}
		FileOutputStream fos = null;
		InputStream inputStream = null;
		try {
			image.createNewFile();
			fos = new FileOutputStream(image);

			inputStream = imgFile.getInputStream();
			int length = inputStream.available();
			byte[] buf = new byte[length];
			inputStream.read(buf, 0, length);
			fos.write(buf);
			fos.flush();
			fos.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		// 数据库中保存的文件的路径
		String savePath = "ueditor" + File.separator + "upload" + File.separator + "image" + File.separator + dir
				+ File.separator + fileName;
		return savePath;
	}
}
