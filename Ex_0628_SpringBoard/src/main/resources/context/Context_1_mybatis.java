package context;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

//스프링한테 설정파일임을 알려주는 어노테이션
@Configuration
public class Context_1_mybatis {
	
	@Bean
	public DataSource ds() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("test_pm");
		ds.setPassword("1111");
		return ds;
	}
	
	//SqlSessionFactoryBean은 스프링의 FactoryBean 인터페이스를 구현한다.
	//이 설정은 스프링이 SqlSessionFactoryBean 자체를 생성하는 것이 아니라
	//factory에서 getObject()메서드를 호출한 결과를 리턴한다는 의미이다.
	//이 경우 스프링은 어플리케이션 시작 지점에 SqlSessionFactory를 빌드하고
	//sqlSessionFactory라는 이름으로 저장한다.
	
	@Bean
	public SqlSessionFactory factoryBean() throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds());
		
		factoryBean.setConfigLocation(new ClassPathResource("config/mybatis/mybatis-config.xml"));
		return factoryBean.getObject();
	}
	
	//Mybatis에서는 SqlSession을 생성하기 위해 SqlSessionFactory를 사용한다.
	//Session을 한번 생성하면 매핑구문을 실행하거나 commit 또는 rollbakc을 하기 위해 Session을 사용할 수 있다.
	//마지막으로 더 이상 필요하지 않은 상태가 되면 Session을 닫는다.
	//Mybatis Spring 연동모듈을 사용하면 SqlSessionFactory를 직접 사용할 필요가 없다.
	//왜냐하면, 스프링 트랜잭션 설정에 따라 자동으로 커밋 또는 롤백을 수행하고, 닫히는 쓰레드에
	//안전한 SqlSession 개체가 Spring Bean에 주입될 수 있기 때문이다.
	
	//SqlSessionTemplate클래스는 SqlSession을 implements하고 코드에서 SqlSession을 
	//대체하는 역할을 한다.
	//SQL을 처리하는 Mybatis method를 호출할 때 SqlSessionTemplate은 SqlSession이 
	//현재의 Spring Transaction에서 사용될 수 있도록 보장해준다.
	//SqlSessionTemplate은 필요한 시점에서 Session을 닫고, Commit하거나 Rollback하는 것을
	//포함한 Session의 LifeCycle을 관리한다.
	
	@Bean
	public SqlSessionTemplate sqlSessionBean() throws Exception {
		return new SqlSessionTemplate(factoryBean());
	}

}

















