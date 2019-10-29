package cn.chenjy.url.service.impl;

import cn.chenjy.url.entity.UrlMapping;
import cn.chenjy.url.framework.util.RedisService;
import cn.chenjy.url.framework.util.UrlUtils;
import cn.chenjy.url.rep.UrlMappingRepository;
import cn.chenjy.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenjy chenjy@chenjy.cn 2019-05-30 10:22
 * @Description:
 */
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    RedisService redisService;
    @Autowired
    UrlMappingRepository urlRep;
    @Override
    public String shortUrl2LongUrl(String shortStr) {
        String rdsKey = "shortUrl:urlMapping:"+shortStr;
        if (redisService.hasKey(rdsKey)) {
            return redisService.get(rdsKey);
        } else {
            UrlMapping urlMapping = urlRep.findByShortUrlCode(shortStr);
            if (null != urlMapping){
                String rdsVal = urlMapping.getLongUrlPath();
                redisService.set(rdsKey,rdsVal,604800);
                return rdsVal;
            } else {
                return null;
            }
        }
    }

    @Override
    public String longUrl2ShortUrlForShorTime(String longStr) {
        String shortUrlCode = UrlUtils.longUrl2ShortUrl(longStr);
        String rdsKey = "shortUrl:urlMapping:"+shortUrlCode;
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrlPath(longStr);
        urlMapping.setShortUrlCode(shortUrlCode);
        urlRep.save(urlMapping);
        redisService.set(rdsKey, longStr, 60L * 60 * 24 * 7);
        return shortUrlCode;
    }


}
