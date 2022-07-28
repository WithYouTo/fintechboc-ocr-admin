package com.cindy.ocrdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.pojo.Log;
import com.cindy.ocrdemo.service.LogService;
import com.cindy.ocrdemo.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author cindy
* @description 针对表【ocr_log(用户发票申请单变更记录表)】的数据库操作Service实现
* @createDate 2022-07-27 15:19:52
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}




