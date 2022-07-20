package com.cindy.ocrdemo.controller;

import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.service.InvoiceDetailService;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.service.TaxiDetailService;
import com.cindy.ocrdemo.service.TrainDetailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ocr")
public class MainController {
    @Resource
    private InvoiceDetailService invoiceDetailService;

    @Resource
    private TrainDetailService trainDetailService;

    @Resource
    private TaxiDetailService taxiDetailService;


    @GetMapping("/invoice")
    public CommonResult getInvoiceOcrDataByImage(){
        try{
            invoiceDetailService.saveInvoiceByApi();
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.success("上传成功");
    }

    @GetMapping("/train")
    public CommonResult getTrainOcrDataByImage(){
        try{
            trainDetailService.saveTrainByApi();
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.success("上传成功");
    }

    @GetMapping("/taxi")
    public CommonResult getTaxiOcrDataByImage(){
        try{
            taxiDetailService.saveTaxiByApi();
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.success("上传成功");
    }


    @PostMapping("/upload")
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file){
        return CommonResult.success("上传成功");
    }
}
