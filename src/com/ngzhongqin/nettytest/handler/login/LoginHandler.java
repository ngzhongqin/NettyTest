package com.ngzhongqin.nettytest.handler.login;

import com.ngzhongqin.nettytest.framework.response.HTTPResponder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
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

            logger.info("content:"+req.content().toString(CharsetUtil.UTF_8));

        String reqString = req.content().toString(CharsetUtil.UTF_8);

        try {
            JSONObject incoming  = new JSONObject(reqString);
            String data = incoming.getString("test");
            logger.info("data:"+data);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("accesstoken","123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        httpResponder.respond(ctx,req,jsonObject);


    }

    public void logout(ChannelHandlerContext ctx, HttpRequest req, String accessToken){
    }
}
