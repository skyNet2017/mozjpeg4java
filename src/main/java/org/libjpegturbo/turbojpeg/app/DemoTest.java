package org.libjpegturbo.turbojpeg.app;

import org.libjpegturbo.turbojpeg.processor.api.ImageProcessException;
import org.libjpegturbo.turbojpeg.processor.api.ImageProcessor;
import org.libjpegturbo.turbojpeg.processor.impl.ImageProcessorImpl;
import org.libjpegturbo.turbojpeg.processor.utils.ImageProcessorUtils;

import java.io.File;
import java.text.DecimalFormat;

public class DemoTest {

    public static void main(String[] arsg){
        String path = "D:\\Nikon\\DSC_0229.JPG";
        File in = new File(path);
        File out = new File(in.getParentFile(),"compress-"+in.getName());
        ImageProcessor processor = new ImageProcessorImpl();
        try {
            long start = System.currentTimeMillis();
            ImageProcessorUtils.compressImage(processor, in, out, 80);
            System.out.println("Total time: "+(System.currentTimeMillis() - start)
                    +" msec,file size:"+formatFileSize(in.length())+"->"+formatFileSize(out.length())+",compress "+(out.length()*100/in.length())+"%,path: "+ in.getAbsolutePath() );
        } catch (ImageProcessException e) {
            e.printStackTrace();
        }
    }

    public static String formatFileSize(long size) {
        try {
            DecimalFormat dff = new DecimalFormat(".0");

            if (size >= 1024 * 1024 * 1024) {
                double doubleValue = ((double) size) / (1024 * 1024 * 1024);
                dff = new DecimalFormat(".00");
                String value = dff.format(doubleValue);
                return value + "G";
            } else if (size >= 1024 * 1024) {
                double doubleValue = ((double) size) / (1024 * 1024);
                String value = dff.format(doubleValue);
                return value + "M";
            } else if (size > 1024) {
                double doubleValue = ((double) size) / 1024;
                String value = dff.format(doubleValue);
                return value + "K";
            } else {
                return size + "B";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(size);
    }

}
