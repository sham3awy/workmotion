Senior Level

Execution Steps:-
------------------

docker build -t senior-assignment:1.0 .

docker run -d -p 8080:8080 -t senior-assignment:1.0

The project should be available on http://localhost:8080

I also have added swagger API, you can find the APIs in the URL 

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

and change the version to v2 then click on explore to view the APIs