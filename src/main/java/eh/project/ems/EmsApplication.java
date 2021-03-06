package eh.project.ems;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import eh.project.ems.business.abstracts.TeamMemberService;
import eh.project.ems.repository.ClaimRepository;
import eh.project.ems.repository.ProjectManagerRepository;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("eh.project.ems"))                                    
          .build();                                             
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CommandLineRunner run(ClaimRepository teamMemberService) { // PROGRAM BAŞLADĞINDA ÇALIŞACAK KOMUTLAR
//		return args->{
//			System.out.println(teamMemberService.getSystemManagerClaims());
//		};
//	}

}
