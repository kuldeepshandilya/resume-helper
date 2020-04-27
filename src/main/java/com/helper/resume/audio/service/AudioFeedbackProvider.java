package com.helper.resume.audio.service;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import com.grammarbot.client.model.GrammarBotResponse;
import com.grammarbot.client.model.Replacement;
import com.grammarbot.client.model.Matches;
import com.helper.resume.audio.grammar.GrammarUtil;
import com.helper.resume.audio.response.Feedback;
import com.helper.resume.audio.response.Feedbacks;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AudioFeedbackProvider {

  @Autowired
  private GrammarUtil grammarUtil;

  private byte[] getFileContent(String fileURL) {
    byte[] content = new RestTemplate().getForObject(fileURL, byte[].class);
    System.out.println("Inside getFileContent, content: "+content);
    return content;
  }

  public  GrammarBotResponse transcribeModelSelectionFromLocalFile(String fileName) throws Exception {
    Path path = Paths.get(fileName);
    byte[] content = Files.readAllBytes(path);

    try (SpeechClient speech = SpeechClient.create()) {
      // Configure request with video media type
      RecognitionConfig recConfig =
          RecognitionConfig.newBuilder()
              // encoding may either be omitted or must match the value in the file header
              //.setEncoding(AudioEncoding.LINEAR16)
              .setEncoding(AudioEncoding.AMR_WB)
              .setLanguageCode("en-US")
              // sample rate hertz may be either be omitted or must match the value in the file
              // header
              .setSampleRateHertz(16000)
              .setModel("video")
              .build();

      RecognitionAudio recognitionAudio =
          RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();

     RecognizeResponse recognizeResponse = speech.recognize(recConfig, recognitionAudio);
      // Just print the first result here.
      SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);
      // There can be several alternative transcripts for a given chunk of speech. Just use the
      // first (most likely) one here.
      SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
      System.out.println("speech: "+alternative.getTranscript());
      return grammarUtil.checkGrammar(alternative.getTranscript());
    }
  }

  public  Feedbacks transcribeModelSelectionFromRemoteFile(String fileURL) throws Exception {
    byte[] content = getFileContent(fileURL);

    try (SpeechClient speech = SpeechClient.create()) {
      // Configure request with video media type
      RecognitionConfig recConfig =
          RecognitionConfig.newBuilder()
              // encoding may either be omitted or must match the value in the file header
              //.setEncoding(AudioEncoding.LINEAR16)
              .setEncoding(AudioEncoding.LINEAR16)
              .setLanguageCode("en-US")
              // sample rate hertz may be either be omitted or must match the value in the file
              // header
              .setSampleRateHertz(16000)
              .setModel("video")
              .build();

      RecognitionAudio recognitionAudio =
          RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();

      RecognizeResponse recognizeResponse = speech.recognize(recConfig, recognitionAudio);
      // Just print the first result here.
      SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);
      // There can be several alternative transcripts for a given chunk of speech. Just use the
      // first (most likely) one here.
      SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
      System.out.println("speech: "+alternative.getTranscript());
      return processFeedback(grammarUtil.checkGrammar(alternative.getTranscript()));
    }
  }

  private Feedbacks processFeedback(GrammarBotResponse grammarBotResponse) {
    List<Feedback> feedbackList = new ArrayList<>();
    List<Matches> matches = grammarBotResponse.getMatches();
    for(Matches match : matches) {
      feedbackList.add(new Feedback(match.getMessage(), match.getContext().getText()));
    }
    return new Feedbacks(feedbackList);
  }


  /**
   * Performs speech recognition on remote FLAC file and prints the transcription.
   *
   * @param gcsUri the path to the remote FLAC audio file to transcribe.
   */
  public  GrammarBotResponse syncRecognizeGcs(String gcsUri) throws Exception {
    // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
    String text = null;
    try (SpeechClient speech = SpeechClient.create()) {
      // Builds the request for remote FLAC file
      RecognitionConfig config =
          RecognitionConfig.newBuilder()
              .setEncoding(AudioEncoding.AMR_WB)
              .setLanguageCode("en-US")
              .setSampleRateHertz(16000)
              .build();

      RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

      // Use blocking call for getting audio transcript
      RecognizeResponse response = speech.recognize(config, audio);
      List<SpeechRecognitionResult> results = response.getResultsList();

      for (SpeechRecognitionResult result : results) {
        // There can be several alternative transcripts for a given chunk of speech. Just use the
        // first (most likely) one here.
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
        System.out.println("speech: "+alternative.getTranscript());
        text = alternative.getTranscript();
      }
    }
    return grammarUtil.checkGrammar(text);
  }
}
