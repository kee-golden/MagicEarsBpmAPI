package com.magicears.bpm.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicears.bpm.exception.TokenExpiredException;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

public class JWTUtil {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String PAYLOAD = "payload";


    public static <T> String sign(T object, long maxAge) {
        try {

            HashMap<String, Object> headerClaims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(object);
            headerClaims.put(PAYLOAD, json);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create().withIssuer("auth0")
                    .withHeader(headerClaims).withExpiresAt(new Date(new Date().getTime() + maxAge))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {

            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            exception.printStackTrace();

            //Invalid Signing configuration / Couldn't convert Claims.
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static <T> String signWithoutExpires(T object) {
        try {

            HashMap<String, Object> headerClaims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(object);
            headerClaims.put(PAYLOAD, json);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create().withIssuer("auth0")
                    .withHeader(headerClaims)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {

            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            exception.printStackTrace();

            //Invalid Signing configuration / Couldn't convert Claims.
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static <T> T unSign(String token, Class<T> classT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim headerClaim = jwt.getHeaderClaim(PAYLOAD);
            String json = headerClaim.asString();
            return JSONObject.parseObject(json, classT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenExpiredException();
        }
    }


}
