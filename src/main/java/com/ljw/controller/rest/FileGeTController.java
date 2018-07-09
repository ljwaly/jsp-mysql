package com.ljw.controller.rest;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ljw.controller.BaseController;
import com.ljw.exception.FileEmptyException;
import com.ljw.vo.reponse.Response;

@RestController
@RequestMapping("/getfile")
public class FileGeTController extends BaseController{

	/**
	 * 
	 * @param file
	 *            :待上传文件
	 * @return
	 * @throws AbstractBaseException
	 */
	@RequestMapping(value = "/io")
	public Response saveAll(@RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest request,
			@RequestParam(value = "controlId") String controlId) throws Exception {
		 String originalFilename = uploadfile.getOriginalFilename();
		if (originalFilename == null || "".equals(originalFilename)) {
			throw new FileEmptyException("uploadfile error","uploadfile IS NULL");
		}
		String type = originalFilename.substring(uploadfile.getOriginalFilename().indexOf("."));// 取文件格式后缀名
		String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
		String path = request.getSession().getServletContext().getRealPath("/upload/" + filename);// 存放位置
		File destFile = new File(path);

		String readFileToString = null;
		try {
			FileUtils.copyInputStreamToFile(uploadfile.getInputStream(), destFile);
			readFileToString = FileUtils.readFileToString(destFile, "UTF-8");// 读取文件内容
		} catch (IOException e) {
			throw e;
		} // 复制临时文件到指定目录下

		if (readFileToString == null || "".equals(readFileToString)) {// 文件内容不存在
			throw new FileEmptyException("FILE_EMPTY", "file is empty");
		}
		System.out.println("readFileToString="+readFileToString);
		
		Response response = new Response();
		response.setResultCode("success");
		response.setResultDesc(readFileToString);
		
		return response;
	}
}
