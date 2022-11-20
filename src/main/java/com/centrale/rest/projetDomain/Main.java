package com.centrale.rest.projetDomain;

import com.centrale.rest.entity.SeriesEntity;

import java.sql.SQLOutput;

public class Main {
   public static void main(String[] args) {

      Series Witcher = new Series();
      Witcher.setName("Sorceleur");
      System.out.println(Witcher.getState());
      Witcher.wish();
      System.out.println(Witcher.getState());
      Witcher.start();
      Witcher.consume(45);
      System.out.println(Witcher.getEpisode());
      Witcher.consume(60);
      System.out.println(Witcher.getState());

      Series BreakingBad = new Series("Breaking Bad",60);
      BreakingBad.finnish();
      BreakingBad.consume(4);
      BreakingBad.drop();
      System.out.println(BreakingBad.getState());
      BreakingBad.start();
      BreakingBad.finnish();
      System.out.println(BreakingBad.getEpisode());

      SeriesEntity WItcher = new SeriesEntity(Witcher);
      System.out.println(WItcher.getName() + WItcher.getState() + WItcher.getTotalEpisode() + WItcher.getEpisode() + WItcher.getId());

      Witcher.start();
      System.out.println(Witcher.getEpisode());
      WItcher.update(Witcher);
      System.out.println(WItcher.getName() + WItcher.getState() + WItcher.getTotalEpisode() + WItcher.getEpisode() + WItcher.getId());






   }
}
