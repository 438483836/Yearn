package com.yearn.life.zto;

import com.alibaba.fastjson.JSONObject;
import com.yearn.life.utils.JsonUtil;
import com.yearn.life.utils.MD5Util;
import com.yearn.life.zto.httpSortResultUtil.SortResultEntity;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestFormat {


    //开关流水线的请求
    public static String switchLine(String status){
        long switchTime=System.currentTimeMillis();
        String sortMode="sorting";
        //String pipeLine= ParamConfigCache.getPipeline();
        JSONObject data = new JSONObject();
        //data.put("pipeLine",pipeLine);
        data.put("switchTime",switchTime);
        data.put("status",status);
        data.put("sortMode",sortMode);
        String data_digest= MD5Util.encrypt(data.toString());
        String msg_type="WCS_PIPELINE_STATUS";
        String company_id="zto";
        return "data="+data+"&data_digest="+data_digest+"&msg_type="+msg_type+"&company_id="
                +company_id;
    }

    //校验时间的请求
    public static String timeVerify(){
        return "data=null&data_digest=&msg_type=ZTO_INSPECTION_TIME&company_id=zto";
    }

    //端口配置信息请求同步
    public static String portSync(){

        Map<String,String> map  = new HashMap<>();
        map.put("data","57730-001");
        map.put("data_digest","");
        map.put("msg_type","WCS_SORTING_SETTING");
        map.put("company_id","zto");

        String digestContent= JsonUtil.toJsonByObj(map);
        String digestedContent=MD5Util.encrypt(digestContent);

        String sendContent="data={\"".concat(map.get("data")).concat("\"}&data_digest=").concat(digestedContent)
                .concat("&msg_type=WCS_SORTING_SETTING&company_id=zto");

        return sendContent;
    }

    //一段码省市区请求
    public static String oneMarkProvince(){

        Map<String,String> map  = new HashMap<>();
        //map.put("data",ParamConfigCache.getPipeline());
        map.put("data_digest","");
        map.put("msg_type","GET_LINE_INFO");
        map.put("company_id","zto");

        String digestContent= JsonUtil.toJsonByObj(map);
        String digestedContent=MD5Util.encrypt(digestContent);

        String sendContent="data={\"".concat(map.get("data")).concat("\"}&data_digest=").concat(digestedContent)
                .concat("&msg_type=WCS_SORTING_SETTING&company_id=zto");

        return sendContent;
    }

    //面单规则请求
    public static String billRule(){
        String sendContent="data=null".concat("&data_digest=");
        sendContent=sendContent.concat("&msg_type=GET_BILL_RULE&company_id=zto");
        return sendContent;
    }

    //分拣信息请求
    /*public static String sortInfo(SortInfoEntity sortInfoEntity){

        String digestContent= JsonUtil.toJsonByObj(sortInfoEntity);
        String digestContentMD5=MD5Util.encrypt(digestContent);
        String sendContent="data=".concat(digestContent).concat("&data_digest=").concat(digestContentMD5);
        sendContent=sendContent.concat("&msg_type=WCS_SORTING_INFO&company_id=zto");
        return sendContent;
    }*/

    //分拣结果信息
    /*public static String sortResult(SortResultEntity sortResultEntity){

        String digestContent= JsonUtil.toJsonByObj(sortResultEntity);
        String digestContentMD5=MD5Util.encrypt(digestContent);
        String sendContent="data=".concat(digestContent).concat("&data_digest=").concat(digestContentMD5);
        sendContent=sendContent.concat("&msg_type=WCS_SORTING_RESULT&company_id=zto");
        return sendContent;
    }*/

    //请求快速补码信息
    public static String complementRequest(SortResultEntity sortResultEntity){

        String digestContent= JsonUtil.toJsonByObj(sortResultEntity);
        String digestContentMD5=MD5Util.encrypt(digestContent);
        String sendContent="data=".concat(digestContent).concat("&data_digest=").concat(digestContentMD5);
        sendContent=sendContent.concat("&msg_type=WCS_FOR_PACKAGE&company_id=zto");
        return sendContent;
    }

    //请求包牌打印信息
    /*public static String packagePrint(PackagePrintEntity packagePrintEntity){

        String digestContent= JsonUtil.toJsonByObj(packagePrintEntity);
        String digestContentMD5=MD5Util.encrypt(digestContent);
        String sendContent="data=".concat(digestContent).concat("&data_digest=").concat(digestContentMD5);
        sendContent=sendContent.concat("&msg_type=WCS_BAG_PRINTING&company_id=zto");
        return sendContent;
    }*/

    //集包绑定信息
    /*public static String packageBind(PackageBindEntity packageBindEntity){

        String digestContent= JsonUtil.toJsonByObj(packageBindEntity);
        String digestContentMD5=MD5Util.encrypt(digestContent);
        String sendContent="data=".concat(digestContent).concat("&data_digest=").concat(digestContentMD5);
        sendContent=sendContent.concat("&msg_type=WCS_BAG_BIND&company_id=zto");
        return sendContent;
    }*/

    //分拣口和集包绑定数据查询
    public static String packageCodeInfo(){

        Map<String,String> map  = new HashMap<>();
        //map.put("data",ParamConfigCache.getPipeline());
        map.put("data_digest","");
        map.put("msg_type","GET_BAGCODE_INFO");
        map.put("company_id","zto");

        String digestContent= JsonUtil.toJsonByObj(map);
        String digestedContent=MD5Util.encrypt(digestContent);

        String sendContent="data={\"".concat(map.get("data")).concat("\"}&data_digest=").concat(digestedContent)
                .concat("&msg_type=WCS_SORTING_SETTING&company_id=zto");

        return sendContent;
    }


}
