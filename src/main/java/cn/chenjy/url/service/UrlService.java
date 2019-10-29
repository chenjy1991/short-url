package cn.chenjy.url.service;

/**
 * @author chenjy chenjy@chenjy.cn 2019-05-30 10:22
 * @Description:
 */
public interface UrlService {
    String shortUrl2LongUrl(String shortStr);

    String longUrl2ShortUrlForShorTime(String longStr);

}
