package com.yearn.life.utils;

import com.yearn.life.pojo.DeskInformation;

/**
 * Created by Vincent on 2018-08-16.
 */
public class VerificationUtil {

    /**
     * 自动补码校验
     * @param complement
     * @return
     */
    /*public static String autoComplementV(Complement complement){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("02");//数据包
        stringBuffer.append("21");//数据包总长度
        stringBuffer.append(complement.getPlcCode());//plc站口
        stringBuffer.append(complement.getBarCode());//条码
        stringBuffer.append(complement.getPackageInt());//包裹重量整数位
        stringBuffer.append(complement.getPackageDec());//包裹重量小数位
        stringBuffer.append("FE");//格口号码
        if (StringUtils.isEmpty(complement.getSlogan())){
            stringBuffer.append("5555");
        }else {
            stringBuffer.append("AAAA");
        }

        String lString = stringBuffer.toString();

        String str = TypeConversion.sumHexStringBy2(lString,2);

        int hexInt = TypeConversion.hexToDecimal(str);

        String hexString = TypeConversion.getHexString4(hexInt);


        return hexString;
    }*/

    /**
     * 上件台校验码
     * @param deskInformation
     * @return 16进制字符串
     */
    public static String upperPieceV(DeskInformation deskInformation){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(TypeConversion.getHexStringI(Integer.parseInt(deskInformation.getData_packet()),2));//数据包
        stringBuffer.append("1E");//数据总长度
        stringBuffer.append(TypeConversion.getHexStringI(Integer.parseInt(deskInformation.getPc_num()),2));//上位机编号
        stringBuffer.append(TypeConversion.getHexString2(deskInformation.getBarcode().length()));//条码长度
        stringBuffer.append(StringUtil.addStr(deskInformation.getBarcode()));//条码

        stringBuffer.append(TypeConversion.getHexString4(Integer.parseInt(deskInformation.getSlogan())&0xFF));//隔口号
        stringBuffer.append(TypeConversion.getHexStringI(Integer.parseInt(deskInformation.getImportant_mess()),6));//重要信息

        String lString = stringBuffer.toString();

        String hexStr = TypeConversion.sumHexStringBy2(lString,2);

        if (hexStr.length() < 4){
            int i = 0;
            int j = 4 - hexStr.length();
            String val = String.valueOf(j);
            String str = String.format("%0" + val + "d",i);
            String newHexStr = str + hexStr;
            return newHexStr;
        }else {
            return hexStr;
        }
    }

    /**
     * 自动集包pc发送PLC数据校验码
     * @param bytes
     * @param pcNum
     * @return 16进制字符串
     */
    public static String autoPackToPlcV(byte[] bytes, String pcNum) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A5A5");
        stringBuffer.append("2");
        stringBuffer.append("13");
        stringBuffer.append(pcNum);
        stringBuffer.append(TypeConversion.bytes2HexString(bytes));
        stringBuffer.append("00000000");
        String lString = stringBuffer.toString();
        String hexStr = TypeConversion.sumHexStringBy2(lString, 2);

        if (hexStr.length() < 4){
            int i = 0;
            int j = 4 - hexStr.length();
            String val = String.valueOf(j);
            String str = String.format("%0" + val + "d",i);
            String newHexStr = str + hexStr;
            return newHexStr;
        }else {
            return hexStr;
        }
    }
}
