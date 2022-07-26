package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
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

    /**
     * 审核人
     */
    private Long auditId;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 状态，0表示待审核，1表示审核通过，2表示审核待修改
     */
    private Integer status;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer deleteFlag;

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