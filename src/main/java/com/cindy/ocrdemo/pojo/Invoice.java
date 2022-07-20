package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
     * 上传图片路径
     */
    private String imgPath;

    /**
     * 图片名称
     */
    private Integer imgName;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 发票总额
     */
    private BigDecimal invoiceAmount;

    /**
     * 备注
     */
    private String note;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}