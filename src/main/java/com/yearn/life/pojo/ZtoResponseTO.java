package com.yearn.life.pojo;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Vincent on 2018-08-16.
 */
@Entity
@Table(name = "zto_responseTO")
public class ZtoResponseTO implements Serializable{

    private static final long serialVersionUID = 8212828624114179641L;

    private Integer id;

    /**
     * 状态
     */
    private String status;
    /**
     * 状态信息,成功或其它
     * 成功：SUCCESS
     */
    @Column(name = "status_code")
    private String statusCode;
    /**
     * 集包号
     */
    @Column(name = "package_code")
    private String packageCode;
    /**
     * 分拣模式
     */
    @Column(name = "sort_mode")
    private String sortMode;
    /**
     * 目的站点编码
     */
    @Column(name = "dest_site_code")
    private String destSiteCode;
    /**
     * 目的站点名
     */
    @Column(name = "dest_site_name")
    private String destSiteName;
    /**
     * 备注信息
     */
    private String remark;

    public ZtoResponseTO() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public String getDestSiteCode() {
        return destSiteCode;
    }

    public void setDestSiteCode(String destSiteCode) {
        this.destSiteCode = destSiteCode;
    }

    public String getDestSiteName() {
        return destSiteName;
    }

    public void setDestSiteName(String destSiteName) {
        this.destSiteName = destSiteName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ZtoResponseTO{" +
                "status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", sortMode='" + sortMode + '\'' +
                ", destSiteCode='" + destSiteCode + '\'' +
                ", destSiteName='" + destSiteName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
