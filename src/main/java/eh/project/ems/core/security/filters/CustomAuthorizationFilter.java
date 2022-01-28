package eh.project.ems.core.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import eh.project.ems.core.security.constants.SecurityConstants;

public class CustomAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/api/login")) {
			filterChain.doFilter(request, response);
		}
		else {
			String authorizationHeader = request.getHeader("Authorization");
			if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")) {
				try {
					String token = authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SecretKey.getBytes());
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = jwtVerifier.verify(token);
					String email = decodedJWT.getSubject();
					String[]claims = decodedJWT.getClaim("role").asArray(String.class);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					//TODO 
					Arrays.stream(claims).forEach(claim->{
						authorities.add(new SimpleGrantedAuthority(claim));
					});
					
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(email,null,authorities);
					
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {
					response.setHeader("error", e.getMessage());
					response.setStatus(HttpStatus.FORBIDDEN.value());
				}
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
		
	}

}
