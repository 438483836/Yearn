package com.yearn.life.ztoService;

import com.alibaba.fastjson.JSONObject;
import com.yearn.life.pojo.ZtoPaMessUpload;
import com.yearn.life.pojo.ZtoResponseTO;
import com.yearn.life.utils.GetSignData;
import com.yearn.life.utils.HttpHelper;
import com.yearn.life.utils.JSONUtils;
import com.yearn.life.utils.OApiException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**中通接口服务
 * Created by Vincent on 2018-07-03.
 */
public class ZTHelper {

    private static Logger logger = LogManager.getLogger(ZTHelper.class);

    public static final String url = "http://localhost:8080/sortService?";

    public static final String msg_type = "SORTING_BAG_BIND";

    public static final String company_id = "zto";


    /**
     *集包关联信息上传(把枪调用)
     * @param data
     * @return
     */
    public static ZtoResponseTO paMessUpload(ZtoPaMessUpload data) throws UnsupportedEncodingException{

        String requestData = JSONUtils.toString(data);
        String data_digest = GetSignData.getSignData(data);

        String plain = url + "data=" + URLEncoder.encode(requestData,"UTF-8")+ "&data_digest=" + data_digest + "&msg_type=" + msg_type + "&company_id=" + company_id;

        try {

            JSONObject response = HttpHelper.httpGet(plain);
            logger.info("paMessUpload response:{}", response);

            if (response.containsKey("status")){
                String status = response.getString("status");
                String packageCode = response.getString("packageCode");
                String sortMode = response.getString("sortMode");
                String destSiteCode = response.getString("destSiteCode");
                String destSiteName = response.getString("destSiteName");
                String remark = response.getString("remark");
                if (StringUtils.isEmpty(packageCode)){
                    logger.info("集包号不能为空！！！", packageCode);
                }
                if (StringUtils.isEmpty(sortMode)){
                    logger.info("分拣模式不能为空！！！", sortMode);
                }

                ZtoResponseTO ztoResponseTO = new ZtoResponseTO();
                ztoResponseTO.setStatus(status);
                ztoResponseTO.setPackageCode(packageCode);
                ztoResponseTO.setSortMode(sortMode);
                ztoResponseTO.setDestSiteCode(destSiteCode);
                ztoResponseTO.setDestSiteName(destSiteName);
                ztoResponseTO.setRemark(remark);
                return  ztoResponseTO;
            }

        } catch (OApiException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return null;
    }

}
