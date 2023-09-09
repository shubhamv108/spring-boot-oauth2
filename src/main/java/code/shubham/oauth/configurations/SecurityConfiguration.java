package code.shubham.oauth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(a -> a.requestMatchers("/**").fullyAuthenticated())
			.sessionManagement(a -> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.oauth2ResourceServer(a -> a.jwt(Customizer.withDefaults()))
			.cors(Customizer.withDefaults())
			.csrf(a -> a.disable())
			.build();
	}

}
