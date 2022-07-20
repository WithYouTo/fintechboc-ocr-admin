package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;

/**
* @author cindy
* @description 针对表【ocr_invoice(发票报销申请单)】的数据库操作Service实现
* @createDate 2022-07-08 21:26:24
*/
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice>
    implements InvoiceService{

}




