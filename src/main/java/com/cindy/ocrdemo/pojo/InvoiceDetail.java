package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 增值税发票报销明细内容
 * @TableName ocr_invoice_detail
 */
@TableName(value ="ocr_invoice_detail")
@Data
public class InvoiceDetail implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long detailId;

    /**
     * 关联主表中的主键
     */
    private Long invoiceId;

    /**
     * 购货方名称
     */
    private String invoicePayerName;

    /**
     * 购货方纳税人识别号
     */
    private String invoiceRatePayerId;

    /**
     * 购货方地址和电话
     */
    private String invoicePayerAddrTell;

    /**
     * 购货方开户行及账号
     */
    private String invoicePayerBankAccount;

    /**
     * 密码区
     */
    private String cryptographicArea;

    /**
     * 发票代码
     */
    private String invoiceDaima;

    /**
     * 发票号码
     */
    private String invoiceHaoma;

    /**
     * 开票日期
     */
    private String invoiceIssueDate;

    /**
     * 货物或服务名称
     */
    private String invoiceGoodsList;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 金额
     */
    private String invoicePriceList;

    /**
     * 税率
     */
    private String invoiceTaxRateList;

    /**
     * 税额
     */
    private String invoiceTaxList;

    /**
     * 合计金额
     */
    private String invoiceTotal;

    /**
     * 合计税率
     */
    private String invoiceTaxTotal;

    /**
     * 价税合计大写总额
     */
    private String invoiceTotalCoverTax;

    /**
     * 价税合计小写总额
     */
    private String invoiceTotalCoverTaxDigits;

    /**
     * 销售方名称
     */
    private String invoiceSellerName;

    /**
     * 销售方纳税人识别号
     */
    private String invoiceSellerId;

    /**
     * 销售方地址和电话
     */
    private String invoiceSellerAddrTell;

    /**
     * 销售方开户行及账号
     */
    private String invoiceSellerBankAccount;

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