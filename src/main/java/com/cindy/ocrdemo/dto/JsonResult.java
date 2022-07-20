package com.cindy.ocrdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class JsonResult {
    private String engineVersion;
    private String errorCode;
    private String objectCount;
    private String errorMsg;
    private List<ExternalObj> objectList;
}
