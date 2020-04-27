package com.helper.resume.resources;

import com.helper.resume.audio.response.Feedbacks;
import com.helper.resume.audio.service.AudioFeedbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudioFeedbackResource {

  @Autowired
  private AudioFeedbackProvider audioFeedbackProvider;

  @RequestMapping(value = "audio/feedback", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Feedbacks getAudioResumeFeedback(@RequestParam("fileurl") String fileURL) throws Exception {
    return audioFeedbackProvider.transcribeModelSelectionFromRemoteFile(fileURL);
  }

}
