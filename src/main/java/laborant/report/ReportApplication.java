package laborant.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ReportApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}
	
	/*
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("laborant.report")).build();
	}
	*/
}
