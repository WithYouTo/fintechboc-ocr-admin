package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 发票报销申请单
 * @TableName ocr_invoice
 */
@TableName(value ="ocr_invoice")
@Data
public class Invoice implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 申请日期
     */
    private Date applyDate;

    /**
     * 上传网络图片路径
     */
    private String netImgPath;

    /**
     * 上传本地图片路径
     */
    private String localImgPath;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 发票总额
     */
    private String invoiceAmount;

    /**
     * 备注
     */
    private String note;

    @TableField(fill = FieldFill.INSERT)
    private String createEmp;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private String updateEmp;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}