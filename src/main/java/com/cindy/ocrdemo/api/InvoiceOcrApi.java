package com.cindy.ocrdemo.api;

import com.alibaba.fastjson.JSON;
import com.cindy.ocrdemo.dto.JsonResult;
import com.cindy.ocrdemo.util.FileUtil;
import com.cindy.ocrdemo.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InvoiceOcrApi {
    @Value("${iflytek.requestInvoiceUrl}")
    private  String requestUrl;

    @Value("${iflytek.apiKey}")
    private  String apiKey;

    @Value("${iflytek.apiSecret}")
    private  String apiSecret;

    @Value("${iflytek.appid}")
    private  String apiId;

//    private  String imagePath = "F:\\data\\invoice_image-02.jpg";

    //解析Json
    private static Gson json = new Gson();

    //1、构建url
    public  String assembleRequestUrl(String requestUrl, String apiKey, String apiSecret) {
        URL url = null;
        // 替换调schema前缀 ，原因是URL库不支持解析包含ws,wss schema的url
        String httpRequestUrl = requestUrl.replace("ws://", "http://").replace("wss://", "https://");
        try {
            url = new URL(httpRequestUrl);
            //获取当前日期并格式化
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = format.format(new Date());

            String host = url.getHost();
            StringBuilder builder = new StringBuilder("host: ").append(host).append("\n").//
                    append("date: ").append(date).append("\n").//
                    append("POST ").append(url.getPath()).append(" HTTP/1.1");

            Charset charset = Charset.forName("UTF-8");
            Mac mac = Mac.getInstance("hmacsha256");
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
            mac.init(spec);
            byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
            String sha = Base64.getEncoder().encodeToString(hexDigits);

            String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
            String authBase = Base64.getEncoder().encodeToString(authorization.getBytes(charset));
            return String.format("%s?authorization=%s&host=%s&date=%s", requestUrl, URLEncoder.encode(authBase), URLEncoder.encode(host), URLEncoder.encode(date));

        } catch (Exception e) {
            throw new RuntimeException("assemble requestUrl error:" + e.getMessage());
        }
    }

    // 2、获取参数
    public String getXParam(String imageBase641, String imageEncoding1) {
        JsonObject jso = new JsonObject();

        /** header **/
        JsonObject header = new JsonObject();
        header.addProperty("app_id", apiId);
        header.addProperty("status", 3);

        jso.add("header", header);

        /** parameter **/
        JsonObject parameter = new JsonObject();
        JsonObject service = new JsonObject();
        JsonObject result = new JsonObject();
        result.addProperty("encoding", "utf8");
        result.addProperty("format", "json");
        result.addProperty("compress", "raw");
        service.add("result", result);
        service.addProperty("template_list", "vat_invoice");

        parameter.add("s824758f1", service);
        jso.add("parameter", parameter);

        /** payload **/
        JsonObject payload = new JsonObject();
        JsonObject inputImage1 = new JsonObject();
        inputImage1.addProperty("encoding", imageEncoding1);
        inputImage1.addProperty("status", 3);
        inputImage1.addProperty("image", imageBase641);
        payload.add("s824758f1_data_1", inputImage1);

        jso.add("payload", payload);
        return jso.toString();
    }

    //3、读取image
    private byte[] readImage(String imagePath) throws IOException {
        InputStream is = new FileInputStream(imagePath);
        byte[] imageByteArray1 = FileUtil.read(imagePath);
        return imageByteArray1;
    }

    //4、处理图片请求
    private ResponseData handleImgContrastRes(String url, String bodyParam) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        String result = HttpUtil.doPost2(url, headers, bodyParam);
        if (result != null) {
            System.out.println("增值税发票识别接口调用结果：" + result);
            return json.fromJson(result, ResponseData.class);
        } else {
            return null;
        }
    }

    //5、比较图片
    public ResponseData imageContrast(String imageFirstUrl) throws Exception {
        String url = assembleRequestUrl(requestUrl, apiKey, apiSecret);
        String imageBase641 = Base64.getEncoder().encodeToString(readImage(imageFirstUrl));
        String imageEncoding1 = imageFirstUrl.substring(imageFirstUrl.lastIndexOf(".") + 1);
        return handleImgContrastRes(url, getXParam(imageBase641, imageEncoding1));
    }

    public static class ResponseData {
        private Header header;
        private PayLoad payload;

        public Header getHeader() {
            return header;
        }

        public PayLoad getPayLoad() {
            return payload;
        }
    }

    public static class Header {
        private int code;
        private String message;
        private String sid;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getSid() {
            return sid;
        }
    }

    public static class PayLoad {
        private Result result;

        public Result getResult() {
            return result;
        }
    }

    public static class Result {
        private String compress;
        private String encoding;
        private String format;
        private String text;

        public String getCompress() {
            return compress;
        }

        public String getEncoding() {
            return encoding;
        }

        public String getFormat() {
            return format;
        }

        public String getText() {
            return text;
        }
    }

    public  JsonResult doRequest(String filePath) throws Exception {
        JsonResult parse = new JsonResult();
        ResponseData respData = imageContrast(filePath);
        if (respData.getPayLoad().getResult() != null) {
            String textBase64 = respData.getPayLoad().getResult().getText();
            String text = new String(Base64.getDecoder().decode(textBase64));
            // 解析返回结果
            parse = JSON.parseObject(text, (Type) JsonResult.class);
        }
        return parse;
    }
}
