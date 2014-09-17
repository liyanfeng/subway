package com.subway.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.subway.service.FaultService;

@SuppressWarnings({ "serial", "rawtypes" })
public class UploadAction extends BaseAction{
    
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    
    @Autowired
	private FaultService fs;

    public String execute() throws Exception {
        String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
        //D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
        System.out.println("realpath: "+realpath);
        if (image != null) {
        	Date d=new Date();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        	String fileName=sdf.format(d)+imageFileName.substring(imageFileName.indexOf("."));
        	System.out.println(fileName);
            File savefile = new File(new File(realpath), fileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            ActionContext.getContext().put("message", "文件上传成功");
            System.out.println("fileName:"+realpath+"\\"+fileName);
            fs.restoreFaultTable(realpath+"\\"+fileName);
            
        }
        return NONE;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
}
