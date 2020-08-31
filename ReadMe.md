I have following installed on the computer where I tested the project:
   - Windows 10 (64 bit)
   - JDK 14.0.2
   - Maven 3.6.3
   
   
   
To run test cases execute “*Run Tests for BoC Quiz.bat*” batch file.


To compile and run web server execute “*Run Server for BoC Quiz.bat*” batch file and open “http://localhost:8080/” in the browser (tested in both Google Chrome /Version 84.0.4147.135 (Official Build) (64-bit)/ and Microsoft Edge /Version 85.0.564.41 (Official build) (64-bit)/)

----------------------------------------

**Notes on web page**:
   - In order to change the filter for the date range of readings click the underscored text next to the Filter label;
   - Two addition pages were used to test error page:
       - http://localhost:8080/pagewitherror
       - http://localhost:8080/page
           

----------------------------------------

**Notes on Technology / Framework**:


The main page of the quiz loads jquery minimized script from Google API site (ajax.googleapis.com). I have limited use of jQuery to issue AJAX request. All dynamic content on the main page is accomplished purely by using DOM Web API and CSS.


Since I used input[Date] control to enter start and end date for the filter, client-side validation is really very limited, most of it is done inside the control itself.

