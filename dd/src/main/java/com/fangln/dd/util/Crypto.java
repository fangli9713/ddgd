package com.fangln.dd.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 加密解密对象
 * @author HuangFu
 *
 */
public interface Crypto {
 
	 /**
	  * 对明文进行加密
	  * @param input 需要加密的数组
	  * @return 加密后的字节数组
	  */
	 public byte[] encrypt(byte[] input);
	 
	 /**
	  * 对明文进行加密
	  * @param input 需要加密的数组
	  * @param inputOffset 需要加密的偏移量
	  * @return 加密后的字节数组
	  */
	 public byte[] encrypt(byte[] input, int inputOffset);
	 
	 /**
	  * 对明文进行加密
	  * @param input 需要加密的数组
	  * @param inputOffset 需要加密的偏移量
	  * @param inputLen 需要加密的长度
	  * @return 加密后的字节数组
	  */
	 public byte[] encrypt(byte[] input, int inputOffset, int inputLen);
	 
	 /**
	  * 对明文进行加密
	  * @param input 需要加密的数组
	  * @param inputOffset 需要加密数组偏移量
	  * @param inputLen 需要加密的长度
	  * @param output 加密后保存结果的缓冲区
	  * @param outputOffset 加密后存储结果处的偏移量 
	  * @return 加密后存储的字节数长度
	  */
	 public int encrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset);
	 
	 /**
	  * 对明文进行加密,直到读到流末尾(-1)
	  * @param input 明文输入流
	  * @param output 密文输出流
	  */
	 public void encrypt(InputStream input, OutputStream output) throws IOException;
	 
	 /**
	  * 对密文进行解密
	  * @param input 密文
	  * @return 解密后的明文
	  */
	 public byte[] decrypt(byte[] input);
	 
	 /**
	  * 对密文进行解密
	  * @param input 密文
	  * @param inputOffset 密文数组偏移量
	  * @return 解密后的明文
	  */
	 public byte[] decrypt(byte[] input, int inputOffset);
	 
	 /**
	  * 对密文进行解密
	  * @param input 密文
	  * @param inputOffset 密文数组偏移量
	  * @param inputLen 密文数组长度
	  * @return 解密后的明文
	  */
	 public byte[] decrypt(byte[] input, int inputOffset, int inputLen);
	 
	 /**
	  * 对密文进行解密
	  * @param input 密文
	  * @param inputOffset 密文数组偏移量
	  * @param inputLen 密文数组长度
	  * @param output 明文缓冲区
	  * @param outputOffset 明文缓冲区偏移量
	  * @return 解密后的明文存储的字节数长度
	  */
	 public int decrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset);
	 
	 /**
	  * 对密文进行解密,直到读到流末尾(-1)
	  * @param input 密文输入流
	  * @param output 明文输出流
	  */
	 public void decrypt(InputStream input, OutputStream output) throws IOException;
	 
	 /**
	  * 加密成字符串
	  * @param originalText
	  * @param charset
	  * @return
	  */
	 public String encrypt(String originalText, String charset);
	 
	 /**
	  * 解密已加密的字符串
	  * @param ciphertext
	  * @param returnCharset
	  * @return
	  */
	 public String decrypt(String ciphertext, String returnCharset);
	
}
