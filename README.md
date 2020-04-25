# resume-helper
App helps create better video resume by providing feedback on your audio and video.

## Idea/Requirement
For many job positions, especially ones which are heavy on communication(say marketing manager, product manager, executives, architects), employers would need video resume for initial screening. As an employer,you would want to 'see' him/her and filter out those candidates who are too bad. Asking for video resume can help in this.
This app is to help candidates create a better video resume by providing feedback as well as making few changes in the video itself, if asked.

## Flow
 - A user (interview candidate) creates a video resume. He/she uploads the video either directly to the application or shares google drive url of the video.
 - Application analyzes audio and video and provides 'feedback'.
 - User again performs step #1 if he wants to implement feedback. This process is repeated ubtil he is satisfied.
 
 To divide the application deliverables into manageable working software in parts, dividing whole into three phases.
 
 ## Phase 1
 Feedback on Audio - To keep the scope minimum and still be able to provide an end-to-end 'working' software, below is the minimal viable product (MVP) - 
 1. User uploads video directly into application. (This saves us from OAuth related code if we allow users to share their Google drive url of video).
 2. Application only focusses on audio in phase 1. It will check if speed and clarity in the audio/speech. English will be the   language.
   --- Clarity -> we will figure out percentage of words in his speech which match to some dictionary words, i.e. how clear are his words. Those words which application could not recognise(or was not audible), will be sent as feedback words on which the candidate hasd to work on.
   --- Speed -> If user is speaking too slow, or too fast, application will let the user know this.
   
 ## Phase 2
 Correct background of the video - Users might be creating videos in an environment which is not well organized. Application can remove any distractions in the video*(moving objects in background, removing background at all etc).
 
 ### Phase
 Feedback on Video - Application should provide feedback on below aspects of user's appearance - 
  - Too much physical/head movement,
  - Sitting/face posture (is user's face/eyes are straight to camera etc),
  


