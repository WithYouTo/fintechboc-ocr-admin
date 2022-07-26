package com.cindy.ocrdemo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.*;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import com.cindy.ocrdemo.pojo.TaxiDetail;
import com.cindy.ocrdemo.pojo.TrainDetail;
import com.cindy.ocrdemo.service.InvoiceDetailService;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.service.TaxiDetailService;
import com.cindy.ocrdemo.service.TrainDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 主管审核发票信息
 */
@RestController
@RequestMapping("/history")
public class InvoiceHistoryController {

    @Resource
    private InvoiceService invoiceService;

    @Resource
    private InvoiceDetailService invoiceDetailService;

    @Resource
    private TrainDetailService trainDetailService;

    @Resource
    private TaxiDetailService taxiDetailService;

    /**
     * 查询用户提交发票列表
     * @return
     */
    @PostMapping("/user")
    public CommonResult listUserApply(@RequestBody PageDto pageDto){
        Page<Invoice> page = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        IPage<Invoice> userList = invoiceService.listUserByPage(page, pageDto);
        return CommonResult.success(userList);
    }


    /**
     * 查询用户审核列表
     * @return
     */
    @GetMapping("/audit")
    public CommonResult listAudit(){
        LambdaQueryWrapper<Invoice> queryWrapper = new LambdaQueryWrapper<Invoice>();
        queryWrapper.eq(Invoice::getStatus, 0).or().eq(Invoice::getStatus, 3).orderByAsc(Invoice::getStatus);
        List<Invoice> list = invoiceService.list(queryWrapper);
        return CommonResult.success(list);
    }

    /**
     * 查询用户审核列表
     * @return
     */
    @GetMapping("/my/audit")
    public CommonResult listMyAudit(){
        LambdaQueryWrapper<Invoice> queryWrapper = new LambdaQueryWrapper<Invoice>();
        queryWrapper.eq(Invoice::getAuditId, UserContext.getUserId());
        queryWrapper.orderByDesc(Invoice::getAuditTime);
        List<Invoice> list = invoiceService.list(queryWrapper);
        return CommonResult.success(list);
    }


    @DeleteMapping("/delete/{id}")
    public CommonResult deleteInvoice(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.getById(id);
        // 0 表示待审核  1表示审核通过  2表示审核待修改  3表示审核拒绝
        boolean flag = (invoice.getStatus() == 0 || invoice.getStatus() == 2);
        if(!flag){
            return CommonResult.failed("发票已审核，无法进行删除");
        }
        this.invoiceService.removeById(id);
        return CommonResult.success("删除成功");
    }

    @PostMapping("/audit/update")
    public CommonResult updateById(Long invoiceId, Integer status) {
        Invoice invoice = invoiceService.getById(invoiceId);
        invoice.setAuditId(UserContext.getUserId());
        invoice.setAuditTime(LocalDateTime.now());
        invoice.setStatus(status);
        invoiceService.updateById(invoice);
        return CommonResult.success("审核成功");
    }

    @GetMapping("/info/detail/{id}")
    public  CommonResult getInvoiceDetailInfo(@PathVariable("id") Long id){
        Invoice invoice = invoiceService.getById(id);
        if(invoice == null){
            return CommonResult.failed("没有查询到发票信息");
        }
        // 增值税发票
        if(invoice.getInvoiceType().equals("invoice")){
            InvoiceIdentifyDto invoiceIdentifyDto = new InvoiceIdentifyDto();
            InvoiceDetail invoiceDetail = invoiceDetailService.getOne(new QueryWrapper<InvoiceDetail>().eq("invoice_id", id));
            invoiceIdentifyDto.setInvoice(invoice);
            invoiceIdentifyDto.setInvoiceDetail(invoiceDetail);
            return CommonResult.success(invoiceIdentifyDto);
        }else if(invoice.getInvoiceType().equals("taxi")){
            // 出租车发票
            TaxiIdentifyDto taxiIdentifyDto = new TaxiIdentifyDto();
            TaxiDetail taxiDetail = taxiDetailService.getOne(new QueryWrapper<TaxiDetail>().eq("invoice_id", id));
            taxiIdentifyDto.setInvoice(invoice);
            taxiIdentifyDto.setTaxiDetail(taxiDetail);
            return CommonResult.success(taxiIdentifyDto);
        }else if(invoice.getInvoiceType().equals("train")){
            // 火车票
            TrainIdentifyDto trainIdentifyDto = new TrainIdentifyDto();
            TrainDetail trainDetail = trainDetailService.getOne(new QueryWrapper<TrainDetail>().eq("invoice_id", id));
            trainIdentifyDto.setInvoice(invoice);
            trainIdentifyDto.setTrainDetail(trainDetail);
            return CommonResult.success(trainIdentifyDto);
        }
        return CommonResult.failed("发票类型不正确，请核对后再操作");
    }



}
