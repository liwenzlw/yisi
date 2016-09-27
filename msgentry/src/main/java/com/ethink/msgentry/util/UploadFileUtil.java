package com.ethink.msgentry.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

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
		if (!ConstantUtil.SUFFIXS.contains(contentType)) {
			return "error";
		}
		// 保存图片
		Date now = new Date();
		// 图片存放的目录的名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = sdf.format(now);
		String fileName = String.valueOf(now.getTime())
				+ String.format("%06d", (int) (new Random().nextDouble() * 1000000)) + ".jpg" ;
		// 图片的全路径
		String fullPath = realPath + "../project_resources/ueditor" + File.separator + "upload" + File.separator + "image" + File.separator
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
			
			Image img = ImageIO.read(inputStream);
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuffer.getGraphics().drawImage(img, 0, 0, width, height, null); // 绘制缩小后的图
			// 可以正常实现bmp、png、gif转jpg
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(imageBuffer); // JPEG编码
//			int length = inputStream.available();
//			byte[] buf = new byte[length];
//			inputStream.read(buf, 0, length);
//			fos.write(buf);
			fos.flush();
			fos.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
		}
		// 数据库中保存的文件的路径
		String savePath = "../project_resources/ueditor" + File.separator + "upload" + File.separator + "image" + File.separator + dir
				+ File.separator + fileName;
		return savePath;
	}
}
