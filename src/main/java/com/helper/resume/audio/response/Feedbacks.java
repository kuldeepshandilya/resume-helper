package com.helper.resume.audio.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Feedbacks {
private List<Feedback> feedbacks;

  public Feedbacks(List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public List<Feedback> getFeedbacks() {
    return feedbacks;
  }
}
