package com.yearn.life.ztoService;

import com.yearn.life.pojo.ZTOResponse;
import com.yearn.life.utils.GetSignData;
import com.yearn.life.utils.JSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URLEncoder;

public class ZTService {

    private static Logger logger = LogManager.getLogger(ZTHelper.class);

    public static final String url = "http://localhost:8080/sortService?";

    public static final String msg_type = "SORTING_BAG_BIND";

    public static final String company_id = "zto";


    private ZTOResponse getZTOResponse(Object data){
        try {
            String requestData = JSONUtils.toString(data);
            String data_digest = GetSignData.getSignData(data);

            String plain = url + "data=" + URLEncoder.encode(requestData,"UTF-8")+ "&data_digest=" + data_digest + "&msg_type=" + msg_type + "&company_id=" + company_id;
            return JSONUtils.parse(plain, ZTOResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }





}
