package com.sky.sns.web.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

//freemaker����� 
public class FreeMarkerGenerator {

	Configuration cfg = null;

	public FreeMarkerGenerator(String templatePath) throws IOException {
		cfg = new Configuration();// ��ʼ��freemaker���ã�����һ��Configuration����
		cfg.setDefaultEncoding("UTF-8");// ���ñ���
		//cfg.setDirectoryForTemplateLoading(new File(.getRealPath(templatePath)));// ����freemakerģ��λ��
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), templatePath);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	public void create(String ftlTemplate, Map<?, ?> contents, String savePath,
			String saveFilename) throws IOException, TemplateException {
		Template temp = cfg.getTemplate(ftlTemplate);// ȡ��ģ���ļ�
		/* Merge data model with template */

		String realPath = ServletActionContext.getServletContext().getRealPath(
				savePath);
		File file = new File(realPath);
		if (!file.exists())
			file.mkdirs();

		Writer out = new OutputStreamWriter(new FileOutputStream(realPath + "/"
				+ saveFilename), "UTF-8");
		temp.process(contents, out);// ����
		out.flush();
	}

	public String parse(String ftlTemplate, Map<?, ?> contents)
			throws IOException, TemplateException {
		try {
			Template temp = cfg.getTemplate(ftlTemplate);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(baos,"UTF-8");
			temp.process(contents, out);
			out.flush();

			return baos.toString("UTF-8");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return "";

	}

}
