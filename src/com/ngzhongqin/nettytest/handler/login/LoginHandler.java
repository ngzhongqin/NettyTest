package com.ngzhongqin.nettytest.handler.login;

import com.ngzhongqin.nettytest.framework.response.HTTPResponder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginHandler {
    public Logger logger = Logger.getLogger(LoginHandler.class);
    private HTTPResponder httpResponder;

    public LoginHandler(){
         this.httpResponder = new HTTPResponder();
    }

    public void login(ChannelHandlerContext ctx, FullHttpRequest req){
        logger.info("Method: login");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("accesstoken","123456");
        } catch (JSONException e) {
            logger.error("Fail to put key pair value in JSON object");
        }

        httpResponder.respond(ctx,req,jsonObject);


    }

    public void logout(ChannelHandlerContext ctx, HttpRequest req, String accessToken){
    }
}
