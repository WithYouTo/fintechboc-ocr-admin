package com.cindy.ocrdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cindy.ocrdemo.anno.OperationLog;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.AuditCheckDto;
import com.cindy.ocrdemo.dto.PageDto;
import com.cindy.ocrdemo.dto.UserContext;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 主管审核发票信息
 */
@RestController
@RequestMapping("/audit")
public class MyAuditController {

    @Resource
    private InvoiceService invoiceService;


    /**
     * 查询审核列表
     * @return
     */
    @PostMapping("/list")
    public CommonResult listMyAudit(@RequestBody PageDto pageDto){
        Page<Invoice> page = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        LambdaQueryWrapper<Invoice> queryWrapper = new LambdaQueryWrapper<Invoice>();
        // 只显示待审核状态列表
        queryWrapper.eq(Invoice::getStatus, 0).orderByDesc(Invoice::getCreateTime);
        IPage<Invoice> list = invoiceService.listUserByPage(page, queryWrapper);
        return CommonResult.success(list);
    }

    /**
     * 查询已经审核列表
     * @return
     */
    @PostMapping("/history")
    public CommonResult listMyHistoryAudit(@RequestBody PageDto pageDto){
        Page<Invoice> page = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        LambdaQueryWrapper<Invoice> queryWrapper = new LambdaQueryWrapper<Invoice>();
        queryWrapper.eq(Invoice::getAuditId, UserContext.getUserId());
        queryWrapper.eq(Invoice::getStatus, 1).or().eq(Invoice::getStatus, 3);
        queryWrapper.orderByDesc(Invoice::getAuditTime);
        IPage<Invoice> list = invoiceService.listUserByPage(page, queryWrapper);
        return CommonResult.success(list);
    }

    /**
     *  审核
     * @param auditCheckDto
     * @return
     */
    @PostMapping("/check")
    @OperationLog()
    public CommonResult updateById(@RequestBody AuditCheckDto auditCheckDto) {
        Invoice invoice = invoiceService.getById(auditCheckDto.getInvoiceId());
        if(invoice == null){
            return CommonResult.failed("根据id没有查询到数据");
        }
        if(!(invoice.getStatus() == 0)){
            return CommonResult.failed("该申请记录状态不是待审核");
        }
        invoice.setAuditId(UserContext.getUserId());
        invoice.setAuditTime(LocalDateTime.now());
        invoice.setStatus(auditCheckDto.getStatus());
        invoice.setAuditNote(auditCheckDto.getOpinion());
        invoiceService.updateById(invoice);
        return CommonResult.success("审核成功");
    }


}
