import java.security.*;
import java.io.*;

public class KeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        // Save Private Key
        try (FileOutputStream out = new FileOutputStream("private.key")) {
            out.write(pair.getPrivate().getEncoded());
        }

        // Save Public Key
        try (FileOutputStream out = new FileOutputStream("public.key")) {
            out.write(pair.getPublic().getEncoded());
        }

        System.out.println("✅ Keys generated successfully.");
    }
}
