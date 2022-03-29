/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decrypteAndEncryte;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author chris
 */
public class Encrypte {
    
  private Cipher cipher;

    public Encrypte() throws NoSuchAlgorithmException, NoSuchPaddingException {
            System.out.println("Proceso de Cifra inicializado...");
        this.cipher = Cipher.getInstance("RSA");
    }
    
    //recuperar la clave privada
     public PrivateKey getPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
     
     //recuperar la clave publica
     
      public PublicKey getPublic(String filename) throws Exception {
           System.out.println("\nCargando la clave publica...");
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        System.out.println("Clave publica cargando con exit!");
        return kf.generatePublic(spec);
         
    }
      
      

   //función para escribir dentro el file 
     private void writeToFile(File output, byte[] toWrite)
      throws IllegalBlockSizeException, BadPaddingException, IOException {
         System.out.println("\nWrite to file...");
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }
     
     
     //enriptar mis mensaje dentro de mi file
     
       public void encryptFile(String MensajeA_Cifrar, File output, PublicKey key) 
        throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        System.out.println("\nEncrytando mensaje...");
        byte[] mensajeCifrado = this.cipher.doFinal(MensajeA_Cifrar.getBytes());
           
           writeToFile(output, mensajeCifrado);
           System.out.println("Mensaje encryptado con exit!");
        }

  
    
    
}
