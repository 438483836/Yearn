package com.yearn.life.pojo;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 扫码信息发送给PLC
 * Created by Vincent on 2018-06-25.
 */
@Entity
@Table(name = "t_desk_information")
public class DeskInformation implements Serializable {

    private static final long serialVersionUID = 1625550484597793311L;

    private Integer id;

    /**
     * 数据包
     */
    private String data_packet;

    /**

     * 数据包总长度
     */
    private String packet_size;

    /**
     * 上位机编号
     */
    private String pc_num;

    /**
     * 条码长度
     */
    private String barcode_length;

    /**
     *条码
     */
    private String barcode;

    /**
     * 格口号
     */
    private String slogan;

    /**
     * 重要信息
     */
    private String important_mess;

    /**
     * 校验
     */
    private String check_data;

    /**
     * 三段码
     */
    private String three_code;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData_packet() {
        return data_packet;
    }

    public void setData_packet(String data_packet) {
        this.data_packet = data_packet;
    }

    public String getPacket_size() {
        return packet_size;
    }

    public void setPacket_size(String packet_size) {
        this.packet_size = packet_size;
    }

    public String getPc_num() {
        return pc_num;
    }

    public void setPc_num(String pc_num) {
        this.pc_num = pc_num;
    }

    public String getBarcode_length() {
        return barcode_length;
    }

    public void setBarcode_length(String barcode_length) {
        this.barcode_length = barcode_length;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getImportant_mess() {
        return important_mess;
    }

    public void setImportant_mess(String important_mess) {
        this.important_mess = important_mess;
    }

    public String getCheck_data() {
        return check_data;
    }

    public void setCheck_data(String check_data) {
        this.check_data = check_data;
    }

    public String getThree_code() {
        return three_code;
    }

    public void setThree_code(String three_code) {
        this.three_code = three_code;
    }

    public DeskInformation() {

    }

    @Override
    public String toString() {
        return "DeskInformation{" +
                "id=" + id +
                ", data_packet='" + data_packet + '\'' +
                ", packet_size='" + packet_size + '\'' +
                ", pc_num='" + pc_num + '\'' +
                ", barcode_length='" + barcode_length + '\'' +
                ", barcode='" + barcode + '\'' +
                ", slogan='" + slogan + '\'' +
                ", important_mess='" + important_mess + '\'' +
                ", check_data='" + check_data + '\'' +
                ", three_code='" + three_code + '\'' +
                '}';
    }
}
