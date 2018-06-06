package com.fangln.dd.util;

import com.fangln.dd.entity.UserToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Component
public class UserTokenUtil {

    private static Logger log = Logger.getLogger(UserTokenUtil.class);
    @Autowired
    private CoreProperties coreProperties;
    private static CoreProperties corePropertiesImpl;

    @PostConstruct
    public void init() {
        corePropertiesImpl = coreProperties;
    }

    private static Key getKeyInstance() {
        //Zn0h3ZRPxO8W5D4H6nm&xE1E9$@OW4S7
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("Zn0h3ZRPxO8W5D4H6nm&xE1E9$@OW4S7");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static String createToken(Long uid) {

        Calendar cl=Calendar.getInstance();
        cl.add(Calendar.SECOND, corePropertiesImpl.getToken_expire());
        long validTimeMills=cl.getTimeInMillis();
        UserToken userToken = new UserToken(uid,validTimeMills);
        Map<String, Object> map = new HashMap<>();
       // map.put("user_token",userToken);
        map.put("id",String.valueOf(uid));
        map.put("token_expire",String.valueOf(validTimeMills));
        return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
       /* BASE64Encoder encoder = new BASE64Encoder();
        try {
            final String encode = encoder.encode(compact.getBytes(MiniappConstant.CHARSET));
            return encode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }

    public static UserToken parseToken(String token) {
        try {
//            final BASE64Decoder decoder = new BASE64Decoder();
//            final String s = new String(decoder.decodeBuffer(token), MiniappConstant.CHARSET);
            Map<String,Object> map = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody();
            if(map!=null){
                final String id = (String)map.get("id");
                final String expire = (String)map.get("token_expire");
                return new UserToken(Long.valueOf(id),Long.valueOf(expire));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {

        final String javaWebToken = UserTokenUtil.createToken(123L);
    }
}


