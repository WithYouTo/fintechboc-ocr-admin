package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 用户发票申请单变更记录表
 * @TableName ocr_log
 */
@TableName(value ="ocr_log")
@Data
public class Log implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 发票主键
     */
    private Long invoiceId;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 操作行为
     */
    private String operateContent;

    @TableField(fill = FieldFill.INSERT)
    private String createEmp;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}