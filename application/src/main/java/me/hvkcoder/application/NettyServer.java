package me.hvkcoder.application;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadFactory;

public class NettyServer {
	public static void main(String[] args) throws IOException {
		Properties conf = new Properties();
		conf.load(NettyServer.class.getClassLoader().getResourceAsStream("application.properties"));

		ThreadFactory bossThreadFactory = Thread.ofVirtual().name(conf.getProperty("boss.prefix", "boss-"), 0).factory();
		ThreadFactory workerThreadFactory = Thread.ofVirtual().name(conf.getProperty("worker.prefix", "worker-"), 0).factory();


		NioEventLoopGroup bossGroup = new NioEventLoopGroup(Integer.parseInt(conf.getProperty("boss.threadCount", "1")), bossThreadFactory);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(Integer.parseInt(conf.getProperty("worker.threadCount", "10")), workerThreadFactory);

		try {
			ChannelFuture channelFuture = new ServerBootstrap().group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

					}
				}).bind(Integer.parseInt(conf.getProperty("server.port", "9000"))).sync();

			System.out.println("服务端已启动，等待客户端连接.....");
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
