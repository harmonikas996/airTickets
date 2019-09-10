package airtickets.security.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import airtickets.model.user.Authority;
import airtickets.security.TokenUtils;

//Filter koji ce presretati svaki zahtev klijenta ka serveru
//Sem nad putanjama navedenim u WebSecurityConfig.configure(WebSecurity web)
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private TokenUtils tokenUtils;

	private UserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
		this.tokenUtils = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String username;
		String authToken = tokenUtils.getToken(request);
		
		if (authToken != null) {
			// uzmi username iz tokena
//			log.info("AUTHTOKEN: " + authToken);
			username = tokenUtils.getUsernameFromToken(authToken);
			
//			log.info("USERNAME: " + username);
			if (username != null) {
				// uzmi user-a na osnovu username-a
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);		
//				log.info("POSLE IF-A USERNAME: " + username);
				// proveri da li je prosledjeni token validan
				if (tokenUtils.validateToken(authToken, userDetails)) {
					// kreiraj autentifikaciju
//					log.info("TOKEN VALIDAN");
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
					authentication.setToken(authToken);
					
					Collection<GrantedAuthority> autoriti = authentication.getAuthorities();
					Authority autor = (Authority) autoriti.iterator().next();
					
//					log.info("COVEK JE: " + autor.getName());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		
		chain.doFilter(request, response);
	}

}