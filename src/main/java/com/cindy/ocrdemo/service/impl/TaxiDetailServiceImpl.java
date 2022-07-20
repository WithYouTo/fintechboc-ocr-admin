package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.api.TaxiOcrApi;
import com.cindy.ocrdemo.dto.JsonResult;
import com.cindy.ocrdemo.dto.Region;
import com.cindy.ocrdemo.dto.TextBlock;
import com.cindy.ocrdemo.mapper.InvoiceMapper;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import com.cindy.ocrdemo.pojo.TaxiDetail;
import com.cindy.ocrdemo.service.TaxiDetailService;
import com.cindy.ocrdemo.mapper.TaxiDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @author cindy
* @description 针对表【ocr_taxi_detail(出租车发票报销明细内容)】的数据库操作Service实现
* @createDate 2022-07-08 21:28:09
*/
@Service
public class TaxiDetailServiceImpl extends ServiceImpl<TaxiDetailMapper, TaxiDetail>
    implements TaxiDetailService{

    @Resource
    private TaxiOcrApi taxiOcrApi;

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private TaxiDetailMapper taxiDetailMapper;

    @Override
    public void saveTaxiByApi() throws Exception {
        JsonResult jsonResult = taxiOcrApi.doRequest();
        // 解析结果为空
        if(ObjectUtils.isEmpty(jsonResult)){
            return;
        }

        List<Region> regionList = jsonResult.getObjectList().get(0).getRegionList();

        Invoice invoice = new Invoice();
        TaxiDetail taxiDetail = new TaxiDetail();

        invoice.setUserId(1L);
        invoice.setUsername("曾欣");
        invoice.setApplyDate(new Date());
        invoice.setImgPath("嘿嘿");
        invoice.setInvoiceType("taxi");
        invoice.setNote("出租车测试数据");
        invoiceMapper.insert(invoice);

        regionList.stream().forEach(i -> {
            List<TextBlock> textBlockList = i.getTextBlockList();
            String key = textBlockList.get(0).getKey();
            String val = textBlockList.get(0).getValue();
            // 购货方
            if(key.equals("Number1_code")){
                taxiDetail.setTaxiDaima(val);
            }else if(key.equals("Number2_code")){
                taxiDetail.setTaxiHaoma(val);
            }else if(key.equals("Plate-number")){
                taxiDetail.setCarNo(val);
            }else if(key.equals("Date")){
                taxiDetail.setTaxiDate(val);
            }else if(key.equals("Time")){
                taxiDetail.setTaxiTime(val);
            }else if(key.equals("Number5_amount")){
                taxiDetail.setAmount(val);
            }else if(key.equals("Number3_price")){
                taxiDetail.setPrice(val);
            }else if(key.equals("Number4_mileage")){
                taxiDetail.setMileage(val);
            }
        });
        taxiDetailMapper.insert(taxiDetail);
    }
}




