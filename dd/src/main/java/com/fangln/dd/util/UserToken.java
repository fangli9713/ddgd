package com.fangln.dd.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
public class UserToken {

    private static Logger log = Logger.getLogger(UserToken.class);

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
        return Jwts.builder().setPayload(uid.toString()).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static Long parseToken2Id(String token) {
        try {
            String jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parsePlaintextJws(jwt).getBody();
            if(jwtClaims!=null){
                return Long.valueOf(jwtClaims);
            }
            throw new RuntimeException("parse failed");
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {

        final String javaWebToken = UserToken.createToken(123L);
        System.out.println("token="+javaWebToken);
        final Long stringObjectMap = parseToken2Id(javaWebToken);
        System.out.println("stringObjectMap= "+stringObjectMap);
    }
}


