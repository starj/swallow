package com.dianping.swallow.consumer;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;



public class ConSlaveThread implements Runnable {

	private ClientBootstrap bootstrap;
	
	private InetSocketAddress slaveAddress;
	
	public ClientBootstrap getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(ClientBootstrap bootstrap) {
		this.bootstrap = bootstrap;
	}

	public void setSlaveAddress(InetSocketAddress slaveAddress) {
		this.slaveAddress = slaveAddress;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);//TODO 配置变量
			while(true){
				synchronized(bootstrap){
			   		ChannelFuture future = bootstrap.connect(slaveAddress);
			   		future.getChannel().getCloseFuture().awaitUninterruptibly();//等待channel关闭，否则一直阻塞！	  
			   	}
				System.out.println();
				Thread.sleep(1000);//TODO 配置变量
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}