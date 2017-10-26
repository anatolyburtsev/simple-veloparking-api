**BUILD**

`./gradlew build`

Don't forget to install `lombok plugin` to launch it in your IDE

**START**

`java -jar build/libs/velobikeSimpleRest-*.jar`

or 

`sudo docker run -itd onotolemobile/velobikesimplerest:0.0.7-snapshot`

**LOOK THROUGH CONTROLLERS WITH SWAGGER**

Just open in browser: `http://localhost:8080/`

**RUN TESTS**

`./gradlew test`


**WRAP INTO DOCKER**

`./gradlew clean buildDocker`



