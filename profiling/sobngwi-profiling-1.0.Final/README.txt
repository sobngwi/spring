swagger
http://localhost:8080/nsy135/rest/api-docs

Run the applicattion :
set the property :
-Dlog4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
-Dlog4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
-javaagent:/home/suse/.m2/repository/org/springframework/spring-instrument/4.1.4.RELEASE/spring-instrument-4.1.4.RELEASE.jar

Ajout de 2 profile : rest et web . Rest pour service REST test 
et Web pour service spring web-mvc test.
Le context.xml est chargé par le plugin tomcat7.

swagger est maintenant :
http://localhost:8080/spring-mvc-hibernate/rest/api-docs