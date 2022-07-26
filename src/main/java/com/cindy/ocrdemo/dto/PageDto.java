package com.cindy.ocrdemo.dto;

import lombok.Data;

@Data
public class PageDto {
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
