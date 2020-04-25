# resume-helper
App helps create better video resume by providing feedback on your audio and video.

## Idea/Requirement
For many job positions, especially the ones which are heavy on communication(say marketing manager, product manager, executives, architects), employers would need video resume for initial screening(and filtering out). As an employer,you would want to 'see' candidate and filter out those candidates who are too bad. Asking for video resume can help in this.

This app is to help candidates create a better video resume by providing them with feedback as well as making few changes in the video itself, if asked.

## Flow
 - A user (interview candidate) creates a video resume through his mobile/laptop. He/she uploads the video either directly to the application or shares google drive url of the video,
 - Application analyzes audio and video and provides 'feedback'.
 - User again performs step #1 if he/she wants to implement feedback. This process is repeated until he/she is satisfied.
 
 ## Phases
 Application in not a trivial one, given requirements around video.
To divide the application deliverables into manageable 'working' softwares in parts, dividing project effort into three phases.
 
 ### Phase 1
 Feedback on Audio only - 
 1. User can upload audio or video file into application (this saves us from writing OAuth code, which is required in case of workig with Google drive files),
 2. Application will check if 'speed and clarity' in the audio/speech is good. English will be the language supported as of now.
   - Clarity -> We will calculate percentage of words in his speech which match to some dictionary word, i.e. how clear are his words. Those words which application could not recognise(or was not audible), will be sent as feedback words on which the candidate hasd to work on. Also we will return percentage clarity (what % of his words were clear).
   - Speed -> If the user is speaking too slow, or too fast, application will let the user know this.
   
 ## Phase 2
 Correct background of the video - Users might be creating videos in an environment which is not well organized(working from home). Application can remove any distractions in the video (removing the moving objects in background or removing background at-all etc).
 
 ### Phase 3
 Feedback on Video - Application should provide feedback on below aspects of user's appearance - 
  - Too much physical/head movement,
  - Sitting/face posture (is user's face/eyes are straight to camera etc),
  


