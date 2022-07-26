package com.cindy.ocrdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cindy.ocrdemo.dto.PageDto;
import com.cindy.ocrdemo.pojo.Invoice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.print.Book;

/**
* @author cindy
* @description 针对表【ocr_invoice(发票报销申请单)】的数据库操作Service
* @createDate 2022-07-08 21:26:24
*/
public interface InvoiceService extends IService<Invoice> {

    IPage<Invoice> listUserByPage(Page<Invoice> page, PageDto pageDto) ;

}
