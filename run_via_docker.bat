cls
docker run -it --rm --name api-tests -v %CD%:/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn test -D"testSuiteFile=src/test/resources/suites/SuiteTest.xml" -D"tokencode=%1"