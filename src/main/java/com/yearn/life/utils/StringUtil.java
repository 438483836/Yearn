package com.yearn.life.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * Created by Vincent on 2018-08-16.
 */
public class StringUtil {

    private static Logger logger = LogManager.getLogger(StringUtil.class);

    /**
     * 在某字符串添加字符
     * @param str 原字符串
     * @return 字符串
     */
    public static String addStr(String str) {

        if (StringUtils.isEmpty(str)){
            logger.error("字符不能不空！！",str);
            return null;
        }else {
            char[] strArray = str.toCharArray();
            String newStr = "";

            for(int i = 0; i < strArray.length; i++){
                if (i%1 == 0 && i > 0){
                    newStr += "0";
                }
                newStr += strArray[i];
            }
            return "0" + newStr;
        }


    }
}
