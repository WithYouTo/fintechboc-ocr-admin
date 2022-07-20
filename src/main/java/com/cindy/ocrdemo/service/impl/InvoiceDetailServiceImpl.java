package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.api.InvoiceOcrApi;
import com.cindy.ocrdemo.dto.ExternalObj;
import com.cindy.ocrdemo.dto.JsonResult;
import com.cindy.ocrdemo.dto.Region;
import com.cindy.ocrdemo.dto.TextBlock;
import com.cindy.ocrdemo.mapper.InvoiceMapper;
import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import com.cindy.ocrdemo.service.InvoiceDetailService;
import com.cindy.ocrdemo.mapper.InvoiceDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author cindy
 * @description 针对表【ocr_invoice_detail(增值税发票报销明细内容)】的数据库操作Service实现
 * @createDate 2022-07-08 21:27:23
 */
@Service
public class InvoiceDetailServiceImpl extends ServiceImpl<InvoiceDetailMapper, InvoiceDetail>
        implements InvoiceDetailService{

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    private InvoiceOcrApi invoiceOcrApi;

    @Override
    public void saveInvoiceByApi() throws Exception {
//        无法获取到配置文件中的值 , @component 将它声明为ioc容器可托管， 通过依赖注入，否则无法获取到值
//        InvoiceOcrApi invoiceOcrApi = new InvoiceOcrApi();
        JsonResult jsonResult = invoiceOcrApi.doRequest();
        // 解析结果为空
        if(ObjectUtils.isEmpty(jsonResult)){
            return;
        }

        List<Region> regionList = jsonResult.getObjectList().get(0).getRegionList();

        Invoice invoice = new Invoice();
        InvoiceDetail invoiceDetail = new InvoiceDetail();

        invoice.setUserId(1L);
        invoice.setUsername("曾欣");
        invoice.setApplyDate(new Date());
        invoice.setImgPath("xxxx");
        invoice.setInvoiceType("invoice");
        invoice.setNote("测试数据");
        invoiceMapper.insert(invoice);

        regionList.stream().forEach(i -> {
            List<TextBlock> textBlockList = i.getTextBlockList();
            String key = textBlockList.get(0).getKey();
            String val = textBlockList.get(0).getValue();
            // 购货方
            if(key.equals("vat-invoice-payer-name")){
                invoiceDetail.setInvoicePayerName(val);
            }else if(key.equals("vat-invoice-rate-payer-id")){
                invoiceDetail.setInvoiceRatePayerId(val);
            }else if(key.equals("vat-invoice-payer-addr-tell")){
                invoiceDetail.setInvoicePayerAddrTell(val);
            }else if(key.equals("vat-invoice-payer-bank-account")){
                invoiceDetail.setInvoicePayerBankAccount(val);
            }else if(key.equals("cryptographic-area")){
                invoiceDetail.setCryptographicArea(val);
            }else if(key.equals("vat-invoice-haoma")){
                invoiceDetail.setInvoiceHaoma(val);
            }else if(key.equals("vat-invoice-daima")){
                invoiceDetail.setInvoiceDaima(val);
            }else if(key.equals("vat-invoice-issue-date")){
                invoiceDetail.setInvoiceIssueDate(val);
            }else if(key.equals("vat-invoice-goods-list")){
                invoiceDetail.setInvoiceGoodsList(val);
            }else if(key.equals("unit-price")){
                invoiceDetail.setUnitPrice(val);
            }else if(key.equals("vat-invoice-tax-list")){
                invoiceDetail.setInvoiceTaxList(val);
            }else if(key.equals("vat-invoice-tax-rate-list")){
                invoiceDetail.setInvoiceTaxRateList(val);
            }else if(key.equals("vat-invoice-total")){
                invoiceDetail.setInvoiceTotal(val);
            }else if(key.equals("vat-invoice-tax-total")){
                invoiceDetail.setInvoiceTaxTotal(val);
            }else if(key.equals("vat-invoice-total-cover-tax")){
                invoiceDetail.setInvoiceTotalCoverTax(val);
            }else if(key.equals("vat-invoice-total-cover-tax-digits")){
                invoiceDetail.setInvoiceTotalCoverTaxDigits(val);
            }else if(key.equals("vat-invoice-seller-name")){
                invoiceDetail.setInvoiceSellerName(val);
            }else if(key.equals("vat-invoice-seller-id")){
                invoiceDetail.setInvoiceSellerId(val);
            }else if(key.equals("vat-invoice-seller-addr-tell")){
                invoiceDetail.setInvoiceSellerAddrTell(val);
            }else if(key.equals("vat-invoice-seller-bank-account")){
                invoiceDetail.setInvoiceSellerBankAccount(val);
            }
        });
        invoiceDetailMapper.insert(invoiceDetail);
    }
}




