package com.yearn.life.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 集包关联信息上传
 * Created by Vincent on 2018-08-16.
 */
public class ZtoPaMessUpload implements Serializable{

    private static final long serialVersionUID = 9092554556145569737L;

    /**
     * 分拣端口编码
     */
    private String sortPortCode;
    /**
     * 集包编码
     */
    private String packageCode;
    /**
     * 端口集包绑定时间
     */
    private Date bindingTime;
    /**
     * 操作员工编号
     */
    private String employeeCode;
    /**
     * 操作员工姓名
     */
    private String employeeName;
    /**
     * 站点名
     */
    private String siteName;
    /**
     * 数据上传时间
     */
    private Date uploadTime;
    /**
     * 分拣线
     */
    private String lineCode;
    /**
     * pda编码
     */
    private String pdaCode;

    public ZtoPaMessUpload() {
    }

    public String getSortPortCode() {
        return sortPortCode;
    }

    public void setSortPortCode(String sortPortCode) {
        this.sortPortCode = sortPortCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public Date getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getPdaCode() {
        return pdaCode;
    }

    public void setPdaCode(String pdaCode) {
        this.pdaCode = pdaCode;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

}
