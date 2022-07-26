package com.cindy.ocrdemo.dto;

import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.InvoiceDetail;
import lombok.Data;

@Data
public class InvoiceIdentifyDto {

    private Invoice invoice;

    private InvoiceDetail invoiceDetail;
}
