package com.yearn.life.utils;

import com.yearn.life.pojo.DeskInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Vincent on 2017/4/16.
 */
public class CheckUtil {

    private static Logger logger = LogManager.getLogger(CheckUtil.class);

    /**
     *
     * @param code 条码
     * @param num 上位机编号
     * @param slogans 隔口号
     * @return
     */
    public static String getCodeAndNumAndSlogans(String code, String num, String slogans){
        String byteCode = TypeConversion.string2HexString(code);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A5A50103");
        String swNum = TypeConversion.string2HexString(num);
        //String slogansCode = TypeConversion.string2HexString(slogans);
        stringBuffer.append(swNum);
        if (byteCode.length() < 32){
            int j = 0;
            int i = 32 - byteCode.length();
            String val = String.valueOf(i);
            String str = String.format("%0"+ val+"d",j);
            String newByteCode = byteCode + str;
            stringBuffer.append(newByteCode);
          //  System.out.println(str);
        }else{
            String str = "A5A50103"+swNum+byteCode+slogans+"00000000005A5A";
            return  str;
        }
        stringBuffer.append(slogans);
        stringBuffer.append("00000000005A5A");
        //String str ="A5A50103"+swNum+newByteCode+slogansCode+"00000000005A5A";
       // System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

   /* *//**
     * 自动补码
     * @param complement
     * @return
     *//*
    public static String getBarcode(Complement complement){

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("A5A5");
        stringBuffer.append("02");//数据包
        stringBuffer.append(TypeConversion.getHexString2(Integer.parseInt(complement.getPacketSize())));//数据包总长度
        stringBuffer.append(complement.getPlcCode());//plc站口
        stringBuffer.append(complement.getBarCode());//条码
        stringBuffer.append(complement.getPackageInt());//包裹重量整数位
        stringBuffer.append(complement.getPackageDec());//包裹重量小数位
        stringBuffer.append("00FE");//格口号码
        if (StringUtils.isEmpty(complement.getSlogan())){
            stringBuffer.append("5555");
        }else {
            stringBuffer.append("AAAA");
        }

        stringBuffer.append("0000");//校验
        stringBuffer.append("5A5A");

        logger.info("自动补码发送十六进制码: " ,stringBuffer.toString());

        return stringBuffer.toString();
    }*/

    /**
     * 自动扫描上件台
     * @param deskInformation
     * @return
     */
    public static String getScanCode(DeskInformation deskInformation){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A5A5");
        stringBuffer.append(TypeConversion.getHexString2(Integer.parseInt(deskInformation.getData_packet())));//数据包
        stringBuffer.append("1E");//数据包总长度
        stringBuffer.append(TypeConversion.getHexString2(Integer.parseInt(deskInformation.getPc_num())));//上位机编号
        stringBuffer.append(TypeConversion.getHexString2(deskInformation.getBarcode().length()));//条码长度
        String modifyBarcode = StringUtil.addStr(deskInformation.getBarcode());//条码
        if (modifyBarcode.length() < 30){
            int i =0;
            int j = 30 - modifyBarcode.length();
            String val = String.valueOf(j);
            String str = String.format("%0"+ val+"d",i);
            String newBarcode = modifyBarcode + str;
            stringBuffer.append(newBarcode);

        }else{

            stringBuffer.append(modifyBarcode);
            stringBuffer.append(TypeConversion.getHexString4(Integer.parseInt(deskInformation.getSlogan())));
            stringBuffer.append(TypeConversion.getHexStringI(Integer.parseInt(deskInformation.getImportant_mess()),6));
            stringBuffer.append(VerificationUtil.upperPieceV(deskInformation));
            stringBuffer.append("5A5A");
            return stringBuffer.toString();

        }
        stringBuffer.append(TypeConversion.getHexString4(Integer.parseInt(deskInformation.getSlogan())));
        stringBuffer.append(TypeConversion.getHexStringI(Integer.parseInt(deskInformation.getImportant_mess()),6));
        stringBuffer.append(VerificationUtil.upperPieceV(deskInformation));
        stringBuffer.append("5A5A");

        return stringBuffer.toString();
    }

    /**
     * 自动集包pc发送PLC数据
     * @param responseData
     * @param
     * @return
     */
    public static String autoPackSendPLC(String responseData){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A5A5");
        stringBuffer.append("00");
        stringBuffer.append(responseData);
        stringBuffer.append("5A5A");

        return stringBuffer.toString();
    }

}
