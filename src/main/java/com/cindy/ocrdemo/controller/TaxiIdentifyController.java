package com.cindy.ocrdemo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.dto.TaxiIdentifyDto;
import com.cindy.ocrdemo.pojo.TaxiDetail;
import com.cindy.ocrdemo.service.TaxiDetailService;
import com.cindy.ocrdemo.service.TaxiDetailService;
import com.cindy.ocrdemo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 识别出租车内容
 */
@RestController
@RequestMapping("taxi")
@Slf4j
public class TaxiIdentifyController {

    // 绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    @Resource
    private TaxiDetailService taxiDetailService;

    @PostMapping("/identify")
    public CommonResult getTaxiIdentifyByImage(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request){
        try{
            String imgName = FileUtil.save(uploadFile, uploadPath);
            // 返回图片具体识别信息即可
            String netFileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgName;
            String localFileUrl = uploadPath + imgName;

            FileUrlDto fileUrlDto = new FileUrlDto();
            fileUrlDto.setNetFileUrl(netFileUrl);
            fileUrlDto.setLocalFileUrl(localFileUrl);
            TaxiIdentifyDto taxiIdentifyDto = taxiDetailService.saveTaxiByApi(fileUrlDto);

            if(taxiIdentifyDto.getTaxiDetail() == null || ObjectUtil.isAllEmpty(taxiIdentifyDto.getTaxiDetail() )){
                return CommonResult.failed("未识别出租车任何数据，请稍后重试");
            }else{
                return CommonResult.success(taxiIdentifyDto);
            }
        }catch (Exception e){
           log.error("识别出租车信息失败，失败原因{}", e.getMessage());
        }
        return CommonResult.failed("识别图片失败,请稍后重试");
    }

    @PostMapping("/save")
    public CommonResult saveTaxiResult(@RequestBody TaxiDetail trainDetail){
        taxiDetailService.updateById(trainDetail);
        return CommonResult.success("保存成功");
    }

}
