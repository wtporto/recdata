package br.com.qrcode;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
 
public class Main {
    public static void main(String[] args) {
        ByteArrayOutputStream out = QRCode.from("Hello World")
                .to(ImageType.PNG).stream();
       
 
        try {
        	String pasta = System.getProperty("user.home");
        	
            FileOutputStream fout = new FileOutputStream(new File(
            		pasta + "\\Imagens\\QR_Code.JPG"));
 
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toString().getBytes()));
            InputStream isFromFirstData = new ByteArrayInputStream(out.toByteArray());
            
            fout.write(out.toByteArray());
 
            fout.flush();
            fout.close();
 
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}