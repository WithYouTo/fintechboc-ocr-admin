package com.cindy.ocrdemo.service;

import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author cindy
* @description 针对表【ocr_invoice_detail(增值税发票报销明细内容)】的数据库操作Service
* @createDate 2022-07-08 23:35:47
*/
public interface InvoiceDetailService extends IService<InvoiceDetail> {

    InvoiceDetail saveInvoiceByApi(FileUrlDto fileUrlDto) throws Exception;
}
