Raman Arora
20622513 r29arora
openjdk version "12.0.2" 2019-10-20
macOS 10.13.6 (MacBook Pro 2013)
Android Studio

How To:

Download all files uploaded to the a5 folder.
Run the app named app-debug.apk

** The hardware used to create this application was unable to support that large resolution of a Pixel C. As such, this application has been developed for and should be tested on any 1920x1080 resolution screen. I created this using a Nexus 7 tablet emulator using API 28.


The app implements the rag doll application as required of the assignment. 
Each body joint is restricted to the angles as outlined. 
The upper and lower legs scale in tandem, however the feet also scale, I was unable to address this.

The application contains 3 buttons in the top right corner. 
The leftmost (first) button from the left resets the current ragdoll into its original position.
The second (middle) button opens a pop up displaying my name and student number.
The rightmost (last) button switches between the 2 ragdolls - ie, paper doll and spider.

Enhancements:
Added a second rag doll in the form of a spider, accessible through the rightmost button. 
The torso of the spider is divided into 3 sections. The top and bottom torso pivot at 50 degrees and 70 degrees respectively, the middle torso does not. Only the middle torso can be used to translate the spider to different parts of the screen. Further, each torso also contains at least 1 pair of legs, with a joint in the middle. The leg components closest to the body, each have limited rotation of 100 degrees. The "outer" leg components are free to rotate 360 degrees.
Finally, the top torso is scalable and the attached legs scale proportionally.

