/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifradoasimetrico;

import decrypteAndEncryte.Decrypte;
import decrypteAndEncryte.Encrypte;
import generetorKeys.GenerateKeys;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author chris
 */
public class CifradoAsimetrico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {


         //Proceso de encryptación--------------------------------------------------
          Encrypte encrypte = new Encrypte();
          
          PublicKey publickeys =  encrypte.getPublic("KeyPair/publicKey");
          
          //File donde se va a almacenar el mensaje cifrado
          File fileEncryptado = new File("KeyPair/mensajeEncryptado.txt");
         
          //String del mensaje
            String mensaje = "Hola jose Manuel! Que tal va ? ";
            
            System.out.println("Voy a cifrar el mensaje...");

            encrypte.encryptFile(mensaje, fileEncryptado, publickeys);
            
            //Proceso de decryptación--------------------------------------------------------------
            Decrypte decrypte = new Decrypte();
            
            byte[] mensajeCifrado = decrypte.getFileInBytes(fileEncryptado);
            
            PrivateKey privatekeys =  encrypte.getPrivate("KeyPair/privateKey");
            
            File fileDecryptado = new File("KeyPair/mensajeDecryptado.txt");
            
            System.out.println("\nVoy a decifrar el mensaje...");
            
            byte[] mensajeDecryptado = decrypte.decryptFile(mensajeCifrado, fileDecryptado, privatekeys);
           
            System.out.println("\nMensaje enviado : "+new String(mensajeDecryptado));
          
          
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        } catch (NoSuchPaddingException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
    
}


//Mi codigo para crear mis propio claves
//        try {
//            generetorKeys.GenerateKeys generateKey = new GenerateKeys(1024);
//            
//            generateKey.generar();
//
//            
//        } catch (NoSuchAlgorithmException | NoSuchProviderException | IOException ex) {
//            System.err.println(ex.getMessage());
//        }