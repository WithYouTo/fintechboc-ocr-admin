package com.cindy.ocrdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuditCheckDto {
    private Integer invoiceId;
    //   1表示已通过  2表示待修改  3表示已拒绝
    private Integer status;
    private String opinion;
}
