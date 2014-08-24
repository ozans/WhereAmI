WhereAmI
========

![Screenshot](screenshot1.png?raw=true)
![Screenshot](screenshot2.png?raw=true)

Simple GUI-less Android app which obtains one's latitude and longitude coordinates via GPS or network and reverse geocodes them using Yahoo!'s webservice to a textual address which is shown as a Toast when launched

Known Bugs
----
* If location gathering via GPS is enabled on your handset, it will always try to obtain the coordinates through GPS. If you are indoors, this call will get stuck for an unacceptably long time. In order to solve this, I plan to cancel the GPS coordinate gathering function call if it does not succeed in 3 seconds, and try via network instead.

TODO
----
* Utilize Google Text-to-speech (TTS) to be able to read aloud the textual address to someone on the phone when they ask you the question "Where are you?"
* If there is a call ongoing when the user launches this application, read it aloud and send the address to them as an SMS which also contains a link to Google Maps pinpointing the user's location through its coordinates
* Make it a widget instead? Updating the textual address every couple of minutes or on press.


