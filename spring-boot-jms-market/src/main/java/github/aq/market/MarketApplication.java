package github.aq.market;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@ComponentScan
@EnableJms
public class MarketApplication {

	public static void main(String[] args) {
		 SpringApplication.run(MarketApplication.class, args);
	}
	//https://memorynotfound.com/spring-boot-passing-command-line-arguments-example

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.setMaxPoolSize(100);
		executor.setThreadNamePrefix("market-");
		executor.initialize();
		return executor;
	}
}
