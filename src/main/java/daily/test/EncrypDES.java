package daily.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class EncrypDES {

	// KeyGenerator 提供对称密钥生成器的功能，支持各种算法
	private KeyGenerator keygen;
	// SecretKey 负责保存对称密钥
	
	private SecretKey deskey;
	// Cipher负责完成加密或解密工作
	private Cipher c;
	// 该字节数组负责保存加密的结果
	private byte[] cipherByte;

	private String keyPath = "d:/key.dat";

	public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
		keygen = KeyGenerator.getInstance("DES");
		// 生成密钥
		deskey = keygen.generateKey();

		// 保存密钥到文件中
		//this.setKey();
		
		// 生成Cipher对象,指定其支持的DES算法
		c = Cipher.getInstance("DES");
	}

	public void setKey() {
		SecretKey k = keygen.generateKey();
		FileOutputStream f = null;
		try {
			f = new FileOutputStream(this.keyPath);
			ObjectOutputStream b = new ObjectOutputStream(f);
			b.writeObject(k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Key getKey(){
		return deskey;
	}
	
//	public Key getKey() {
//		FileInputStream f = null;
//		Key k = null;
//		try {
//			f = new FileInputStream(this.keyPath);
//			ObjectInputStream b = new ObjectInputStream(f);
//			k = (Key) b.readObject();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				f.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return k;
//	}

	/**
	 * 对字符串加密
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Encrytor(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		// c.init(Cipher.ENCRYPT_MODE, deskey);
		c.init(Cipher.ENCRYPT_MODE, this.getKey());
		byte[] src = str.getBytes();
		// 加密，结果保存进cipherByte
		cipherByte = c.doFinal(src);
		return cipherByte;
	}

	/**
	 * 对字符串解密
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Decryptor(byte[] buff) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		// c.init(Cipher.DECRYPT_MODE, deskey);
		c.init(Cipher.DECRYPT_MODE, this.getKey());
		cipherByte = c.doFinal(buff);
		return cipherByte;
	}

	/**
	 * @param args
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static void main(String[] args) throws Exception {
		EncrypDES de = new EncrypDES();

		String msg = "http://www.baidu.com?q=123";

		System.out.println("明文是:" + msg);
		
		// 加密
		byte[] encontent = de.Encrytor(msg);
		byte[] encode = Base64.encodeBase64(encontent);
		String encodeContent = new String(encode);
		encodeContent = URLEncoder.encode(encodeContent, "UTF-8");
		System.out.println("加密后:" + encodeContent);

        // 解码
		encodeContent = URLDecoder.decode(encodeContent, "UTF-8");
		byte[] decode = Base64.decodeBase64(encodeContent.getBytes());
		byte[] decod = de.Decryptor(decode);
		String decodeContent = new String(decod);
		System.out.println("解密后:" + decodeContent);
	}
}