package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.config.UserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@Tag(name = UserApi.TAG_AUTH)
public class OAuthController {

  @GetMapping("/auth-google-info")
  @Operation(summary = UserApi.SUMARIO_GOOGLE_INFO, description = UserApi.DESCRICAO_GOOGLE_INFO)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = UserApi.RESPONSE_200),
      @ApiResponse(responseCode = "500", description = UserApi.RESPONSE_500)
  })
  public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User principal) {
    return principal.getAttributes();
  }

  @GetMapping("/redirect-to-google")
  @Operation(summary = UserApi.SUMARIO_GOOGLE_REDIRECT, description = UserApi.DESCRICAO_GOOGLE_REDIRECT)
  public void redirectLink(HttpServletResponse response) throws IOException {
    response.sendRedirect("/api/oauth2/authorization/google");
  }
}
