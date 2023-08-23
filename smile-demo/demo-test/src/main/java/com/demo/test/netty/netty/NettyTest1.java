package com.demo.test.netty.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;

/**
 * @author :xiezhi
 * @date : 2023/8/14
 */
public class NettyTest1 {



    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        System.out.println(NettyRuntime.availableProcessors() * 2);

    }


}
