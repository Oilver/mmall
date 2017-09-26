package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by geely
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
    public String upload(MultipartFile file,String path){
        
    	String filename = file.getOriginalFilename();
    	String extendFilename = filename.substring(filename.lastIndexOf(".")+1);
    	String targetFilename = UUID.randomUUID().toString()+"."+extendFilename;
    	File Dir = new File(path);
    	if(!Dir.exists()){
    		Dir.setWritable(true);
    		Dir.mkdirs();
    	}
    	File targetFile = new File(path, targetFilename);
    	try {
			file.transferTo(targetFile);
			
			FTPUtil.uploadFile(Lists.newArrayList(targetFile));
			targetFile.delete();
		} catch (IllegalStateException | IOException e) {
			logger.error("文件异常");
			e.printStackTrace();
		}
    	
    	return targetFile.getName();
    }

}
