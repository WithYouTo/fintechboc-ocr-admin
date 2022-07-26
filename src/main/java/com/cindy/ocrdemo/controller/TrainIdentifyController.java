package com.cindy.ocrdemo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.dto.TrainIdentifyDto;
import com.cindy.ocrdemo.pojo.TrainDetail;
import com.cindy.ocrdemo.service.TrainDetailService;
import com.cindy.ocrdemo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 识别火车票内容
 */
@RestController
@RequestMapping("train")
@Slf4j
public class TrainIdentifyController {

    // 绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    @Resource
    private TrainDetailService trainDetailService;

    @PostMapping("/identify")
    public CommonResult getTrainIdentifyByImage(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request){
        try{
            String imgName = FileUtil.save(uploadFile, uploadPath);
            // 返回图片具体识别信息即可
            String netFileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgName;
            String localFileUrl = uploadPath + imgName;

            FileUrlDto fileUrlDto = new FileUrlDto();
            fileUrlDto.setNetFileUrl(netFileUrl);
            fileUrlDto.setLocalFileUrl(localFileUrl);
            TrainIdentifyDto trainIdentifyDto = trainDetailService.saveTrainByApi(fileUrlDto);
            if(trainIdentifyDto.getTrainDetail() == null || ObjectUtil.isAllEmpty(trainIdentifyDto.getTrainDetail())){
                return CommonResult.failed("未识别火车票任何数据，请稍后重试");
            }else{
                return CommonResult.success(trainIdentifyDto);
            }
        }catch (Exception e){
           log.error("识别火车票信息失败，失败原因{}", e.getMessage());
        }
        return CommonResult.failed("识别图片失败,请稍后重试");
    }

    @PostMapping("/save")
    public CommonResult saveTrainResult(@RequestBody TrainDetail trainDetail){
        trainDetailService.updateById(trainDetail);
        return CommonResult.success("保存成功");
    }

}
