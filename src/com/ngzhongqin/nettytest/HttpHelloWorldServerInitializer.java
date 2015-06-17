package com.ngzhongqin.nettytest;

import com.ngzhongqin.nettytest.router.RouteInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpHelloWorldServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
//        p.addLast(new HttpServerCodec());

//        p.addLast("decoder", new HttpRequestDecoder());
//        p.addLast("encoder", new HttpResponseEncoder());


//        p.addLast(new HttpRequestDecoder());
//        // Uncomment the following line if you don't want to handle HttpChunks.
//        //p.addLast(new HttpObjectAggregator(1048576));
//        p.addLast(new HttpResponseEncoder());

        p.addLast("codec",new HttpServerCodec());
        p.addLast("aggregator", new HttpObjectAggregator(512 * 1024));

        p.addLast(new RouteInboundHandler());
    }
}
