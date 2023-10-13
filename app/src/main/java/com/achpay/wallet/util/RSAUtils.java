package com.achpay.wallet.util;

import com.achpay.wallet.util.DecryptUtils.Base64Utils;

import javax.crypto.Cipher;

import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 *
 * @author hcz
 */
public class RSAUtils {


    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDHc+PP8LuTlBL1zCX+lh9kcurgHHIXFnV/tDK789DaJuhwZvQ1lu5Zdcn+ULbNUKkB6b5tCP0sZxlpoCVKMyKHtdeh/YGXwBD8sMc+XcRs0eh3/tyr4EoBu3bomzHWDGmHjH/F5GotFTrGcB6xQwAROy4mT5SketlQ3c7tucI+QIDAQAB";
    public static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMMdz48/wu5OUEvXMJf6WH2Ry6uAcchcWdX+0Mrvz0Nom6HBm9DWW7ll1yf5Qts1QqQHpvm0I/SxnGWmgJUozIoe116H9gZfAEPywxz5dxGzR6Hf+3KvgSgG7duibMdYMaYeMf8Xkai0VOsZwHrFDABE7LiZPlKR62VDdzu25wj5AgMBAAECgYBKcdxYrp5EaHLwjNlIk0ciGfeYpvhC1yGbqY6mb1soQAhpbkJyKudyVG4EHXGpy6dyiEzoJxg063NdwWp7/sYTHk/N13UzGTudIKuNacnJk0WKu4owQticC71ZIqUjSZgN0CiEKQ6YfoGOFTzeMqzVYQjImPzGdLK74y3YYlmigQJBAObzhzYlWjOypx3klmrPTu2KXPg3ATTEB8kN/isY8bYuikVdd2yUd0AvaC7PPwEEjGmsSrEeXw1tsVfZ8VkBaikCQQDYR0+8VzGLdgIFQc/6+IY5fQlEt/Hc7qsi7JT4o+f+BGJlAT7+OeDMThavKdWq1UvZDyCKdtYRfxQ1jj7D4yJRAkBrG6InkGcm9sHecTb5Ti+ypqq7Svc6O3fI3L51ylm/PhJOXSyXpLsxf0r3+pGjrTJZh9gUEJvQpIDM13zA5JERAkBI2zTsED9baIRjuvjR5Xhp00oVARYTw76YxDOm0qgq9NUki1fqEhs9F60ikqgspS+oziS7IC8as8FeDS3tlQ0RAkA5OdDvhQRQPI75ULyHazTEm4Rak8TKmKl64pmnwcw4GS9fKWs7jRAuem1OtwA8HAqjaDeXC8Cd6fDfq7z5bZnE";


    /**RSA算法*/
    public static final String RSA = "RSA";
    /**加密方式，android的*/
//  public static final String TRANSFORMATION = "RSA/None/NoPadding";
    /**加密方式，标准jdk的*/
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    /** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";


    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;





    /** */
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }



    /** */
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }





    /**
     * java端公钥加密
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) {
        try {
            data = Base64Utils.encode(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }





    /**
     * java端私钥解密
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) {
        String temp = "";

        try {
            byte[] bytes = Base64Utils.decode(data);

            temp = new String(decryptByPrivateKey(bytes, PRIVATEKEY),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }


}
