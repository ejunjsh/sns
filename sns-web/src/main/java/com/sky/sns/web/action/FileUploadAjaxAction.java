package com.sky.sns.web.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.sns.mybatis.iService.IUserService;
import com.sky.sns.web.utility.ImageUtils;


@Controller
@Scope("prototype")
public class FileUploadAjaxAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060797524560513291L;

	@Autowired
	private IUserService userService;

	private InputStream inputStream;

	private File file;

	public String uploadToTemporary() throws Exception {
		if (curUser != null) {
			if (file != null) {
				String url=ImageUtils.SaveToTemporary(file);
				
				inputStream = new ByteArrayInputStream(("{\"status\":\"A00004\",\"data\":\""+url+"\",\"message\":\"上传成功\"}").getBytes("UTF-8"));
				return "text";
			}
			inputStream = new ByteArrayInputStream(
					"{\"status\":\"A00002\",\"data\":null,\"message\":\"上传失败\"}".getBytes("UTF-8"));
			return "text";
		}
		inputStream = new ByteArrayInputStream(
				"{\"status\":\"A00001\",\"data\":null}".getBytes("UTF-8"));
		return "text";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
