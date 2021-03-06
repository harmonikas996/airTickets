 package airtickets.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import airtickets.security.TokenUtils;
import airtickets.security.auth.RestAuthenticationEntryPoint;
import airtickets.security.auth.TokenAuthenticationFilter;
import airtickets.service.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
	// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	// Neautorizovani pristup zastcenim resursima
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Definisemo nacin autentifikacije
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	TokenUtils tokenUtils;

	// Definisemo prava pristupa odredjenim URL-ovima
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and()
			// komunikacija izmedju klijenta i servera je stateless
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			
			// za neautorizovane zahteve posalji 401 gresku
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			
			// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
			.authorizeRequests()
			.antMatchers("/auth/**").permitAll()

			.antMatchers("/flightreservations").permitAll()

			.antMatchers("/vehicles/all").permitAll()
			.antMatchers("/vehicles/**").permitAll()
			.antMatchers("/hotels/**").permitAll()
			.antMatchers("/amenities/**").permitAll()
			.antMatchers("/rentacars/all").permitAll()
			.antMatchers("/rentacars/search").permitAll()
			.antMatchers("/rentacars/{\\^[0-9]+&}").permitAll()
			.antMatchers("/airports/{\\^[0-9]+&}").permitAll()
			.antMatchers("/flights/search").permitAll()
			.antMatchers("/flights/**").permitAll()
			.antMatchers("/vehicles/search").permitAll()
			.antMatchers("/aircompanies/all").permitAll()
			.antMatchers("/aircompanies/**").permitAll()
			.antMatchers("/seats/quickSeatsByCompany").permitAll()
			.antMatchers("/vehicles/makeQuickReservation/**").permitAll()
			.antMatchers("/vehicles/makeReservation/**").permitAll()
			.antMatchers("/amenityReservations/makeReservation/**").permitAll()
			.antMatchers("/roomReservations/makeQuickReservation/**").permitAll()
			.antMatchers("/rentacarratings/**").permitAll()
			.antMatchers("/carratings/**").permitAll()
			.antMatchers("/hotelRatings/**").permitAll()
			.antMatchers("/aircompanyratings/**").permitAll()
			.antMatchers("/roomRatings/**").permitAll()
			.antMatchers("/finishReservation/**").permitAll()
			
			.antMatchers("/hotels/all").permitAll()
			.antMatchers("/hotels/locations").permitAll()
			.antMatchers("/airports/all").permitAll()
			.antMatchers("/roomReservations/all").permitAll()
			.antMatchers("/hotels/freeRooms").permitAll()
			.antMatchers("/carreservations/all").permitAll()
			.antMatchers("/carreservations/quick/**").permitAll()
			.antMatchers("/roomPrices/**").permitAll()
			.antMatchers("/seats/seatsByFlight").hasAnyAuthority("sysadmin", "hotel", "rentacar", "aircompany", "client")
			.antMatchers("/seats/makeReservation").hasAnyAuthority("sysadmin", "hotel", "rentacar", "aircompany", "client")
			.antMatchers("/branchoffices/locations").permitAll()
			// svaki zahtev mora biti autorizovan
			.anyRequest().authenticated().and()
			
			// presretni svaki zahtev filterom
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

//		http.headers().cacheControl().disable();
//		http.requestCache().disable();
		http.csrf().disable();
	}

	// Generalna bezbednost aplikacije
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}


}
