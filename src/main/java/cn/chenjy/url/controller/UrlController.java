package cn.chenjy.url.controller;

import cn.chenjy.url.service.UrlService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * auther: chenj chenjy@chenjy.cn
 * datetime：2019/5/29 21:15
 */
@Controller
public class UrlController {

    @Autowired
    UrlService urlService;
    @Value("${const.domain}")
    String domain;
    @Value("${const.key}")
    String key;

    @RequestMapping(value = "{shortStr}", method = RequestMethod.GET)
    public String shortUrl2LongUrl(@PathVariable String shortStr, HttpServletResponse response) {
        try {
            String longUrl = urlService.shortUrl2LongUrl(shortStr);
            if (!StringUtils.isEmpty(longUrl)) {
                if (!longUrl.contains("http://") && !longUrl.contains("https://")) {
                    longUrl = "http://" + longUrl;
                }
                return "redirect:" + longUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "generateShort", method = RequestMethod.POST)
    @ResponseBody
    public String longUrl2ShortUrl(@RequestBody Map<String, String> data, HttpServletRequest request) {
        Map<String,Object> ret = new HashMap<>();
        if (!data.containsKey("url") || !data.containsKey("key")) {
            ret.put("code",403);
            ret.put("msg","params is missing");
            ret.put("data",null);
            return JSON.toJSONString(ret);
        }
        try {
            if (data.get("key").equals(key)) {
                String shortUlr = urlService.longUrl2ShortUrlForShorTime(data.get("url"));
                ret.put("code",200);
                ret.put("msg","success");
                ret.put("data",domain + shortUlr);
            } else {
                ret.put("code",401);
                ret.put("msg","key is error");
                ret.put("data",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("code",500);
            ret.put("msg","system error");
            StackTraceElement[] stes = e.getStackTrace();
            List<String>  list = new ArrayList<>();
            for (StackTraceElement s:stes){
                if (s.toString().contains("cn.chenjy.url")){
                    list.add(s.toString());
                }
            }
            ret.put("data", JSON.toJSONString(list));
        }
        return JSON.toJSONString(ret);
    }

    @RequestMapping(value = "index.html")
    public String index(){
        return "page/index";
    }
}
