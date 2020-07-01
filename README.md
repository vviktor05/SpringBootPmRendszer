# Spring Boot Projektmenedzsment rendszer
Az első Spring Boot projektem.

# CRUD REST-API útvonalak:

- GET

http://localhost:8080/api/projects

http://localhost:8080/api/projects/active

http://localhost:8080/api/projects/id/{id}

http://localhost:8080/api/projects/name/{name}

http://localhost:8080/api/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

0-tól az összes paraméter használatával lehet szűrni, sorrend nem számít.

http://localhost:8080/api/developer/projects

http://localhost:8080/api/developer/projects/active

http://localhost:8080/api/developer/projects/id/{id}

http://localhost:8080/api/developer/projects/name/{name}

http://localhost:8080/api/developer/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

http://localhost:8080/api/teams

http://localhost:8080/api/teams/id/{id}

http://localhost:8080/api/teams/working_on/project_id/{id}

A megadott id-vel rendelkező projekten dolgozó csapatokat adja vissza.

http://localhost:8080/api/customers

http://localhost:8080/api/customers/{id}

http://localhost:8080/api/tasks

http://localhost:8080/api/tasks/active

http://localhost:8080/api/tasks/id/{id}

http://localhost:8080/api/tasks/name/{topic}
-------------------------------------------------
http://localhost:8080/api/developer/tasks

http://localhost:8080/api/developer/tasks/active

http://localhost:8080/api/developer/tasks/id/{id}

http://localhost:8080/api/developer/tasks/name/{topic}

http://localhost:8080/api/reports

A csapatvezető, csak azokat a reportokat látja, amelyek kapcsolódnak valamelyik projekthez amin dolgozik.

http://localhost:8080/api/reports/id/{id}

http://localhost:8080/api/employees

http://localhost:8080/api/employees/id/{id}

http://localhost:8080/api/employees/in_team/team_id/{id}

A megadott csapat id-vel rendelkező csapatban dolgozó fejlesztőket adja vissza.

http://localhost:8080/api/employees/not_in_team/team_id/{id}

http://localhost:8080/api/team_memberships

http://localhost:8080/api/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/projects_teams

http://localhost:8080/api/projects_teams/project_id/{projectId}/team_id/{teamId}

- POST

http://localhost:8080/api/projects

http://localhost:8080/api/customers

http://localhost:8080/api/tasks

http://localhost:8080/api/reports

http://localhost:8080/api/employees

http://localhost:8080/api/teams

http://localhost:8080/api/team_memberships

http://localhost:8080/api/projects_teams

- DELETE

http://localhost:8080/api/projects/{id}

http://localhost:8080/api/customers/{id}

http://localhost:8080/api/tasks/{id}

http://localhost:8080/api/reports/{id}

http://localhost:8080/api/employees/{id}

http://localhost:8080/api/teams/{id}

http://localhost:8080/api/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/projects_teams/project_id/{projectId}/team_id/{teamId}

- PUT

http://localhost:8080/api/projects/{id}

http://localhost:8080/api/customers/{id}

http://localhost:8080/api/tasks/{id}

http://localhost:8080/api/reports/{id}

http://localhost:8080/api/employees/{id}

http://localhost:8080/api/teams/{id}

http://localhost:8080/api/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/projects_teams/project_id/{projectId}/team_id/{teamId}