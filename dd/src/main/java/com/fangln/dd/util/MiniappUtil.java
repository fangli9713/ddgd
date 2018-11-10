package com.fangln.dd.util;

import com.alibaba.fastjson.JSON;
import com.fangln.dd.entity.User;
import com.fangln.dd.entity.UserToken;
import com.fangln.dd.service.user.UserService;
import com.fangln.dd.util.http.HttpUtil;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

@Component
public class MiniappUtil {

	private static Logger logger = Logger.getLogger(MiniappUtil.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CoreProperties coreProperties;

	private static UserService userServiceImpl;
	private static CoreProperties corePropertiesImpl;

	@PostConstruct
	public void init() {
		userServiceImpl = userService;
		corePropertiesImpl = coreProperties;
	}




	
	//小程序用户后台登录
	public  static User getUser(HttpServletRequest request , HttpServletResponse response){
		Map<?,?> requestMap = getRequestMap(request);
		String rawData = null,signature = null,encryptedData = null,code = null,iv = null,token = null;
		if(requestMap!=null){
			rawData = (String)requestMap.get("rawData");
			signature = (String)requestMap.get("signature");
			encryptedData = (String)requestMap.get("encryptedData");
			code = (String)requestMap.get("code");
			iv = (String)requestMap.get("iv");
			token = (String)requestMap.get("token");
		}
		
		if(!StringUtils.isEmpty(token)){
			final String PRIVATE_URI = "/mini/private/";
			String contextPath = request.getContextPath();
			String zo = contextPath + PRIVATE_URI;
			String requstUri = request.getRequestURI();
			if (requstUri.startsWith(zo)) {
				//解析token
				final UserToken userToken = UserTokenUtil.parseToken(token);
				if(userToken!=null){
					List<User> userList=userServiceImpl.selectUserById(userToken.getUser_id());
					if(!CollectionUtils.isEmpty(userList)){
						return userList.get(0);
					}
				}
			}
				
		}
		//1：获取session_key
		String sessionKey = null,purePhoneNumber = null,openid = null;
		Map<?,?> userMap = null;
		User user = null;
		try {
			if(!StringUtils.isEmpty(code)){
				String appId = corePropertiesImpl.getApp_id();
				String secret = corePropertiesImpl.getApp_secret();
				String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";  //请求地址 
				String sessionJson;
				try {
					sessionJson = HttpUtil.doJsonRquest(requestUrl, null, null);
					Map<?,?> sessionMap = (Map)JSON.parse(sessionJson);
					openid = (String)sessionMap.get("openid");
					sessionKey = (String)sessionMap.get("session_key");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!StringUtils.isEmpty(encryptedData)&&!StringUtils.isEmpty(iv) && !StringUtils.isEmpty(sessionKey)){
				if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
					Security.addProvider(new BouncyCastleProvider());
				}
				final Base64.Decoder decoder = Base64.getDecoder();
				byte[] keyByte = decoder.decode(sessionKey);
				byte[] ivByte = decoder.decode(iv);
				byte[] dataByte = decoder.decode(encryptedData);
				// 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
				int base = 16;
				if (keyByte.length % base != 0) {
					int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
					byte[] temp = new byte[groups * base];
					Arrays.fill(temp, (byte) 0);
					System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
					keyByte = temp;
				}
				
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
				SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
				AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
				parameters.init(new IvParameterSpec(ivByte));
				cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
				byte[] resultByte = cipher.doFinal(dataByte);
				if (null != resultByte && resultByte.length > 0) {
					String userJson = new String(resultByte, MiniappConstant.CHARSET);
					userMap = (Map)JSON.parse(userJson);
					purePhoneNumber = (String)userMap.get("purePhoneNumber");
				}
			}
			//根据openid 来判断用户是否已经注册了
			if(openid!=null){
				Map<String, Object> paramMap = new HashMap<String,Object>();
				if(purePhoneNumber!=null){
					paramMap.put("phone", purePhoneNumber);
				}else{
					paramMap.put("openid", openid);
				}
				List<User> userList=userServiceImpl.selectUsers(paramMap , 0);
				//用户已经存在
				if(!CollectionUtils.isEmpty(userList)){
					user = userList.get(0);
					//判断用户的信息是否需要更新  判断逻辑：之前没手机号，现在有了，之前没openid现在有了
					User updateUser = new User();
					BeanUtils.copyProperties(user, updateUser);
					boolean update = false;
					if(StringUtils.isEmpty(user.getPhone()) &&!StringUtils.isEmpty(purePhoneNumber)){
						updateUser.setPhone(purePhoneNumber);
						if(!update){update = true;}
					}
					if(StringUtils.isEmpty(user.getOpenid())){
						updateUser.setOpenid(openid);
						if(!update){update = true;}
					}
					if(userMap!=null){
						if(StringUtils.isEmpty(user.getNickname()) && !StringUtils.isEmpty(userMap.get("nickName"))){
							updateUser.setNickname((String)userMap.get("nickName"));
							if(!update){update = true;}
						}
						if(StringUtils.isEmpty(user.getHead_img_url()) && !StringUtils.isEmpty(userMap.get("avatarUrl"))){
							updateUser.setHead_img_url((String)userMap.get("avatarUrl"));
							if(!update){update = true;}
						}
						if(StringUtils.isEmpty(user.getCity()) && !StringUtils.isEmpty(userMap.get("city"))){
							updateUser.setCity((String)userMap.get("city"));
							if(!update){update = true;}
						}
						if(StringUtils.isEmpty(user.getProvince()) && !StringUtils.isEmpty(userMap.get("province"))){
							updateUser.setProvince((String)userMap.get("province"));
						}
						if(StringUtils.isEmpty(user.getCountry()) && !StringUtils.isEmpty(userMap.get("country"))){
							updateUser.setCountry((String)userMap.get("country"));
							if(!update){update = true;}
						}
						Object sex = userMap.get("gender");
						if(StringUtils.isEmpty(user.getSex()) && !StringUtils.isEmpty(sex)){
							if(sex.toString().equals("1")){
								updateUser.setSex(MiniappConstant.SEX_MALE);
								if(!update){update = true;}
							}else if(sex.toString().equals("2")){
								updateUser.setSex(MiniappConstant.SEX_FEMALE);
								if(!update){update = true;}
							}
						}
					}
					
					if(update){
						userServiceImpl.updateUser(updateUser);
					}
				}else{
					if(!StringUtils.isEmpty(purePhoneNumber)){
						paramMap.clear();
						paramMap.put("miniapp_openid", openid);
						List<User> userList1=userServiceImpl.selectUsers(paramMap , 0);
						//更新用户的手机号
						if(!CollectionUtils.isEmpty(userList1)){
							User updateUser = new User();
							BeanUtils.copyProperties(userList1.get(0), updateUser);
							updateUser.setPhone(purePhoneNumber);
							userServiceImpl.updateUser(updateUser);
							return updateUser;
						}
						
					}
					
				    user=new User();
					user.setReg_time(new Date());
					user.setStatus(MiniappConstant.USER_NORMAL);
					
					user.setOpenid(openid);
					if(purePhoneNumber!=null){
						user.setPhone(purePhoneNumber);
					}
					userServiceImpl.inertUser(user);
				}
			}
			if(user!=null){
				//cookie 存储用户信息(或者其他方式缓存用户信息)
//				Key skey=ServiceUtil.getServiceContext().getKeyManager().getAppAccesssTokenKey();
//				Calendar cl=Calendar.getInstance();
//				cl.add(Calendar.SECOND, 7200);
//				byte[] d=(openid+","+cl.getTimeInMillis()).getBytes(WebConstants.WEB_CHARSET);
//				String openidTime=CryptoUtil.encrypt(d, skey, skey.getAlgorithm());
//				 //存放到cookie中
//		    	 WebUtil.storeCookie(response, WebConstants.WEIXIN_OPENID_COOKIE_KEY, openidTime, WebConstants.OPENID_COOKIE_STORE_TIME, null, "/");
			}else{
				writeString(response, null,MiniappConstant.NOT_FOUND_USER,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	/**
	 * 获取用户的openid
	 * 获取方式：1：cookie；2：通过小程序的code
	 * @return
	 */
	public  void getOpenid(HttpServletRequest request,String code,String openid,String sessionKey){}
	
	/**
	 * 返回数据至小程序前端
	 * @throws IOException 
	 */
	public static void writeEmpty(HttpServletResponse response){
		writeString(response, null, MiniappConstant.RESULT_EMPTY, MiniappConstant.RESULT_EMPTY_MSG);
	}

	public static void writeSuccess(HttpServletResponse response,Object obj){
		writeString(response, obj, MiniappConstant.OP_SUCCESS, MiniappConstant.OP_SUCCESS_MSG);
	}

	public static void writeString(HttpServletResponse response,Object obj,int responseCode,String responseMsg){
		MiniappResponse miniappResponse = new MiniappResponse();
		miniappResponse.setResp_code(responseCode);
		miniappResponse.setResp_des(responseMsg);
		miniappResponse.setData(obj);
		try {
			String json = JSON.toJSONString(miniappResponse);
			//WebUtil.printFinshJson(response, gson.toJson(miniappResponse));
			response.setCharacterEncoding(MiniappConstant.CHARSET);
//			response.setContentType("application/json");
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Pragma","No-cache");

			/**
			 * 测试用 解决前端跨域请求
			 */
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter w=response.getWriter();
			w.write(json);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回数据至小程序前端
	 * @throws IOException 
	 */
	public static String createSuccess(HttpServletResponse response,Object obj){
		return createString(response, obj, MiniappConstant.OP_SUCCESS, MiniappConstant.OP_SUCCESS_MSG);

	}

	public static String createString(HttpServletResponse response,Object obj,int responseCode,String responseMsg){
		MiniappResponse miniappResponse = new MiniappResponse();
		miniappResponse.setResp_code(responseCode);
		miniappResponse.setResp_des(responseMsg);
		miniappResponse.setData(obj);
		return JSON.toJSONString(miniappResponse);
	}
	
	public static Map<?,?> getRequestMap(HttpServletRequest request){
		Object requestMap = request.getAttribute("requestMap");
		if(requestMap!=null){
			return (Map<?,?>)requestMap;
		}
        String s = null;
        try {
          byte[] bytes = new byte[1024 * 1024];  
            InputStream is = request.getInputStream();  
 
            int nRead = 1;  
            int nTotalRead = 0;  
            while (nRead > 0) {  
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
                if (nRead > 0)  
                    nTotalRead = nTotalRead + nRead;  
            }  
            s = new String(bytes, 0, nTotalRead, "utf-8");  
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<?,?> map = null;
       if(s!=null){
    	  try {
    		  map = (Map)JSON.parse(s);
		} catch (Exception e) {
			  logger.warn("小程序请求json格式有误:"+s);
		}
       }
       return map;
    }
}
