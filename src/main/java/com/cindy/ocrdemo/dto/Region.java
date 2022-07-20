package com.cindy.ocrdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Region {
    private Integer id;
    private Integer order;
    private String type;
    private List<TextBlock> textBlockList;
}
