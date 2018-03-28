package com.sztvis.buscloud.core.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
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
    public static boolean generateImage(String imgStr, String file, String path, HttpServletRequest request) throws FileNotFoundException {
        String filepath=request.getSession().getServletContext().getRealPath(file);
        File upload=new File(filepath);
        if (!upload.exists())
            upload.mkdirs();
        if(imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i){
                if(b[i]<0)
                    b[i] += 256;
            }
//            File pathout=new File(upload.getAbsolutePath(),path);
            OutputStream out = new FileOutputStream(filepath+path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception ex){
            return false;
        }

    }

}
