package com.cindy.ocrdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.InvoiceIdentifyDto;
import com.cindy.ocrdemo.dto.PageDto;
import com.cindy.ocrdemo.dto.TaxiIdentifyDto;
import com.cindy.ocrdemo.dto.TrainIdentifyDto;
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

/**
 * 我的审核
 */
@RestController
@RequestMapping("/history")
public class MyApplyController {

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
        LambdaQueryWrapper<Invoice> queryWrapper = new LambdaQueryWrapper<Invoice>();
        // 创建时间降序， 状态升序
        queryWrapper.orderByDesc(Invoice::getCreateTime);
        queryWrapper.orderByAsc(Invoice::getStatus);
        IPage<Invoice> userList = invoiceService.listUserByPage(page, queryWrapper);
        return CommonResult.success(userList);
    }

    /**
     * 删除待审核记录
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteInvoice(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.getById(id);
        // 0 表示待审核  1表示已通过  2表示待修改  3表示已拒绝
        boolean flag = (invoice.getStatus() == 0);
        if(!flag){
            return CommonResult.failed("发票已审核，无法进行删除");
        }
        this.invoiceService.removeById(id);
        return CommonResult.success("删除成功");
    }

    /**
     * 查看发票详情
     * @param id
     * @return
     */
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
