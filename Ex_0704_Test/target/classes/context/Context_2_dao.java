package context;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


// 스프링한테 설정파일임을 알려주는 어노테이션
@Configuration
@ComponentScan("dao")
public class Context_2_dao {
	
}






