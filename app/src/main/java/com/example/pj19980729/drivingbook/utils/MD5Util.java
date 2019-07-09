package com.example.pj19980729.drivingbook.utils;
import java.security.MessageDigest;

/**
 * @author 刘煦健
 * @date 2019-06-25 10:51
 * Description:<描述>
 */

public class MD5Util {

    private static final String[] HEX_DIGITS = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    /**
     * MD5加密
     *
     * @param origin 字符
     * @return 加密结果
     */
    public static String MD5Encode(String origin) {

        return MD5Encode(origin,"utf8");
    }


    /**
     * MD5加密
     * @param origin 字符
     * @param charsetname 编码
     * @return
     */
    public static String MD5Encode(String origin, String charsetname){
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }catch (Exception e){
        }
        return resultString;
    }

    /**
     *
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String password = "123";
        String encoded = MD5Encode(password);
        System.out.println(password);
        System.out.println(encoded);
    }
}
