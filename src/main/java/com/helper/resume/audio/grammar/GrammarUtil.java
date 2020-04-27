package com.helper.resume.audio.grammar;

import com.grammarbot.client.GrammarBotBuilder;
import com.grammarbot.client.GrammarBotClient;
import com.grammarbot.client.model.GrammarBotResponse;
import org.springframework.stereotype.Service;

@Service
public class GrammarUtil {
  private static final String API_KEY = "KS9C5N3Y";

  public  GrammarBotResponse checkGrammar(String para) {
    System.out.println("para : "+para);
    GrammarBotClient botClient = new GrammarBotBuilder().build();
   // GrammarBotClient botClient = new GrammarBotBuilder().setApiKey(API_KEY).build();
    return botClient.check(para, "en-US");
  }
}
