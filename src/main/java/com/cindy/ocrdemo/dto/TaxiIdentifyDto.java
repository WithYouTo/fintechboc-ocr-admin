package com.cindy.ocrdemo.dto;

import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.TaxiDetail;
import lombok.Data;

@Data
public class TaxiIdentifyDto {

    private Invoice invoice;

    private TaxiDetail taxiDetail;
}
