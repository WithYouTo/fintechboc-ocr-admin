package com.cindy.ocrdemo.aop;

import com.cindy.ocrdemo.dto.AuditCheckDto;
import com.cindy.ocrdemo.dto.UserContext;
import com.cindy.ocrdemo.pojo.*;
import com.cindy.ocrdemo.service.InvoiceService;
import com.cindy.ocrdemo.service.LogService;
import com.cindy.ocrdemo.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperationLogAop {

    @Resource
    private InvoiceService invoiceService;

    @Resource
    private LogService logService;

    /**
     * 定义拦截点
     */
    @Pointcut("@annotation(com.cindy.ocrdemo.anno.OperationLog)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void recordOperation(JoinPoint joinPoint) throws Throwable {
        // 获取请求参数
        /*
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        这里可以获取到get请求的参数和其他信息
        log.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        */

        //重点 这里就是获取@RequestBody参数的关键  调试的情况下 可以看到o变量已经获取到了请求的参数
        Object[] objArr = joinPoint.getArgs();
        if(objArr.length <= 0){
            return ;
        }
        Object param = objArr[0];

        AuditCheckDto auditCheckDto = (AuditCheckDto)param;

        Invoice invoice = invoiceService.getById(auditCheckDto.getInvoiceId());
        // 记录状态变更
        if(invoice.getStatus() != auditCheckDto.getStatus()){
            Log log = new Log();
            log.setInvoiceId(auditCheckDto.getInvoiceId());
            log.setImgUrl(invoice.getNetImgPath());
            log.setCreateTime(LocalDateTime.now());
            log.setCreateEmp(UserContext.getUser().getUsername());
            String s = "审核状态由【" + ConstantUtil.auditConstMap.get(invoice.getStatus())  + "】更新为【" + ConstantUtil.auditConstMap.get(auditCheckDto.getStatus())  + "】";
            log.setOperateContent(s);
            logService.save(log);
        }
    }



}
