package com.helper.resume.audio.response;

/**
 * message=Statistics suggests that 'horse' might be the correct word here. Please check.
 * statement = degrees he's right okay no turn on the hose I'm holding sure, (context's text attribute)
 * Correction = Replacement [value=horse]
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {
private String error;
private String statement;

  public Feedback(String error, String statement) {
    this.error = error;
    this.statement = statement;
  }

  public String getError() {
    return error;
  }

  public String getStatement() {
    return statement;
  }

}
