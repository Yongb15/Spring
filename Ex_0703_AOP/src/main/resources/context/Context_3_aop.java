package context;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import emp.EmpLoggingAspect;
import emp.EmpManager;

@Configuration
@EnableWebMvc
public class Context_3_aop {
	@Bean
	public EmpManager empManager() {
		return new EmpManager();
	}
	
	@Bean
	public EmpLoggingAspect loggingAspect() {
		return new EmpLoggingAspect();
	}
}
