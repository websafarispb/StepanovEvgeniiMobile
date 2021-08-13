# StepanovEvgeniiMobile second homework in mobile testing

There six test suits. 
One of them named 'web' and it testing Google page.
For running web suit input in terminal or cmd "mvn clean test -Pweb" 
Second test is named 'native' and testing EPAMTestApp.apk. 
For running native suit input in terminal or cmd "mvn clean test -Pnative" 
For running cloud suits you have to get token on site https://mobilecloud.epam.com/
and encoded it using EncodedToken class from utils package, you should add token as environment  
variable in edit configuration. Run Encoder and copy encoded token from cmd. After this
you can run clouds test suits from terminal using next pattern
mvn clean test -PiOSCloudWeb -Dtoken=encodedToken
or you can add encoded toke in teg <token> in pom.xml