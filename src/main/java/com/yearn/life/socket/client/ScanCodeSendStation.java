package com.yearn.life.socket.client;

import com.yearn.life.pojo.DeskInformation;
import com.yearn.life.utils.ByteUtil;
import com.yearn.life.utils.CheckUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * 扫描上件台发送给PLC
 * Created by Vincent on 2018-08-16.
 */
public class ScanCodeSendStation extends Thread {

    private static Logger logger = LogManager.getLogger(ScanCodeSendStation.class);

    public ScanCodeSendStation(){

    }

    //服務器地址
    public static final String IP_ADR = "10.96.10.180";

    //服務端口
    public static final int PORT = 2000;

    static DataOutputStream outputStream = null;



    public static void scanCodeSendPLC(final DeskInformation deskInformation) {
        final Socket socket;
        try {

            socket = new Socket(IP_ADR, PORT);

            logger.info("扫描服务器连接成功:{}" ,socket.isConnected());

            outputStream = new DataOutputStream(socket.getOutputStream());


            while (true){

                try {
                    outputStream.write(ByteUtil.hexStr2ByteArray(CheckUtil.getScanCode(deskInformation)));
                    logger.info("发送命令:{}" , Arrays.toString(ByteUtil.hexStr2ByteArray(CheckUtil.getScanCode(deskInformation))));
                    break;

                } catch (IOException e) {
                    logger.error("发送命令异常: " ,e.getMessage());
                }finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (IOException e) {
            logger.error("扫描上件台服务器异常: " ,e.getMessage());
            e.printStackTrace();
        }

    }

}
