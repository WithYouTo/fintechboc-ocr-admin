package com.cindy.ocrdemo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.cindy.ocrdemo.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    // 绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        logger.info("【上传路径upload】=" + uploadPath);
        // 在 uploadPath 文件夹中通过日期对上传的文件归类保存
        String format = sdf.format(new Date());
        File folder = new File(uploadPath + format);
        logger.info("【上传路径folder】=" + uploadPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
            logger.info("文件夹创建成功");
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        logger.info("【文件夹oldName】=" + oldName);
        String newName = UUID.randomUUID().toString().replace("-", "")
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        logger.info("【文件夹newName】=" + newName);
        try {
            // 文件保存
            uploadFile.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
             String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                     + "/" + format + "/" + newName;
            return CommonResult.success(filePath);
        } catch (IOException e) {
            logger.error("上传失败", e.getMessage());
        }
        return CommonResult.success("上传成功");
    }

    @GetMapping("/delete")
    public CommonResult delete(@RequestParam("url") String url) {
        if (ObjectUtils.isEmpty(url)) {
            return CommonResult.failed("参数为空");
        }
        String[] buff = url.split("/");
        String fileName = uploadPath + "/" + buff[3] + "/" + buff[4];
        File file = new File(fileName);
        file.delete();
        return CommonResult.success("删除成功");
    }


}
