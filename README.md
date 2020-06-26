# Spring Boot Projektmenedzsment rendszer
Az első Spring Boot projektem.

# CRUD REST-API útvonalak:

- GET

http://localhost:8080/api/projects

http://localhost:8080/api/projects/active

http://localhost:8080/api/projects/search/id/{id}

http://localhost:8080/api/projects/search/name/{name}

http://localhost:8080/api/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

0-tól az összes paraméter használatával is lehet szűrni, sorrend nem számít.

http://localhost:8080/api/teams

http://localhost:8080/api/teams/search/id/{id}

http://localhost:8080/api/teams/search/working_on/project_id/{id}

A megadott id-vel rendelkező projekten dolgozó csapatokat adja vissza.

http://localhost:8080/api/teams/search/not_working_on/project_id/{id}

http://localhost:8080/api/customers

http://localhost:8080/api/tasks

http://localhost:8080/api/tasks/active

http://localhost:8080/api/tasks/search/id/{id}

http://localhost:8080/api/tasks/search/name/{topic}

http://localhost:8080/api/reports

http://localhost:8080/api/reports/search/id/{id}

http://localhost:8080/api/employees

http://localhost:8080/api/employees/search/in_team/team_id/{id}

A megadott csapat id-vel rendelkező csapatban dolgozó fejlesztőket adja vissza.

http://localhost:8080/api/employees/search/not_in_team/team_id/{id}

http://localhost:8080/api/team_memberships

- POST
http://localhost:8080/api/

- DELETE
http://localhost:8080/api/

- PUT
http://localhost:8080/api/
