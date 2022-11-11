# unlimint
Steps to Run the Project -
  -  Clone the Repo
  -  mvn clean install
  -  java -jar jarname.jar jsonfilename.json jsonfilename1.json csvfilename.csv csvfilename2. csv      (you can pass your json or csv file in any order)
  Keep the files to be parsed at the location of jar file and output file will be generated at that location also.
  
  
 Project Code Flow -
    -  In Main class fetching filname from filename arguments we passed.
    -  Get Path of current directory
    -  Created a thread pool equal to number of file arguments we passed.
    -  checking file extension and based on that taking required arguments calling utility class to parse the data i.e  CSVParseUtil  or JSONParseUtil
    -  Looking for file in directory
    -  If found then parse the data and put in output json file as expected.
    
    
    
 Project Uses public Maven dependencies are below -
  -com.googlecode.json-simple
  -commons-io
  -commons-csv
  -com.fasterxml.jackson.core
    
