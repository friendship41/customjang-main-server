package com.friendship41.customjangmainserver.data.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Unit {
  private String xPosition;
  private String yPosition;
  private boolean isAlive;
}
