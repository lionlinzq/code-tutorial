package utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * AES加密算法工具类
 * @since: 2021/6/28
 * @author: hongdahao
 */
public class AesEncoder {

    //算法名称
    final String KEY_ALGORITHM = "AES";

    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";

    private Key key;

    private Cipher cipher;

    /**
     * 解密
     * @param encryptedDataStr
     * @param keyBytesStr
     * @param ivStr
     * @return
     * @author wangyue
     * @since 2018.08.15
     */
    public byte[] decrypt(String encryptedDataStr, String keyBytesStr, String ivStr) throws Exception {
        byte[] encryptedText = null;
        byte[] encryptedData = null;
        byte[] sessionkey = null;
        byte[] iv = null;

        try {
            sessionkey = Base64.decodeBase64(keyBytesStr);
            encryptedData = Base64.decodeBase64(encryptedDataStr);
            iv = Base64.decodeBase64(ivStr);

            init(sessionkey);

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw e;
        }
        return encryptedText;
    }

    public void init(byte[] keyBytes) {

        // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        AesEncoder s = new AesEncoder();
        String encryptedDataStr = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM\"\n"
                + "    \"QmRzooG2xrDcvSnxIMXFufNstNGTyaGS\"\n"
                + "    \"9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+\"\n"
                + "    \"3hVbJSRgv+4lGOETKUQz6OYStslQ142d\"\n"
                + "    \"NCuabNPGBzlooOmB231qMM85d2/fV6Ch\"\n"
                + "    \"evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6\"\n"
                + "    \"/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw\"\n"
                + "    \"u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn\"\n"
                + "    \"/Hz7saL8xz+W//FRAUid1OksQaQx4CMs\"\n"
                + "    \"8LOddcQhULW4ucetDf96JcR3g0gfRK4P\"\n"
                + "    \"C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB\"\n"
                + "    \"6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns\"\n"
                + "    \"/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd\"\n"
                + "    \"lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV\"\n"
                + "    \"oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG\"\n"
                + "    \"20f0a04COwfneQAGGwd5oa+T8yO5hzuy\"\n" + "    \"Db/XcxxmK01EpqOyuxINew==";
        String keyBytesStr = "tiihtNczf5v6AKRyjwEUhQ==";
        String ivStr = "r7BXXKkLb8qrSNn05n0qiA==";

        byte[] decrypt = s.decrypt(encryptedDataStr, keyBytesStr, ivStr);
        String str;
        try {
            str = new String(decrypt, "utf-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
