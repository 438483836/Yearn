package com.yearn.life.zto.httpSortResultUtil;

/**
 * Created by Vincent on 2018-10-16
 */
public class SortResultEntity {

    private String billCode; //单号
    private String trayCode; //小车编号
    private String pipeline; //分拣线编码
    private Long sortTime; //分拣时间
    private Integer turnNumber; //循环圈数
    private String sortPortCode; //分拣口编号
    private String sortSource; //分拣来源
    private String packageCode; //集包号
    private String scanUser; //扫描人编码
    private String endSortcamCode; //最后一次请求的相机编号
    private String endcamareCode; //最后一次经过的相机编号
    private String supplyCode;  //供包台编号
    private String sortCode; //补码编码/异常码,若非正常分拣，则必填

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getTrayCode() {
        return trayCode;
    }

    public void setTrayCode(String trayCode) {
        this.trayCode = trayCode;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public Long getSortTime() {
        return sortTime;
    }

    public void setSortTime(Long sortTime) {
        this.sortTime = sortTime;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getSortPortCode() {
        return sortPortCode;
    }

    public void setSortPortCode(String sortPortCode) {
        this.sortPortCode = sortPortCode;
    }

    public String getSortSource() {
        return sortSource;
    }

    public void setSortSource(String sortSource) {
        this.sortSource = sortSource;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getScanUser() {
        return scanUser;
    }

    public void setScanUser(String scanUser) {
        this.scanUser = scanUser;
    }

    public String getEndSortcamCode() {
        return endSortcamCode;
    }

    public void setEndSortcamCode(String endSortcamCode) {
        this.endSortcamCode = endSortcamCode;
    }

    public String getEndcamareCode() {
        return endcamareCode;
    }

    public void setEndcamareCode(String endcamareCode) {
        this.endcamareCode = endcamareCode;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public String toString() {
        return "SortResultEntity{" +
                "billCode='" + billCode + '\'' +
                ", trayCode='" + trayCode + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", sortTime='" + sortTime + '\'' +
                ", turnNumber=" + turnNumber +
                ", sortPortCode='" + sortPortCode + '\'' +
                ", sortSource='" + sortSource + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", scanUser='" + scanUser + '\'' +
                ", endSortcamCode='" + endSortcamCode + '\'' +
                ", endcamareCode='" + endcamareCode + '\'' +
                ", supplyCode='" + supplyCode + '\'' +
                ", sortCode='" + sortCode + '\'' +
                '}';
    }

}
