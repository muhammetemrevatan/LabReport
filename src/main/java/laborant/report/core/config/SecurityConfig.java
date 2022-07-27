package laborant.report.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}user").roles("USER")
				.and()
				.withUser("admin").password("{noop}admin").roles("ADMIN","USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
					.httpBasic()
				.and()
					.authorizeRequests()
					//.antMatchers("/shawie").permitAll() // kullanıcı adı sifreye gerek yok açık.
					//.antMatchers(HttpMethod.GET, "/").authenticated() // roller ile ugrasmadan kullanıcı adı sifre dogru ise giris yapar.
					.antMatchers(HttpMethod.POST, "/api/reportdefinition/reportdetailspage").hasRole("ADMIN") // authenticated olacak kişinin birde rolü olacaksa kullanılır.
					.antMatchers(HttpMethod.GET, "/api/reportdefinition/reportdetailspage").hasRole("ADMIN")
					
					.antMatchers(HttpMethod.POST, "/api/reportdefinition/reportdetailspage/editreport/**").hasRole("ADMIN") //** önündeki path den sonra gelen tüm alanlar için geçerli anlamındadır.
					.antMatchers(HttpMethod.GET, "/api/reportdefinition/reportdetailspage/editreport/**").hasRole("ADMIN")

					.antMatchers(HttpMethod.POST, "/api/reportdefinition/reportdetailspage").hasRole("USER")
					.antMatchers(HttpMethod.GET, "/api/reportdefinition/reportdetailspage").hasRole("USER")
				.and()
					.exceptionHandling()
					.accessDeniedPage("/403.html");
				
				
	}
}
 