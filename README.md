# filesystemapi
File System Spring Boot API - Cassandra

# compile
mvn clean install
mvn clean integration-test

#run
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html

# oracle
http://192.168.56.3:8080/apex/

### play
http://localhost:8090/
%2F

##### GET printPathToFile
http://localhost:8090/printPathToFile/tmp/Users%2Fmarco27%2Ftemp%2Ftmp.txt
http://localhost:8090/printPathToFile/Users%2Fmarco27%2Fopt/Users%2Fmarco27%2Ftemp%2Fopt.txt
http://localhost:8090/printPathToFile/Volumes%2FMAC200%2Fmac200/Users%2Fmarco27%2Ftemp%2Fmac200.txt
http://localhost:8090/printPathToFile/Users%2Fmarcoguastalli%2Fopt%2Fdocker/Users%2Fmarcoguastalli%2Ftemp%2Fdocker.txt
{"pathToPrint":"/Users/marcoguastalli/opt/docker","fileToPrint":"/Users/marcoguastalli/temp/docker.txt"}

##### GET findFileStructureById
http://localhost:8090/findFileStructureById/Users%2Fmarco27%2Ftemp
http://localhost:8090/findFileStructureById/Users%2Fmarcoguastalli%2Ftemp

##### GET findFileStructureByPath
http://localhost:8090/findFileStructureByPath/Users%2Fmarco27%2Ftemp
http://localhost:8090/findFileStructureByPath/Users%2Fmarcoguastalli%2Ftemp

##### GET(!) saveFileStructure
http://localhost:8090/saveFileStructure/Users%2Fmarco27%2Ftemp
http://localhost:8090/saveFileStructure/Users%2Fmarcoguastalli%2Ftemp

##### DELETE deleteFileStructure
http://localhost:8090/deleteFileStructure/Users%2Fmarco27%2Ftemp
http://localhost:8090/deleteFileStructure/Users%2Fmarcoguastalli%2Ftemp
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deleteFileStructure/Users%2Fmarcoguastalli%2Ftemp"
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deleteFileStructure/Users%2Fmarco27%2Ftemp"
