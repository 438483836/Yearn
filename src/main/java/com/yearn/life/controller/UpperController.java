package com.yearn.life.controller;

import com.yearn.life.pojo.DeskInformation;
import com.yearn.life.pojo.ZtoPaMessUpload;
import com.yearn.life.pojo.ZtoResponseTO;
import com.yearn.life.service.DeskInformationService;
import com.yearn.life.service.ZtoBackMessService;
import com.yearn.life.socket.client.ScanCodeSendStation;
import com.yearn.life.utils.DateConversion;
import com.yearn.life.ztoService.ZTHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 上件台
 * Created by Vincent on 2018-08-16.
 */
@RestController
public class UpperController {

    private static Logger logger = LogManager.getLogger(UpperController.class);

    @Autowired
    private DeskInformationService deskInformationService;

    @Autowired
    private ZtoBackMessService ztoBackMessService;

    @RequestMapping(path = "/searchDeskMess", method = RequestMethod.GET)
    public void searchDeskMess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String barCode = request.getParameter("barcode");

        response.setHeader("Content-type","text/html;charset=UTF-8");

        DeskInformation deskInformation = deskInformationService.getByBarcode(barCode);

        if (deskInformation != null){

            ScanCodeSendStation.scanCodeSendPLC(deskInformation);

            PrintWriter writer = response.getWriter();
            writer.write("条码："+deskInformation.getBarcode());
            writer.write("格口号: "+deskInformation.getSlogan());
            response.setStatus(200);

        }else {
            PrintWriter writer = response.getWriter();
            writer.write("该条码不存在！！！");
        }
    }

    @RequestMapping(path = "findData", method = RequestMethod.GET)
    public void findData(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String barCode = request.getParameter("barcode");
        response.setHeader("Content-type","text/html;charset=UTF-8");

        DeskInformation deskInformation = deskInformationService.getByBarcode(barCode);

        if (deskInformation != null){
            PrintWriter writer = response.getWriter();
            writer.write("条码："+deskInformation.getBarcode());
            writer.write("格口号: "+deskInformation.getSlogan());
            response.setStatus(200);
        }
        else {
            PrintWriter writer = response.getWriter();
            writer.write("该条码不存在！！！");
        }
    }

    @RequestMapping(path = "/upInformation",method = RequestMethod.GET)
    public Object upInformation(HttpServletRequest request, HttpServletResponse response)  throws IOException{
        String sortPortCode = request.getParameter("sortPortCode");
        String packageCode =request.getParameter("packageCode");
        String bindingTime = request.getParameter("bindingTime");
        String employeeCode = request.getParameter("employeeCode");
        String employeeName = request.getParameter("employeeName");
        String siteName = request.getParameter("siteName");
        String uploadTime =  request.getParameter("uploadTime");
        String lineCode = request.getParameter("lineCode");

        ZtoPaMessUpload ztoPaMessUpload = new ZtoPaMessUpload();
        ztoPaMessUpload.setSortPortCode(sortPortCode);
        ztoPaMessUpload.setPackageCode(packageCode);
        ztoPaMessUpload.setBindingTime(DateConversion.getCurrentDate(bindingTime,"yyyy-MM-dd HH:mm:ss"));
        ztoPaMessUpload.setEmployeeCode(employeeCode);
        ztoPaMessUpload.setEmployeeName(employeeName);
        ztoPaMessUpload.setSiteName(siteName);
        ztoPaMessUpload.setUploadTime(DateConversion.getCurrentDate(uploadTime,"yyyy-MM-dd HH:mm:ss"));
        ztoPaMessUpload.setLineCode(lineCode);

        ZtoResponseTO ztoResponseTO = ZTHelper.paMessUpload(ztoPaMessUpload);

        ZtoResponseTO data = ztoBackMessService.save(ztoResponseTO);

        if (data != null){
            logger.info("保存数据成功！！！");
            response.setStatus(200);
            return ztoResponseTO.toString();
        }

        return null;

    }

    @RequestMapping(path = "/sortService",method = RequestMethod.GET)
    public Object sortService(@RequestParam(value = "data",required = true) Object data, @RequestParam(value = "data_digest",required = true) String data_digest, @RequestParam(value = "msg_type",required = true) String msg_type, @RequestParam(value = "company_id",required = true) String company_id) throws IOException{

        if (data != null &&msg_type.equals("SORTING_BAG_BIND")){
            String status = "SUCCESS";
            String packageCode = "01";
            String sortMode = "test";
            String destSiteCode = "01";
            String destSiteName ="test";
            String remark = "";
            ZtoResponseTO ztoResponseTO = new ZtoResponseTO();
            ztoResponseTO.setStatus(status);
            ztoResponseTO.setPackageCode(packageCode);
            ztoResponseTO.setSortMode(sortMode);
            ztoResponseTO.setDestSiteCode(destSiteCode);
            ztoResponseTO.setDestSiteName(destSiteName);
            ztoResponseTO.setRemark(remark);
            //String jsonObj = JSONUtils.toString(ztoResponseTO);
            //logger.info("打印接收数据=====>" + jsonObj);
            return ztoResponseTO;

        }
        return null;
    }

}
