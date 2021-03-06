package com.yearn.life.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by Vincent on 2018-10-17
 */
@Component
public class Progress {

    /**
     * 已读取文件的比特数
     */
    private long bytesRead;
    /**
     * 文件总比特数
     */
    private long contentLength;
    /**
     * 正读的第几个文件
     */
    private long items;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }
}
