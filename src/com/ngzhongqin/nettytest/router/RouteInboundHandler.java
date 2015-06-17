package com.ngzhongqin.nettytest.router;

import com.ngzhongqin.nettytest.handler.login.LoginHandler;
import com.ngzhongqin.nettytest.handler.signup.SignUpHandler;
import com.sun.deploy.net.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;

import javax.rmi.CORBA.Util;
import java.nio.charset.Charset;


public class RouteInboundHandler extends ChannelInboundHandlerAdapter {
    private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {


        //System.out.println("msg: " + msg.toString());

        if(msg instanceof FullHttpRequest){
            FullHttpRequest request = (FullHttpRequest) msg;
            System.out.println("FullHttpRequest: " + request.toString());

            System.out.println("content: "+request.content().toString(CharsetUtil.UTF_8));

//            System.out.flush();
        }

//        if (msg instanceof HttpContent) {
//            HttpContent content = (HttpContent) msg;

       //     System.out.println("HttpContent: " + content.content().toString(CharsetUtil.UTF_8));

//            System.out.flush();
//
//            if (content instanceof LastHttpContent) {
//                System.out.println("} END OF CONTENT");
//            }
//        }

//        if (msg instanceof FullHttpRequest) {
//            FullHttpRequest req = (FullHttpRequest) msg;
//
//            System.out.println("req.getMethod().name(); "+req.getMethod().name());
//
//            UriPathHelper uriPathHelper = new UriPathHelper();
//            UriPath uriPath = uriPathHelper.getUriPath(req.getUri());
//
//            switch (uriPath){
//                case INVALID:
//                    break;
//                case LOGIN:
//                    LoginHandler loginHandler = new LoginHandler();
//                    loginHandler.login(ctx,req,null,null);
//                    break;
//                case LOGOUT:
//                    LoginHandler loginHandler1 = new LoginHandler();
//                    loginHandler1.logout(ctx,req,null);
//                    break;
//                case SIGNUP:
//                    SignUpHandler signUpHandler = new SignUpHandler();
//                    signUpHandler.signUp(ctx,req);
//                    break;
//                default:
//                    break;
//            }

//            This section is not in use

/*
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
*/
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}