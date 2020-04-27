package com.helper.resume.audio.grammar;

import com.grammarbot.client.GrammarBotBuilder;
import com.grammarbot.client.GrammarBotClient;
import com.grammarbot.client.model.GrammarBotResponse;
import com.grammarbot.client.model.Matches;
import com.grammarbot.client.model.Warnings;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GrammarUtil {
  private static final String API_KEY = "KS9C5N3Y";

  public  GrammarBotResponse checkGrammar(String para) {
    System.out.println("para : "+para);
    GrammarBotClient botClient = new GrammarBotBuilder().build();
   // GrammarBotClient botClient = new GrammarBotBuilder().setApiKey(API_KEY).build();
    GrammarBotResponse response = botClient.check(para, "en-US");
    Warnings warning = response.getWarnings();
    if(warning != null){
      System.out.println("warning: "+warning.toString());
    }
    List<Matches> matches =  response.getMatches();
    if(matches != null ) {
      for (Matches match : matches) {
        System.out.println("Feedback:");
        System.out.println(match);
      }
    }
  return response;
  }
}
