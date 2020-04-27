package com.helper.resume.rssources;

import com.helper.resume.audio.response.Feedbacks;
import com.helper.resume.audio.service.AudioFeedbackProvider;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
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
    //export GOOGLE_APPLICATION_CREDENTIALS
    /*String s3FilePath = "https://resume-helper-bucket.s3.amazonaws.com/sample+reading.MP3";
    s3FilePath = "https://resume-helper-bucket.s3.amazonaws.com/Google_Gnome.wav";*/
    //http://localhost:8080/audio/feedback?fileurl=https://resume-helper-bucket.s3.amazonaws.com/Google_Gnome.wav
    System.out.println("==== Inside getAudioResumeFeedback, fileURL ===== " + fileURL);
    Feedbacks feedbacks = audioFeedbackProvider.transcribeModelSelectionFromRemoteFile(fileURL);
    return feedbacks;
  }

  private  boolean isListEmpty(List list) {
    return (list == null || list.isEmpty());
  }

}
