package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.api.TrainOcrApi;
import com.cindy.ocrdemo.dto.*;
import com.cindy.ocrdemo.mapper.InvoiceMapper;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.TrainDetail;
import com.cindy.ocrdemo.service.TrainDetailService;
import com.cindy.ocrdemo.mapper.TrainDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author cindy
* @description 针对表【ocr_train_detail(火车票发票报销明细内容)】的数据库操作Service实现
* @createDate 2022-07-08 21:28:16
*/
@Service
public class TrainDetailServiceImpl extends ServiceImpl<TrainDetailMapper, TrainDetail>
    implements TrainDetailService{

        @Resource
        private TrainOcrApi trainOcrApi;

        @Resource
        private InvoiceMapper invoiceMapper;

        @Resource
        private TrainDetailMapper trainDetailMapper;

        @Override
        public TrainIdentifyDto saveTrainByApi(FileUrlDto fileUrlDto) throws Exception {
            JsonResult jsonResult = trainOcrApi.doRequest(fileUrlDto.getLocalFileUrl());
            // 解析结果为空
            if(ObjectUtils.isEmpty(jsonResult)){
                return null;
            }

            List<Region> regionList = jsonResult.getObjectList().get(0).getRegionList();

            Invoice invoice = new Invoice();
            TrainDetail trainDetail = new TrainDetail();

            invoice.setUserId(UserContext.getUserId());
            invoice.setUsername(UserContext.getUser().getUsername());
            invoice.setApplyDate(new Date());
            invoice.setLocalImgPath(fileUrlDto.getLocalFileUrl());
            invoice.setNetImgPath(fileUrlDto.getNetFileUrl());
            invoice.setInvoiceType("train");

            regionList.stream().forEach(i -> {
                List<TextBlock> textBlockList = i.getTextBlockList();
                String key = textBlockList.get(0).getKey();
                String val = textBlockList.get(0).getValue();
                if(key.equals("Number1")){
                    trainDetail.setTicketNo(val);
                }else if(key.equals("Ticket-check")){
                    trainDetail.setTicketCheck(val);
                }else if(key.equals("Station-From")){
                    trainDetail.setStationFrom(val);
                }else if(key.equals("Station-To")){
                    trainDetail.setStationTo(val);
                }else if(key.equals("Number2-Train")){
                    trainDetail.setTrain(val);
                }else if(key.equals("Date")){
                    trainDetail.setGoDate(val);
                }else if(key.equals("Time")){
                    trainDetail.setGotime(val);
                }else if(key.equals("Seat")){
                    trainDetail.setSeat(val);
                }else if(key.equals("Name")){
                    trainDetail.setName(val);
                }else if(key.equals("identity")){
                    trainDetail.setIdentity(val);
                }else if(key.equals("Number3-Amount")){
                    // 发票总额
                    invoice.setInvoiceAmount(val);
                    trainDetail.setAmount(val);
                }else if(key.equals("Seat-type")){
                    trainDetail.setSeatType(val);
                }
            });

            invoiceMapper.insert(invoice);
            trainDetail.setInvoiceId(invoice.getId());
            trainDetailMapper.insert(trainDetail);

            TrainIdentifyDto trainIdentifyDto = new TrainIdentifyDto();
            trainIdentifyDto.setInvoice(invoice);
            trainIdentifyDto.setTrainDetail(trainDetail);
            return trainIdentifyDto;
        }
}




