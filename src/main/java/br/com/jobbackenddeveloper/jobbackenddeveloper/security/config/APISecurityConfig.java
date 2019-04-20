package br.com.jobbackenddeveloper.jobbackenddeveloper.security.config;

import br.com.jobbackenddeveloper.jobbackenddeveloper.security.JwtAuthenticationEntryPoint;
import br.com.jobbackenddeveloper.jobbackenddeveloper.security.filters.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	@Qualifier("JwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.applyPermitDefaultValues();
		corsConfiguration.addAllowedOrigin("http://localhost:3000");

		httpSecurity.cors().configurationSource(request -> corsConfiguration)
				.and()
				.antMatcher("/api/**").csrf().
		disable().
		exceptionHandling().
		authenticationEntryPoint(unauthorizedHandler).
		and().
		sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().
		authorizeRequests().
		antMatchers("/css/**", "/fonts/**", "/js/**","/images/**","/login/**", "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**", "/v2/api-docs").
		permitAll().
		antMatchers("/api/**").
		authenticated().and().addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class).headers().cacheControl();

	}
	
}
