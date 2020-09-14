package edu.cybersoft.elearning.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicAuthenticationFilter {
    private UserDetailsService userDetailsService;

    public JwtFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jsonWebTokenHeader = request.getHeader("Authorization");
        java.lang.System.out.println(jsonWebTokenHeader);

        String jwtWithBearerFromHttpHeaderAuthorization = request.getHeader("Authorization");
        if (jwtWithBearerFromHttpHeaderAuthorization != null && jwtWithBearerFromHttpHeaderAuthorization.startsWith("Bearer ")) {
            String jwt = jwtWithBearerFromHttpHeaderAuthorization.replace("Bearer ", "");

            String email = Jwts.parser().setSigningKey("Hieu").parseClaimsJws(jwt).getBody().getSubject();

            java.lang.System.out.println(email);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            response.sendError(401, "Invalid credentials!");
        }
        chain.doFilter(request, response);
    }
}