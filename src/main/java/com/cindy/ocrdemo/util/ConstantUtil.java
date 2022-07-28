package com.cindy.ocrdemo.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

    public static final Map<Integer, String> auditConstMap = new HashMap<Integer, String>() {
        {
            put(0, "待审批");
            put(1, "审批通过");
            put(2, "待修改");
            put(3, "审批驳回");
        }
    };
}
