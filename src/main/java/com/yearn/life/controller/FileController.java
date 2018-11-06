package com.yearn.life.controller;

import com.yearn.life.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vincent on 2018-10-29
 */
@Controller
public class FileController
{
    @RequestMapping(path = "upload")
    public Object upload(){
        return "/upload";
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty())
            return "文件为空";
        // 获取文件名
        String fileName = file.getOriginalFilename();// 文件上传后的路径
        String filePath = null;
        try{
            filePath = new File("F:\\").getCanonicalPath() + "/tmp/uploadFile/";
        } catch (IOException e){
            e.printStackTrace();
        }
        //存储路径
        String tagFilePath = filePath + DateUtil.getTime() + fileName;
        File dest = new File(tagFilePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return fileName + "上传失败";
    }
}
