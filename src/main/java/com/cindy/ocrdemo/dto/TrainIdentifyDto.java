package com.cindy.ocrdemo.dto;

import com.cindy.ocrdemo.pojo.Invoice;
import com.cindy.ocrdemo.pojo.TrainDetail;
import lombok.Data;

@Data
public class TrainIdentifyDto {

    private Invoice invoice;

    private TrainDetail trainDetail;
}
