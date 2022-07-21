package com.cindy.ocrdemo.service;

import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.pojo.TrainDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cindy
* @description 针对表【ocr_train_detail(火车票发票报销明细内容)】的数据库操作Service
* @createDate 2022-07-08 21:28:16
*/
public interface TrainDetailService extends IService<TrainDetail> {
    TrainDetail saveTrainByApi(FileUrlDto fileUrlDto) throws Exception;
}
