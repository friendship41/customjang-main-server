package com.friendship41.customjangmainserver.data.response;

import com.friendship41.customjangmainserver.data.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResultResponse extends ProcessResultResponse {
  private Member member;
}
