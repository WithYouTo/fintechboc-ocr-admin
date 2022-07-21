package com.cindy.ocrdemo.util;

import com.cindy.ocrdemo.dto.FileUrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件操作工具类
 */
@Component
public class FileUtil {

	/**
	 * 读取文件内容为二进制数组
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] read(String filePath) throws IOException {

		InputStream in = new FileInputStream(filePath);
		byte[] data = inputStream2ByteArray(in);
		in.close();

		return data;
	}

	/**
	 * 流转二进制数组
	 *
	 * @param in
	 * @return
	 * @throws IOException
	 */
	static byte[] inputStream2ByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

	/**
	 * 文件保存到本地
	 * @param uploadFile
	 * @return
	 * @throws IOException
	 */
	public static String save(MultipartFile uploadFile, String uploadPath) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String format = sdf.format(new Date());
		File folder = new File(uploadPath + format);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		// 对上传的文件重命名，避免文件重名
		String oldName = uploadFile.getOriginalFilename();
		String newName = UUID.randomUUID().toString().replace("-", "")
				+ oldName.substring(oldName.lastIndexOf("."), oldName.length());
		// 文件保存
		uploadFile.transferTo(new File(folder, newName));

//		String localFilePath = uploadPath + newName;
//		String netFilePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ "/" + format + "/" + newName;


//		FileUrlDto fileUrlDto = new FileUrlDto();
//		fileUrlDto.setLocalFileUrl(uploadPath + newName);

		String imgName =  "/" + format + "/" + newName;

		return  imgName;
	}

}
