package com.yearn.life.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {

    /**
     * 读取excel中的数据,生成list
     * @param file
     * @return
     */
    String readExcelFile(MultipartFile file);
}
