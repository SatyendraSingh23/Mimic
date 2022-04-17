/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.raven.project;

import java.io.File;  
import java.io.IOException;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.EnumMap;

/**
 *
 * @author Satyendra
 */
public class Client {
    private static final Client client=new Client();
    private final String path = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"raven"+File.separator+"project"+File.separator+"images"+File.separator+"private"+File.separator+"sat123.png";
    private String localIpAddress;
    private Client(){}

    public static Client getInstance()
    {
        return Client.client;
    }
    public String getLocalIpAddress()
    {
         try
         {
             InetAddress localhost = InetAddress.getLocalHost();
             localIpAddress = (localhost.getHostAddress()).trim();
             
         }catch(UnknownHostException e)
         {
             e.printStackTrace();
             localIpAddress=null;
         }
        return this.localIpAddress;
    }
    private static void generateQRcode(String data, String path, String charset, Map map, int h, int w)   
    {  
        try{
        //the BitMatrix class represents the 2D matrix of bits  
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));  
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void deleteImage()
    {
        File file=new File(this.path);
        if (file.exists()) file.delete();
    }
    public String getQRCode()
    {
        try
        {
            File file=new File(this.path);
            if(file.exists()){
                file.delete();
            }
            String str=this.getLocalIpAddress();
            //path where we want to get QR Code  
            //Encoding charset to be used  
            String charset = "UTF-8";  
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new EnumMap<>(EncodeHintType.class);  
            //generates QR code with Low level(L) error correction capability  
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
            //invoking the user-defined method that creates the QR code  
            Client.generateQRcode(str, path, charset, hashMap, 400, 300);//increase or decrease height and width accodingly
            return File.separator+"images"+File.separator+"private"+File.separator+"sat123.png";
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return File.separator+"images"+File.separator+"private"+File.separator+"sat123.png";
    }
}
