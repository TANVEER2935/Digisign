import java.nio.file.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.io.*;

public class SignDocument {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get("document.txt"));
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("private.key"));

        // Load private key
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Sign the document
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] digitalSignature = signature.sign();

        // Save signature
        Files.write(Paths.get("signature.sig"), Base64.getEncoder().encode(digitalSignature));

        System.out.println("✅ Document signed.");
    }
}
