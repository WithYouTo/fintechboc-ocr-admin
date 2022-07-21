package com.cindy.ocrdemo.controller;

import com.cindy.ocrdemo.common.CommonResult;
import org.springframework.web.bind.annotation.*;

/**
 * 主管审核发票信息
 */
@RestController
@RequestMapping("/list")
public class InvoiceListController {

    /**
     * 查询用户提交发票列表
     * @return
     */
    @GetMapping("/user")
    public CommonResult listUserApply(){
        return CommonResult.success(null);
    }


    /**
     * 查询用户审核列表
     * @return
     */
    @GetMapping("/audit")
    public CommonResult listaAudit(){
        return CommonResult.success(null);
    }

}
