package com.cindy.ocrdemo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.dto.PageDto;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.Arrays;

/**
* @author cindy
* @description 针对表【ocr_invoice(发票报销申请单)】的数据库操作Service实现
* @createDate 2022-07-08 21:26:24
*/
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice>
    implements InvoiceService{

    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public IPage<Invoice> listUserByPage(Page<Invoice> page, LambdaQueryWrapper<Invoice> queryWrapper) {
        // 创建时间降序， 状态升序
//        queryWrapper.orderByDesc(Invoice::getCreateTime);
//        queryWrapper.orderByAsc(Invoice::getStatus);
        return invoiceMapper.selectPage(page, queryWrapper);
    }
}




