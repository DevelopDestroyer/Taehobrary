/*
	- application.java에서 다음과 같은 설정을 해준다.
	- 1. 쓰레드 풀로 동작해야 할 경우 ThreadPoolTaskScheduler
	- 2. 단일 쓰레드로 동작시킬 경우 ConcurrentTaskScheduler로 빈을 등록한다.




*/

package com.test.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class OpenholoCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenholoCrawlerApplication.class, args);
	}
	
	@Bean 
	public TaskScheduler taskScheduler() { 
		return new ConcurrentTaskScheduler();

	} 
	/*
	@Bean 
	public TaskScheduler taskScheduler() { 
	
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler(); 
		taskScheduler.setPoolSize(10); 
		return taskScheduler; 
		
	} 
	*/
}



/*

	crontab을 구성할 java파일에서는 다음과 같이 각 패턴별로 구현할수 있다.


*/

@Component
public class CronTable { 	
	
    @Value("${crawler.repository.path.github}")
    String REPOSITORY_PATH_FROM_GITHUB;
    
    @Value("${crawler.target.github}")
    String GITHUB_URL;
    
    
	// 매일 5시 30분 0초에 실행한다.
    //초 분 시 일 월 년
	@Scheduled(cron = "0 30 5 * * *") 
	public void aJob() { 
	    Date today = new Date();
	    //System.out.println(today);
	    Zip zip = new Zip();
	    //SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
	    SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");//hhmmss
	        
	    //System.out.println("Date: "+date.format(today));
	    //System.out.println("Crontab Time: "+time.format(today));
	    

	} 

	
	
	// 매월 1일 0시 0분 0초에 실행한다. 
	@Scheduled(cron = "0 0 0 1 * *") 
	public void anotherJob() { 
		// 실행될 로직 
		System.out.println("크론탭탭탭");		
	} 
	
	
	
	// 서버 시작 후 60초 후에 첫 실행, 그리고 60초마다 실행 
	
	@Scheduled(initialDelay = 60000, fixedDelay = 60000) 
	public void otherJob() { 
	    Date today = new Date();
	    System.out.println(today);
	    Zip zip = new Zip();
	    //SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
	    SimpleDateFormat time = new SimpleDateFormat("yyyyMMddhhmmss");
	        
	    //System.out.println("Date: "+date.format(today));
	    System.out.println("Crontab Time: "+time.format(today));
		
	}
	
	

}