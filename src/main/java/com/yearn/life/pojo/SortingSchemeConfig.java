package com.yearn.life.pojo;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Vincent on 2018-11-05
 */
@Entity
@Table(name = "tb_sorting_scheme_config")
public class SortingSchemeConfig implements Serializable {

    private static final long serialVersionUID = -53198479483275188L;

    private Integer id;

    /**
     * 数据推送类型
     */
    private String schemeId;

    /**
     * 分拣线编码
     */
    private String pipeline;

    /**
     * 分拣口编码
     */
    private String sortPortCode;
    /**
     * 分拣口名
     */
    private String sortPortCodeName;
    /**
     * 下一站id
     */
    private String siteCode;
    /**
     * 下一站名称
     */
    private String siteName;
    /**
     * 数据模式
     */
    private String sortMode;
    /**
     * 快速补码
     */
    private String sortCode;
    /**
     * 大头笔
     */
    private String standardSortCode;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getSortPortCode() {
        return sortPortCode;
    }

    public void setSortPortCode(String sortPortCode) {
        this.sortPortCode = sortPortCode;
    }

    public String getSortPortCodeName() {
        return sortPortCodeName;
    }

    public void setSortPortCodeName(String sortPortCodeName) {
        this.sortPortCodeName = sortPortCodeName;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getStandardSortCode() {
        return standardSortCode;
    }

    public void setStandardSortCode(String standardSortCode) {
        this.standardSortCode = standardSortCode;
    }

    @Override
    public String toString() {
        return "SortingSchemeConfig{" +
                "id=" + id +
                ", schemeId='" + schemeId + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", sortPortCode='" + sortPortCode + '\'' +
                ", sortPortCodeName='" + sortPortCodeName + '\'' +
                ", siteCode='" + siteCode + '\'' +
                ", siteName='" + siteName + '\'' +
                ", sortMode='" + sortMode + '\'' +
                ", sortCode='" + sortCode + '\'' +
                ", standardSortCode='" + standardSortCode + '\'' +
                '}';
    }
}
