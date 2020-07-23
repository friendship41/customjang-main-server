package com.friendship41.customjangmainserver.filter;

import com.sun.org.apache.regexp.internal.RE;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HeaderAccessTokenFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    if (isContainAccessToken(request)) {
      String access_token = request.getHeader("access_token");
      System.out.println();
    }
    filterChain.doFilter(request, response);
  }

  private boolean isContainAccessToken(HttpServletRequest request) {
    return request.getHeader("access_token") != null;
  }
}
