package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class DesUtil {

    public static byte[] encrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return cipher.doFinal(src);
    }

    public static String encrypt(String src, String password) throws Exception {
        byte[] e = encrypt(src.getBytes(), password);
        return Base64.getEncoder().encodeToString(e);
    }

    public static String decrypt(String src, String password) throws Exception {
        byte[] base = Base64.getDecoder().decode(src);
        byte[] d = decrypt(base, password);
        return new String(d);
    }

    public static void main(String[] args) throws Exception {
        byte[] SECURITY_KEY = {0x36, 0x31, 0x33, 0x30, 0x32, 0x32, 0x32, 0x32, 0x32};
        String ret = encrypt("jdbc:postgresql://172.16.0.150:15432/tenant_1008851_dev?user=postgres&password=csb123456&allowMultiQueries=true&stringtype=unspecified", new String(SECURITY_KEY));
        System.out.println(ret);

        String e = "qsN5r7Mo7211ZDPop520/am2H7UovQNQW4AhVYlVwpXuswB6sscVmp0gc+5mAjHMJLcQ7vB/uBm/Pap4igcWtVGyL9EK0jrxmDqsL1+e1Rr/W/tfu2KB0PywgFDCzz717HPUEIUSTDWjOZHFUFAVbTUlZp1Q4dRAb8B3KOTlFbSmTiBt4Hb2JQ==";
        String dd = decrypt(e, new String(SECURITY_KEY));
        System.out.println(dd);
    }

}
