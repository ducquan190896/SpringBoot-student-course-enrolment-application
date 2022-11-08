package com.quan.gradepractice.Security.Filter;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.quan.gradepractice.Exception.EntityNotFoundException;

@Component
public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JWTCreationException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        }  catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        } 
        catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        } 
        catch (ServletException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        } 
        catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
            response.getWriter().flush();
        }
    }
}
