import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class VerifySignature {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get("document.txt"));
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get("public.key"));
        byte[] signatureBytes = Base64.getDecoder().decode(Files.readAllBytes(Paths.get("signature.sig")));

        // Load public key
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Verify signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        boolean isValid = signature.verify(signatureBytes);

        if (isValid) {
            System.out.println("✅ Signature is valid.");
        } else {
            System.out.println("❌ Signature is NOT valid.");
        }
    }
}
