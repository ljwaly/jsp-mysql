package com.ljw.threadpool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MessageThreadPool {

	private static Queue<String> queue = new LinkedBlockingQueue<String>(); 
	
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
			50, //线程池中保持的线程数的大小
			200, //最大
			1, 
			TimeUnit.SECONDS, 
			new LinkedBlockingQueue<Runnable>()
			) ;
	
	@Autowired
	private PoolService service;
	
	/**
	 * 系统启动，线程池开始工作
	 */
	@PostConstruct
	public void excutePoolElement(){
		System.out.println("111");
		//开启线程
		new Thread("myThreadPoolTest"){
			
			@Override
			public void run() {
				System.out.println(222);
				
				while (true) {
					int size = queue.size();
					if (size != 0) {//如果队列不为空，则进行处理
						
						String message = queue.poll();
						//System.out.println(message);//此处可以验证队列的排序
						if (message != null && message.trim().length() > 0) {
							pool.execute(new MessageTask(message));
						}
						
					} else {//如果队列中没有消息，则休息200ms，再次去查询
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							
						}
					}
				}
				
			};
			
		}.start();
	}
	
	/**
	 * 向对列外部添加消息
	 */
	public boolean addMessage(String message){
		return queue.offer(message);
	}
	
	/**
	 * 清空队列
	 */
	public void cleanQueue(){
		queue.clear();
	}
	
	/**
	 * 队列是否为空
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	class MessageTask implements Runnable{

		private String msg;
		
		public MessageTask(String msg) {
			super();
			this.msg = msg;
		}
		/**
		 *  count++;用下面的方法实现
		 * 	AtomicInteger count =  new AtomicInteger(); 
		 *  count.addAndGet( 1 );  
		 *  如果是 JDK 8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好 （ 减少乐观锁的重试次数 ） 。
		 */
		@Override
		public void run() {
			double u = ThreadLocalRandom.current().nextDouble();//用这个产生随机数要比new Random()要好，效率提高20%，防止并发	seed争夺
			
			
			service.sendMessage(msg);//因为输出在异步线程，所以输出语句是乱序的，
			
		}
		
	} 
}
