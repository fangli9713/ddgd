package com.fangln.dd.util;

import com.fangln.dd.util.http.BytesTool;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class CryptoUtil {

	/**
	 * 加密
	 * @param originalText 明文字符文本
	 * @param charset 明文字符编码
	 * @param key 秘钥
	 * @param algorithm 算法
	 * @return 16进制密文
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
	public static String encrypt(String originalText,String charset,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{
		return encrypt(originalText.getBytes(charset),key,algorithm);
    }
	
	/**
	 * 加密
	 * @param input 明文字节数组
	 * @param key 秘钥
	 * @param algorithm 算法
	 * @return 16进制密文
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
    public static String encrypt(byte[] input,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{
		return encrypt( input, 0, input.length,key,algorithm);
	}
	
    /**
     * 加密
     * @param input 明文字节数组
     * @param inputOffset 明文字节数组偏移量
     * @param inputLen 明文字节长度
     * @param key 秘钥
     * @param algorithm 算法
     * @return 16进制密文
     * @throws InvalidKeyException
     * @throws ShortBufferException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
	public static String encrypt(byte[] input, int inputOffset, int inputLen,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		ByteBuffer buf=crypt(input,  inputOffset,  inputLen,getEncryptCipher(key,algorithm));
		return byteToHexString(buf);
	}
	
	
	
	/**
	 * 解密
	 * @param ciphertext 16进制加密文本
	 * @param returnCharset 返回明文字符编码
	 * @param key 秘钥
	 * @param algorithm 使用的算法
	 * @return 明文本
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
	public static String decrypt(String ciphertext,String returnCharset,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{
		return decrypt(hexStringToByteArray(ciphertext),returnCharset,key,algorithm);
    }
	
	
	/**
	 * 解密
	 * @param input 密文字节数组
	 * @param returnCharset 返回明文字符编码
	 * @param key 秘钥
	 * @param algorithm 使用的算法
	 * @return 明文本
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IOException
	 */
    public static String decrypt(byte[] input,String returnCharset,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{
		return decrypt( input, 0, input.length,returnCharset,key,algorithm);
	}
	
    
    /**
     * 解密
     * @param input 密文字节数组
     * @param inputOffset 密文字节数组偏移量
     * @param inputLen 密文字节长度
     * @param returnCharset 返回明文字符编码
     * @param key 秘钥
     * @param algorithm 算法
     * @return 明文本
     * @throws InvalidKeyException
     * @throws ShortBufferException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
	public static String decrypt(byte[] input, int inputOffset, int inputLen, String returnCharset,Key key,String algorithm) throws InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		ByteBuffer buf=crypt(input,  inputOffset,  inputLen,getDecryptCipher(key,algorithm));
		int offset;
		int length;
		byte[] array;
		if(buf.hasArray()){
			 offset=buf.position()+buf.arrayOffset();
			 length=buf.remaining();
			 array=buf.array();
		}else{
			offset=0;
			length=buf.remaining();
			array=new byte[length];
			buf.get(array);
		}
		
		if(returnCharset==null)
			return new String(array,offset,length);
		else 
			return new String(array, offset, length, returnCharset);
		
	}
	
	
	
	/**
	 * 像OutputStream中写入秘钥
	 * @param out
	 * @param key
	 * @throws IOException
	 */
	public static void writeKey(OutputStream out,Key key) throws IOException{
		ObjectOutputStream objout=new ObjectOutputStream(out);
		objout.writeObject(key);
	}
	
	/**
	 * 保存秘钥到指定文件中
	 * @param key
	 * @param savePath
	 * @throws IOException
	 */
	public static void saveKeyToFile(Key key,String savePath) throws IOException{
		OutputStream out=new FileOutputStream(savePath);
		try{
			writeKey( out, key);
		}finally{
			if(out!=null)
		    	out.close();
		}
	}
	
	/**
	 * 从本地文件中读取秘钥
	 * @param keyFilePath
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Key readKeyFromFile(String keyFilePath) throws IOException, ClassNotFoundException{
		InputStream in=new FileInputStream(keyFilePath);
		try{
			return readKeyFromInputStream(in);
		}finally{
			if(in!=null)
				in.close();
		}
	}
	
	/**
	 * 从指定的InputStream中读取秘钥
	 * @param keyInput
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Key readKeyFromInputStream(InputStream keyInput) throws IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(keyInput);
		return (Key)in.readObject();
	}
	
	/**
	 * 从指定的URL中读取秘钥
	 * @param keyurl
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Key readKeyFromURL(URL keyurl) throws IOException, ClassNotFoundException{
		InputStream in=keyurl.openStream();
		try{
			return readKeyFromInputStream(in);
		}finally{
			if(in!=null)
				in.close();
		}
	}
	
	
	/**
	 * 得到随机DES秘钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Key createRandomDESKey() throws NoSuchAlgorithmException{
		return createRandomKey("DES");
	}
	
	/**
	 * 得到随机AES秘钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Key createRandomAESKey() throws NoSuchAlgorithmException{
		return createRandomKey("AES");
	}
	
	
	
	/**
	 * 得到随机对称秘钥
	 * @param algorithm 算法,如AES,DES
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Key createRandomKey(String algorithm) throws NoSuchAlgorithmException{
		KeyGenerator keygen=KeyGenerator.getInstance(algorithm);
		SecureRandom random=new SecureRandom();
		keygen.init(random);
		Key key=keygen.generateKey();
		return key;
	}
	
	
	/**
	 * 得到RSA非对称秘钥
	 * @return key[0]=PublicKey ,key[1]=PrivateKey
	 * @throws NoSuchAlgorithmException
	 */
	public static Key[] createRSAKey() throws NoSuchAlgorithmException{
		Key[] keys=new Key[2];
		KeyPairGenerator pairgen=KeyPairGenerator.getInstance("RSA");
		SecureRandom random=new SecureRandom();
		pairgen.initialize(512, random);
		KeyPair keyPair=pairgen.generateKeyPair();
		keys[0]=keyPair.getPublic();
		keys[1]=keyPair.getPrivate();
		return keys;
	}
	
	/**
	 * 得到加密的Cipher
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static Cipher getEncryptCipher(Key key,String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
    	Cipher ciper=Cipher.getInstance(algorithm);
    	ciper.init(Cipher.ENCRYPT_MODE, key);
    	return ciper;
    }
	
	/**
	 * 得到解密的Cipher
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static Cipher getDecryptCipher(Key key,String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
    	Cipher ciper=Cipher.getInstance(algorithm);
    	ciper.init(Cipher.DECRYPT_MODE, key);
    	return ciper;
    }
	
	
	/**
	 * 加密或者解密，根据cipher决定
	 * @param input
	 * @param inputOffset
	 * @param inputLen
	 * @param cipher
	 * @return
	 * @throws IOException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static ByteBuffer crypt(byte[] input, int inputOffset, int inputLen,Cipher cipher) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
		   int blockSize= cipher.getBlockSize();
		   int outputSize=cipher.getOutputSize(blockSize);
		   
		   int numOfBlocks=inputLen/blockSize;
		   int lengthOfLastPart = inputLen%blockSize;
		   
		   byte[] outBytes=new byte[outputSize*(numOfBlocks+1)];
		   int outputOffset=0;
		   final int outOffset=outputOffset;
		   if(numOfBlocks>0){
		       int h;
			   for (int i = 0; i < numOfBlocks; i++) {
				    //update之前先判断容量是否足够
				    outputSize=cipher.getOutputSize(blockSize);
				    if(outBytes.length-outputOffset<outputSize){
				    	 //剩余容量不足
				    	 int newLength=(outBytes.length<<1)+outputSize;
				    	 byte[] newByes=new byte[newLength];
				    	 System.arraycopy(outBytes, outOffset, newByes, outOffset, outputOffset);
				    	 outBytes=newByes;
				    }
				    
					h =cipher.update(input, inputOffset, blockSize, outBytes, outputOffset);
					inputOffset += blockSize;
					outputOffset += h;
			   }
		   }
		   //doFinal之前先判断容量是否足够
		    outputSize=cipher.getOutputSize(blockSize);
		    if(outBytes.length-outputOffset<outputSize){
		    	 //剩余容量不足
		    	 int newLength=(outBytes.length<<1)+outputSize;
		    	 byte[] newByes=new byte[newLength];
		    	 System.arraycopy(outBytes, outOffset, newByes, outOffset, outputOffset);
		    	 outBytes=newByes;
		    }
		   
		   if(lengthOfLastPart>0){
			   outputOffset+=cipher.doFinal(input, inputOffset, lengthOfLastPart, outBytes, outputOffset);
		   }else{
			   outputOffset+=cipher.doFinal(outBytes, outputOffset);
		   }
		   
		   return ByteBuffer.wrap(outBytes, outOffset, outputOffset);
			   
	}

	/**
	 * 加密或者解密，根据cipher决定
	 * @param in
	 * @param out
	 * @param cipher
	 * @throws IOException
	 * @throws ShortBufferException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static void crypt(InputStream in,OutputStream out,Cipher cipher) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
		   int blockSize= cipher.getBlockSize();
		   int outputSize=cipher.getOutputSize(blockSize);
		   byte[] inBytes=new byte[blockSize];
		   byte[] outBytes=new byte[outputSize];
		   int inLength=0;
		   int outLength=0;
		   int b;
		   while((b=in.read())!=-1){
			   inBytes[inLength++]=(byte)b ;
			   if(inLength==blockSize){
				   outLength=cipher.update(inBytes, 0, blockSize, outBytes);
				   out.write(outBytes, 0, outLength);
				   inLength=0;
			   }
		   }
		   
		   if(inLength>0)
			   outBytes=cipher.doFinal(inBytes, 0, inLength);
		   else
			   outBytes=cipher.doFinal();
		   
		   if(outBytes!=null){
			   out.write(outBytes);
		   }
		   
	}
	
	
	public static byte[] getKeyBytes(Key key) throws IOException{
		ByteArrayOutputStream out=new ByteArrayOutputStream(512);
		writeKey(out, key);
		byte[] bs=out.toByteArray();
		return bs;
	}
	
	public static String getKeyJavaCode(Key key) throws IOException{
		ByteArrayOutputStream out=new ByteArrayOutputStream(512);
		writeKey(out, key);
		byte[] bs=out.toByteArray();
		return getKeyJavaCode(bs);
	}
	
	public static String getKeyJavaCode(byte[] bs) throws IOException{
		StringBuilder buf=new StringBuilder(1024);
		buf.append("byte[] keyBytes=new byte[]{");
		for(int i=0;i<bs.length;i++){
			if(i!=0)
				buf.append(',');
			buf.append("(byte)").append(bs[i]&0xff);
		}
		
		buf.append("};");

		return buf.toString();
	}
	
	/**
	 * 
	 * @param privateKey
	 * @param wrappedKey
	 * @param wrappedKeyAlgorithm
	 * @return 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static Key unWrapKey(Key privateKey,byte[] wrappedKey,String wrappedKeyAlgorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.UNWRAP_MODE, privateKey);
		return cipher.unwrap(wrappedKey, wrappedKeyAlgorithm, Cipher.SECRET_KEY);
	}
	
	/**
	 * 
	 * @param publicKey
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] wrapKey(Key publicKey,Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.WRAP_MODE, publicKey);
		return cipher.wrap(key);
	}
	
	public static boolean isValidAlgorithm(String algorithm){
		try {
			Cipher cipher=Cipher.getInstance(algorithm);
			return cipher!=null;
		} catch (NoSuchAlgorithmException e) {
			return false;
		} catch (NoSuchPaddingException e) {
			return false;
		}
	}
	

	
	/**
	 * origin 以UTF-8转换字节然后返回16进制数据
	 * @param origin 
	 * @return
	 */
	public static String MD5Encode(String origin) {
		return MD5EncodeString(origin,"UTF-8"); 
	}
	
	/**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果 
     */
    public static byte[] MD5Encode(String origin,String charset) {
    	byte[] data;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            data=md.digest(charset==null?origin.getBytes():origin.getBytes(charset));
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
        return data;
    }
    
    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果 16进制数据
     */
    public static String MD5EncodeString(String origin,String charset) {
    	byte[] data=MD5Encode(origin, charset);
    	return byteArrayToHexString(data,0,data.length);
    }
    
    private static String byteArrayToHexString(byte buf[], int offset, int length) {
		StringBuffer strbuf = new StringBuffer(length<<1);
		int i;
		int end=length + offset;
		for (i = offset; i < end; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append('0');
			strbuf.append(Integer.toString((buf[i] & 0xff), 16));
		}
		return strbuf.toString();
	}
  
	
	/**
	 * 字节数组转换成十六进制字符串,中间没有空格
	 * @param data
	 * @return
	 */
	public static String byteToHexString(ByteBuffer data){
		StringBuilder inputBuffer = new StringBuilder();
		String sTemp;
		while(data.hasRemaining()){
			byte cChar=data.get();
		    sTemp = Integer.toHexString(cChar&0xff);
		    if(sTemp.length()==1){
		    	inputBuffer.append('0').append(sTemp);
		    } else 	
		        inputBuffer.append(sTemp);

		}
		return inputBuffer.toString();
	}
	
	public static byte[] hexStringToByteArray(String hex) {
		if (hex == null)
			return null;
		int length= hex.length();
		int byteLength = length>>1;
		byte[] result = new byte[byteLength];
		char[] buffer = new char[2];
		int i = 0;
		for (int pos = 0; pos < length; pos += 2, i++) {
			buffer[0] = hex.charAt(pos);
			buffer[1] = hex.charAt(pos + 1);
			result[i] = (byte) parseInt(buffer, 16);
		}
		return result;
	}
	
	/**
	 * 使用第二个参数指定的基数，将字符数组参数解析为有符号的整数。 除了第一个字符可以是用来表示负值的 ASCII 减号 '-'
	 * ('\u002D’)外，字符串中的字符必须都是指定基数的数字（通过 Character.digit(char, int)
	 * 是否返回一个负值确定）。返回得到的整数值。
	 * 
	 * @param ch
	 *            字符数组
	 * @param radix
	 *            解析ch 时使用的基数
	 * @return
	 * @throws NumberFormatException
	 */
	public static int parseInt(char[] ch, int radix)
			throws NumberFormatException {
		if (ch == null) {
			throw new NumberFormatException("null");
		}

		if (radix < Character.MIN_RADIX) {
			throw new NumberFormatException("radix " + radix
					+ " less than Character.MIN_RADIX");
		}

		if (radix > Character.MAX_RADIX) {
			throw new NumberFormatException("radix " + radix
					+ " greater than Character.MAX_RADIX");
		}

		int result = 0;
		boolean negative = false;
		int i = 0, max = ch.length;
		int limit;
		int multmin;
		int digit;

		if (max > 0) {
			if (ch[0] == '-') {
				negative = true;
				limit = Integer.MIN_VALUE;
				i++;
			} else {
				limit = -Integer.MAX_VALUE;
			}
			multmin = limit / radix;
			if (i < max) {
				digit = Character.digit(ch[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("For input string: \""
							+ new String(ch) + "\"");
				} else {
					result = -digit;
				}
			}
			while (i < max) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit(ch[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("For input string: \""
							+ new String(ch) + "\"");
				}
				if (result < multmin) {
					throw new NumberFormatException("For input string: \""
							+ new String(ch) + "\"");
				}
				result *= radix;
				if (result < limit + digit) {
					throw new NumberFormatException("For input string: \""
							+ new String(ch) + "\"");
				}
				result -= digit;
			}
		} else {
			throw new NumberFormatException("For input string: \""
					+ new String(ch) + "\"");
		}
		if (negative) {
			if (i > 1) {
				return result;
			} else { /* Only got "-" */
				throw new NumberFormatException("For input string: \""
						+ new String(ch) + "\"");
			}
		} else {
			return -result;
		}
	}
	
	
	public static String  beckEncrypt(String originalText,String key){
		try{
			
			String charset="UTF-8";
			StringBuilder sbr=new StringBuilder(originalText.length()+key.length()+1);
			sbr.append(originalText).append(',').append(key);
			
			String a=sbr.toString();
		
			//System.out.println(a);
			byte[] b=MD5Encode(a, charset);
			//System.out.println(BytesTool.byteArrayToHexString(b));
			byte[] c=originalText.getBytes(charset);
			byte[] d=new byte[c.length+b.length];
			System.arraycopy(c, 0, d, 0, c.length);
			System.arraycopy(b, 0, d, c.length, b.length);
			
			//System.out.println(BytesTool.byteArrayToHexString(d));
			byte[] e=MD5Encode(key, charset);
			//System.out.println(BytesTool.byteArrayToHexString(e));
			
			byte f=0x00;
			for(int i=0;i<e.length;i++){
				if(i==0){
					f=e[i];
				}else{
					f=(byte)(f^e[i]);
				}
			}
			
			//System.out.println(BytesTool.byteArrayToHexString(new byte[]{f}));
			
			for(int i=0;i<d.length;i++){
				d[i]=(byte)(d[i]^f);
			}
			
			//System.out.println(BytesTool.byteArrayToHexString(d));
			byte[] h1=Base64.getUrlEncoder().encode(d);
			String h=new String(h1,charset);
			//System.out.println(h);
			String a12=RandomStringGenerator.getDefaultInstance().getNewString(5);
			sbr=new StringBuilder(h.length()+a12.length());
			return sbr.append(a12.substring(0, 2)).append(h).append(a12.substring(2, a12.length())).toString();
		}catch(Exception ex){
			if(ex instanceof RuntimeException){
				throw (RuntimeException)ex;
			}else{
				throw new RuntimeException(ex.getMessage(),ex);
			}
		}
		
	}
	
	
	public static String  beckDecrypt(String encryptText,String key){
		try{
			String charset="UTF-8";
			String h=encryptText.substring(2,encryptText.length()-3);

			byte[] g=Base64.getUrlDecoder().decode(h.getBytes(charset));
		
			byte[] e=MD5Encode(key, charset);
			byte f=0x00;
			for(int i=0;i<e.length;i++){
				if(i==0){
					f=e[i];
				}else{
					f=(byte)(f^e[i]);
				}
			}
			
			for(int i=0;i<g.length;i++){
				g[i]=(byte)(g[i]^f);
			}
			
			String x=new String(g,0,g.length-16,charset);
			
			StringBuilder sbr=new StringBuilder(x.length()+key.length()+1);
			sbr.append(x).append(',').append(key);
			String a=sbr.toString();
			
			byte[] b=MD5Encode(a, charset);
			
			boolean eq=true;
			for(int i=0,j=g.length-16;i<b.length;i++,j++){
				if(b[i]!=g[j]){
					eq=false;
					break;
				}
			}
			
			if(eq){
				return x;
			}else{
				throw new RuntimeException("invalid data!");
			}
		}catch(Exception ex){
			if(ex instanceof RuntimeException){
				throw (RuntimeException)ex;
			}else{
				throw new RuntimeException(ex.getMessage(),ex);
			}
		}	
		
	}
	
	public static String beckEncrypt(String originalText,String siginKey,Crypto crypto){
        try{
			String charset="UTF-8";
			StringBuilder sbr=new StringBuilder(originalText.length()+siginKey.length()+1);
			sbr.append(originalText).append(',').append(siginKey);
			String a=sbr.toString();
			byte[] b=MD5Encode(a, charset);
			byte[] c=originalText.getBytes(charset);
			byte[] d=new byte[c.length+b.length];
			System.arraycopy(c, 0, d, 0, c.length);
			System.arraycopy(b, 0, d, c.length, b.length);
			return BytesTool.byteArrayToHexString(crypto.encrypt(d));
		}catch(Exception ex){
			if(ex instanceof RuntimeException){
				throw (RuntimeException)ex;
			}else{
				throw new RuntimeException(ex.getMessage(),ex);
			}
		}
	}
	
	public static String beckDecrypt(String encryptText,String siginKey,Crypto crypto){
		try{
			String charset="UTF-8";
			byte[] g=crypto.decrypt(BytesTool.hexStringToByteArray(encryptText));
			String x=new String(g,0,g.length-16,charset);
			StringBuilder sbr=new StringBuilder(x.length()+siginKey.length()+1);
			sbr.append(x).append(',').append(siginKey);
			String a=sbr.toString();
			
			byte[] b=MD5Encode(a, charset);
			
			boolean eq=true;
			for(int i=0,j=g.length-16;i<b.length;i++,j++){
				if(b[i]!=g[j]){
					eq=false;
					break;
				}
			}
			
			if(eq){
				return x;
			}else{
				throw new RuntimeException("sign error!");
			}
		}catch(Exception ex){
			if(ex instanceof RuntimeException){
				throw (RuntimeException)ex;
			}else{
				throw new RuntimeException(ex.getMessage(),ex);
			}
		}	
	}
	
	/**
	 * 加密
	 * @param originalText 明文
	 * @param key
	 * @return 密文
	 */
	public static String encryptCompress(String originalText,Key key) {
		try{
			byte[] input=originalText.getBytes("UTF-8");
			ByteBuffer buf= CryptoUtil.crypt(input, 0, input.length, CryptoUtil.getEncryptCipher(key, key.getAlgorithm()));
			int offset;
			int length;
			byte[] array;
			if(buf.hasArray()){
				 offset=buf.position()+buf.arrayOffset();
				 length=buf.remaining();
				 array=buf.array();
			}else{
				offset=0;
				length=buf.remaining();
				array=new byte[length];
				buf.get(array);
			}
			return BytesTool.compressBytes(array, offset, length);
			
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(),ex);
		}
	}
	
	/**
	 * 解密
	 * @param encrypttext 密文
	 * @param key
	 * @return 明文
	 */
	public static String decryptCompress(String encrypttext,Key key) {
		try{
			byte[] input=BytesTool.unCompress(encrypttext);
			ByteBuffer buf= CryptoUtil.crypt(input, 0, input.length, CryptoUtil.getDecryptCipher(key, key.getAlgorithm()));
			int offset;
			int length;
			byte[] array;
			if(buf.hasArray()){
				 offset=buf.position()+buf.arrayOffset();
				 length=buf.remaining();
				 array=buf.array();
			}else{
				offset=0;
				length=buf.remaining();
				array=new byte[length];
				buf.get(array);
			}
			return new String(array, offset, length,"UTF-8");
			
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(),ex);
		}
	}
	
	
//	public static void main(String[] args){
//		JSerpentCrypto t=new JSerpentCrypto();
//		byte[] key1=t.generateRandomKey();
//		t.setKey(key1);
//		String source="1233456asdfsdgsdgdger34sefsrturtw1!!#$#$%^&DfgD@#$";
//		String siginKey="1234567890";
//		System.out.println("s:"+source);
//		String e=beckEncrypt(source,siginKey,t);
//		System.out.println("e:"+e);
//		String rs=beckDecrypt(e,"1234567890",t);
//		System.out.println("rs:"+rs);
//		System.out.println(rs.equals(source));
//	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException{
    //  System.out.println(getKeyJavaCode(new JSerpentCrypto().generateRandomKey()));	
      System.out.println(getKeyJavaCode(createRandomAESKey()));	
//    String originalText="{\"cmd\":\"user.updateNickName\",\"msgId\":\"100010\",\"timeMills\":\"1479872535511\", \"data\":{\"user_id\":\"1\",\"nick_name\":\"张三\"}}";
//    String v1=beckEncrypt(originalText,"ab12bcdfff3098cac87fe4e32cbad098");
//    String v2=beckDecrypt(v1,"ab12bcdfff3098cac87fe4e32cbad098");
//    System.out.println(originalText);
//    System.out.println(v1);
//    System.out.println(v2);
//    System.out.println(originalText.equals(v2));
   }
}
