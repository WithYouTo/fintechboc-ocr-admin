package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.api.TrainOcrApi;
import com.cindy.ocrdemo.dto.JsonResult;
import com.cindy.ocrdemo.dto.Region;
import com.cindy.ocrdemo.dto.TextBlock;
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
        public void saveTrainByApi() throws Exception {
            JsonResult jsonResult = trainOcrApi.doRequest();
            // 解析结果为空
            if(ObjectUtils.isEmpty(jsonResult)){
                return;
            }

            List<Region> regionList = jsonResult.getObjectList().get(0).getRegionList();

            Invoice invoice = new Invoice();
            TrainDetail trainDetail = new TrainDetail();

            invoice.setUserId(1L);
            invoice.setUsername("曾欣");
            invoice.setApplyDate(new Date());
            invoice.setImgPath("火车票");
            invoice.setInvoiceType("traIn");
            invoice.setNote("火车票测试数据");
            invoiceMapper.insert(invoice);

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
                    trainDetail.setAmount(val);
                }else if(key.equals("Seat-type")){
                    trainDetail.setSeatType(val);
                }
            });
            trainDetailMapper.insert(trainDetail);
        }
}




