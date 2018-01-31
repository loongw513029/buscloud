package com.sztvis.buscloud.core.helper;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午6:14
 */
public class ImageHelper {
    /**
     * 将Base64字符串转换为图片
     * @param imgStr base64字符串
     * @param path 图片地址
     * @return
     */
    public static boolean generateImage(String imgStr,String path){
        if(imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;i++){
                if(b[i]<0)
                    b[i] += 256;
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}
