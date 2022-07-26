package com.cindy.ocrdemo.service;

import com.cindy.ocrdemo.dto.FileUrlDto;
import com.cindy.ocrdemo.dto.TaxiIdentifyDto;
import com.cindy.ocrdemo.pojo.TaxiDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cindy
* @description 针对表【ocr_taxi_detail(出租车发票报销明细内容)】的数据库操作Service
* @createDate 2022-07-08 21:28:09
*/
public interface TaxiDetailService extends IService<TaxiDetail> {
    TaxiIdentifyDto saveTaxiByApi(FileUrlDto fileUrlDto) throws Exception;
}
