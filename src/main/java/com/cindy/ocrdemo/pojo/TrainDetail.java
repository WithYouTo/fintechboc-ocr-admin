package com.cindy.ocrdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 火车票发票报销明细内容
 * @TableName ocr_train_detail
 */
@TableName(value ="ocr_train_detail")
@Data
public class TrainDetail implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long trainId;

    /**
     * 票号
     */
    private String ticketNo;

    /**
     * 检票口
     */
    private String ticketCheck;

    /**
     * 出发站
     */
    private String stationFrom;

    /**
     * 到达站
     */
    private String stationTo;

    /**
     * 发车日期
     */
    private String goDate;

    /**
     * 开车时间
     */
    private String gotime;

    /**
     * 车次
     */
    private String train;

    /**
     * 座位号
     */
    private String seat;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String identity;

    /**
     * 票价
     */
    private String amount;

    /**
     * 席别
     */
    private String seatType;

    /**
     * 备注
     */
    private String note;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}