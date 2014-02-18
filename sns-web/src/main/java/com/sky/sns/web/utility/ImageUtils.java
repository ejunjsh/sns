package com.sky.sns.web.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.sky.sns.common.DateUtils;


public class ImageUtils {
    public static String SaveToTemporary(File file) throws IOException
    {
    	String guid = java.util.UUID.randomUUID().toString();
		String fileName = guid + ".jpg";
		String path="/uploadedFile/tmp/"+ DateUtils.format(new Date(),"yyyy-MM-dd");
		String realPath = ServletActionContext.getServletContext().getRealPath(path);
		File savefile = new File(new File(realPath), fileName);
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
		
		FileUtils.copyFile(file, savefile);
		
		return path+"/"+fileName;
    }
    
    public static String SaveToRealDevice(String relativeUrl,String type,boolean isWatermark) throws IOException
    {
    	String tmpPath = ServletActionContext.getServletContext().getRealPath(relativeUrl);
    	File tmpFile=new File(tmpPath);
    	String returnPath=SaveToRealDevice(tmpFile,type,isWatermark);
		tmpFile.delete();
    	return returnPath;
    }
    
    public static String SaveToAvatar(String relativeUrl,String type,boolean isWatermark) throws IOException
    {
    	String tmpPath = ServletActionContext.getServletContext().getRealPath(relativeUrl);
    	File tmpFile=new File(tmpPath);
    	String returnPath=SaveToRealDevice(tmpFile,type,isWatermark);
    	String realPath=ServletActionContext.getServletContext().getRealPath(returnPath);
		//scale image to three sizes.
		String realFileName160=realPath.replace(".jpg", "_160.jpg");
		String realFileName48=realPath.replace(".jpg", "_48.jpg");
		String realFileName24=realPath.replace(".jpg", "_24.jpg");
		Thumbnails.of(tmpFile).outputQuality(1).outputFormat("jpg")
        .size(160, 160)
        .toFile(new File(realFileName160));
		Thumbnails.of(tmpFile).outputQuality(1).outputFormat("jpg")
        .size(48, 48)
        .toFile(new File(realFileName48));
		Thumbnails.of(tmpFile).outputQuality(1).outputFormat("jpg")
        .size(24, 24)
        .toFile(new File(realFileName24));
    	
		tmpFile.delete();
    	return returnPath;
    }
    
    public static String SaveToRealDevice(File file,String type,boolean isWatermark) throws IOException
    {
    	String guid = java.util.UUID.randomUUID().toString();
		String fileName = guid + ".jpg";
		String fileNameNormal= guid + "_normal.jpg";
		String fileNameThumbnail= guid + "_thumbnail.jpg";
		String path="/uploadedFile/"+type+"/"+ DateUtils.format(new Date(),"yyyy-MM-dd");
		String realPath = ServletActionContext.getServletContext().getRealPath(path);
		File savefile = new File(new File(realPath), fileName);
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
		
		FileUtils.copyFile(file, savefile);
		
		String waterPath=ServletActionContext.getServletContext().getRealPath("/staticFile/img/watermark.png");
		String realFileNameNormal=realPath+"/"+fileNameNormal;
		String realFileThumbnail=realPath+"/"+fileNameThumbnail;
		BufferedImage image=ImageIO.read(file);
		int normalWidth=0;
		int normalHeight=0;
		if(image.getWidth()>580||image.getHeight()>580)
		{

				if(image.getWidth()>580)
				{
				   normalWidth=580;
				   normalHeight=(int)(((double)image.getHeight()/image.getWidth())*580);
				}
				else
				{
					normalWidth=image.getWidth();
					normalHeight=image.getHeight();
				}
			
		}
		else
		{
			normalWidth=image.getWidth();
			normalHeight=image.getHeight();
		}
		
		if(isWatermark)
		{
		Thumbnails.of(savefile).outputQuality(1).outputFormat("jpg")
        .size(normalWidth, normalHeight)
        .keepAspectRatio(false)   
        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(waterPath)), 0.3f)
        .toFile(new File(realFileNameNormal));
		}
		else
		{
			Thumbnails.of(savefile).outputQuality(1).outputFormat("jpg")
	        .size(normalWidth, normalHeight)
	        .keepAspectRatio(false)   
	        .toFile(new File(realFileNameNormal));
		}
		
		
		int thumbWidth=0;
		int thumbHeight=0;
		if(image.getWidth()>image.getHeight()&&image.getWidth()>160)
		{

			thumbHeight=160;
		    thumbWidth=(int)(((double)image.getWidth()/image.getHeight())*160);
			
		}
		else if(image.getWidth()<image.getHeight()&&image.getHeight()>160)
		{
			thumbWidth=160;
			thumbHeight=(int)(((double)image.getHeight()/image.getWidth())*160);
		}
		else
		{
			thumbWidth=image.getWidth();
			thumbHeight=image.getHeight();
		}
		
		Thumbnails.of(savefile).outputQuality(1).outputFormat("jpg")
        .size(thumbWidth,thumbHeight)
        .keepAspectRatio(false)
        .toFile(new File(realFileThumbnail));
		
		return path+"/"+fileName;
    }
} 
