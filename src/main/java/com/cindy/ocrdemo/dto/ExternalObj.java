package com.cindy.ocrdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExternalObj {
    private String description;
    private String error_msg;
    private Integer regionCount;
    private String errorMsg;
    private List<Region> regionList;
}
