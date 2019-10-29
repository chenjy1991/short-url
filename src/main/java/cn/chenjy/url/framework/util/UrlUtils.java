package cn.chenjy.url.framework.util;

import java.security.MessageDigest;
import java.util.Random;

import static java.lang.Long.parseLong;

/**
 * @author chenjy chenjy@chenjy.cn 2019-05-29 17:27
 * @Description:
 */
public class UrlUtils {
    static String[] chars = new String[]{          //要使用生成URL的字符
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    public static String longUrl2ShortUrl(String longUrl) {
        String shorUrl = "";
        String md5Str = MD5(generateSalt() + longUrl);
        String[] strs = new String[4];
        for (int i = 0; i < 4; i++) {
            int j = i + 1;
            String str = md5Str.substring(i * 8, j * 8);
            strs[i] = str;
        }
        String chooseStr = strs[(int) Math.random() * 4];
        return hex16to64(chooseStr);
    }

    public static String shortUrl2LongUrl(String shorUrl) {
        return shorUrl;
    }


    private static String generateSalt() {
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(source.charAt(random.nextInt(source.length())));
        }
        return sb.toString();
    }

    private static String hex16to64(String str) {
        int scale = 62;
        long hex10 = Long.parseLong(str, 16);
        StringBuilder sb = new StringBuilder();
        do {
            int remainder = (int) (hex10 % scale);
            sb.append(chars[remainder]);
            hex10 = hex10 / scale;
        }
        while (hex10 > scale - 1);

        sb.append(chars[(int) hex10]);
        return sb.toString();
    }

    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
