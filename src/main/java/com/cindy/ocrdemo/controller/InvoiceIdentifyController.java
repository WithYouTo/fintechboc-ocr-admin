package com.cindy.ocrdemo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.dto.InvoiceIdentifyDto;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import com.cindy.ocrdemo.service.InvoiceDetailService;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 识别增值税内容
 */
@RestController
@RequestMapping("invoice")
@Slf4j
public class InvoiceIdentifyController {

    // 绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    @Resource
    private InvoiceService invoiceService;

    @Resource
    private InvoiceDetailService invoiceDetailService;

    @PostMapping("/identify")
    public CommonResult getInvoiceIdentifyByImage(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request){
        try{
            String imgName = FileUtil.save(uploadFile, uploadPath);
            // 返回图片具体识别信息即可
            String netFileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgName;
            String localFileUrl = uploadPath + imgName;

            FileUrlDto fileUrlDto = new FileUrlDto();
            fileUrlDto.setNetFileUrl(netFileUrl);
            fileUrlDto.setLocalFileUrl(localFileUrl);
            InvoiceIdentifyDto invoiceIdentifyDto = invoiceDetailService.saveInvoiceByApi(fileUrlDto);
            if(invoiceIdentifyDto.getInvoiceDetail() == null || ObjectUtil.isAllEmpty(invoiceIdentifyDto.getInvoiceDetail())){
                return CommonResult.failed("未识别数据，请稍后重试");
            }else{
                return CommonResult.success(invoiceIdentifyDto);
            }
        }catch (Exception e){
           log.error("识别图片失败，失败原因{}", e.getMessage());
        }
        return CommonResult.failed("识别图片失败,请稍后重试");
    }

    @PostMapping("/save")

    public CommonResult saveInvoiceResult(@RequestBody InvoiceDetail invoiceDetail){
        // 判断当前申请单状态
        Invoice invoice = invoiceService.getOne(new LambdaQueryWrapper<Invoice>().eq(Invoice::getId, invoiceDetail.getInvoiceId()));
        if(invoice == null){
            return CommonResult.failed("没有查询到申请记录");
        }
        // 判断当前申请单状态是否是待修改
        if(invoice.getStatus() == 2){
            invoiceService.insertOperateLog(invoice.getId(), 0);
            // 状态更新为待提交
            invoice.setStatus(0);
        }
        // 如果修改小写的价税合计，需要更新主表的价格
        if(invoice.getInvoiceAmount() != invoiceDetail.getInvoiceTotalCoverTaxDigits()){
            invoice.setInvoiceAmount(invoiceDetail.getInvoiceTotalCoverTaxDigits());
        }
        invoiceService.updateById(invoice);
        invoiceDetailService.updateById(invoiceDetail);

        return CommonResult.success("保存成功");
    }

}
