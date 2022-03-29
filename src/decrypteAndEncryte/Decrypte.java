/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decrypteAndEncryte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author chris
 */
public class Decrypte {
    
     private Cipher cipher;

    public Decrypte() throws NoSuchAlgorithmException, NoSuchPaddingException {
        System.out.println("\n------------------------------------------------------\nProceso de Decifra inicializado...");
         this.cipher = Cipher.getInstance("RSA");
    }
    
    public PrivateKey getPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        System.out.println("\nCargado la clave privada... ");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        System.out.println("Clave privada cargado con exit!");
        return kf.generatePrivate(spec);
    }
       
       
       
    public byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }
           
    
    public byte[] decryptFile(byte[] input, File output, PrivateKey key) 
        throws IOException, GeneralSecurityException {
        System.out.println("\nDecryptado mensaje...");
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        System.out.println("Mensaje decryptado");
        
        byte[] mensajeDecryptado = this.cipher.doFinal(input);
        writeToFile(output, mensajeDecryptado);
        
        return mensajeDecryptado;
    }
              
              
    private void writeToFile(File output, byte[] toWrite)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }
     
     
}
