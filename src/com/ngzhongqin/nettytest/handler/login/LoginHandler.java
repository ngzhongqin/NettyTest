package com.ngzhongqin.nettytest.handler.login;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.log4j.Logger;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class LoginHandler {
    public Logger logger = Logger.getLogger(LoginHandler.class);

    public LoginHandler(){

    }

    public void login(ChannelHandlerContext ctx, FullHttpRequest req, String username, String password){
        logger.info("Method: login");


        byte[] CONTENT = { 'L', 'o', 'g', 'i', 'n', };

        if (HttpHeaders.is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }
        boolean keepAlive = HttpHeaders.isKeepAlive(req);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));




        //TODO: To remove the below line in SIT, UAT then Production
        response.headers().set("Access-Control-Allow-Origin", "*");


        response.headers().set("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response.headers().set(CONTENT_TYPE, "text/plain");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            ctx.write(response);
        }
    }

    public void logout(ChannelHandlerContext ctx, HttpRequest req, String accessToken){

        byte[] CONTENT = { 'L', 'o', 'g', 'o', 'f', 'f' };

        if (HttpHeaders.is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }
        boolean keepAlive = HttpHeaders.isKeepAlive(req);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));
        response.headers().set(CONTENT_TYPE, "text/plain");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            ctx.write(response);
        }
    }
}
