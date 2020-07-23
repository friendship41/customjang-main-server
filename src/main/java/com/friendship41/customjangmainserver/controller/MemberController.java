package com.friendship41.customjangmainserver.controller;

import com.friendship41.customjangmainserver.config.security.data.ClientUser;
import com.friendship41.customjangmainserver.config.security.service.ClientUserDetails;
import com.friendship41.customjangmainserver.config.security.data.Entry;
import com.friendship41.customjangmainserver.data.response.ProcessResultResponse;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${auth.server.uri.myinfo}")
  private String authServerUri;

  @GetMapping("/member/myinfo")
  @ResponseBody
  public Object getMyInfo() {
    ClientUserDetails userDetails =
        (ClientUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    ClientUser clientUser = userDetails.getClientUser();
    clientUser.setEntries(Arrays.asList(new Entry("1번"), new Entry("2번")));

    ProcessResultResponse response = this.getUserProfileFromAuthServer();
    log.info("response: "+response.toString());
    return response;
  }

  @GetMapping("/callback")
  public String callback() {
    return "forward:/member/myinfo";
  }

  private ProcessResultResponse getUserProfileFromAuthServer() {
    try {
      return oAuth2RestTemplate.getForObject(authServerUri, ProcessResultResponse.class);
    } catch (HttpClientErrorException e) {
      log.error("user not found", e);
      return ProcessResultResponse.makeErrorResponse(
          ProcessResultResponse.RESULT_CODE_RESULT_NOT_FOUND,
          ProcessResultResponse.RESULT_MESSAGE_MEMBER_NOT_FOUND
      );
    }
  }
}
