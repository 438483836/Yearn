package com.yearn.life.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Vincent on 2018-08-16.
 */
public class GetSignData {

    public GetSignData(){

    }

    /**
     * 获取签名
     * @param obj 初始参数集合
     * @return
     */
    public static String getSignData(Object obj){

        JSONObject jsonObject = new JSONObject();
        String dataJson = jsonObject.toJSONString(obj);

        return md5Encrypt(dataJson);
    }

    /**
     * md5加密
     *
     * @param content
     * @return
     */
    public static String md5Encrypt(String content) {
        try {
            return DigestUtils.md5Hex(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
