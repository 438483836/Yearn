package com.yearn.life.utils;

/**
 * Created by Vincent on 2018-07-03.
 */
public class OApiException extends Exception{

    public OApiException(String errCode, String errMsg) {super("error code: " + errCode + ", error message: " + errMsg);}

}

