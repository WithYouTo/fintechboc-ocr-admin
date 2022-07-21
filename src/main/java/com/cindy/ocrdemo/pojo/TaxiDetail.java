package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 出租车发票报销明细内容
 * @TableName ocr_taxi_detail
 */
@TableName(value ="ocr_taxi_detail")
@Data
public class TaxiDetail implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long taxiId;

    /**
     * 关联主表中的主键
     */
    private Long invoiceId;


    /**
     * 发票代码
     */
    private String taxiDaima;

    /**
     * 发票号码
     */
    private String taxiHaoma;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 乘车日期
     */
    private String taxiDate;

    /**
     * 乘车时间区间
     */
    private String taxiTime;

    /**
     * 乘车金额
     */
    private String amount;

    /**
     * 单价
     */
    private String price;

    /**
     * 里程
     */
    private String mileage;

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