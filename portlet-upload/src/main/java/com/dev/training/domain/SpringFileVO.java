package com.dev.training.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * VO for the Spring porlet MVC file upload
 * @author Shantan Talla
 *
 */
 
public class SpringFileVO {
 
    private CommonsMultipartFile fileData;
    private String message;
    private String serverImg;
 
    public String getServerImg() {
		return serverImg;
	}

	public void setServerImg(String serverImg) {
		this.serverImg = serverImg;
	}

	public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

}