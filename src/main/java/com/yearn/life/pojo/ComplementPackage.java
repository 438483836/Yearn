package com.yearn.life.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Vincent on 2018-11-05
 */
@Entity
@Table(name = "complementPackage")
public class ComplementPackage implements Serializable {

    private static final long serialVersionUID = 196679666144790501L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 单号
     */
    private String BillCode;

    /**
     * 目的地
     */
    private String Destination;

    /**
     * 操作台号
     */
    private String ClientId;

    /**
     * 图片路径
     */
    private String PicPath;

    /**
     * 创建时间
     */
    private Date CreateTime;

    /**
     * 是否发送
     */
    private Integer IsSendToClient;

    /**
     * 补码台号
     */
    private String ComplementClient;

    /**
     * 补码用户
     */
    private String ComplementUser;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Integer getIsSendToClient() {
        return IsSendToClient;
    }

    public void setIsSendToClient(Integer isSendToClient) {
        IsSendToClient = isSendToClient;
    }

    public String getComplementClient() {
        return ComplementClient;
    }

    public void setComplementClient(String complementClient) {
        ComplementClient = complementClient;
    }

    public String getComplementUser() {
        return ComplementUser;
    }

    public void setComplementUser(String complementUser) {
        ComplementUser = complementUser;
    }
}
