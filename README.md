# Spring Boot Projektmenedzsment rendszer
Az első Spring Boot projektem.

# CRUD REST-API útvonalak:

- GET

http://localhost:8080/api/project_manager/projects

http://localhost:8080/api/project_manager/projects/active

http://localhost:8080/api/project_manager/projects/id/{id}

http://localhost:8080/api/project_manager/projects/name/{name}

http://localhost:8080/api/project_manager/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

0-tól az összes paraméter használatával lehet szűrni, sorrend nem számít.

http://localhost:8080/api/project_manager/teams

http://localhost:8080/api/project_manager/teams/id/{id}

http://localhost:8080/api/project_manager/teams/working_on/project_id/{id}

A megadott id-vel rendelkező projekten dolgozó csapatokat adja vissza.

http://localhost:8080/api/project_manager/teams/not_working_on/project_id/{id}

http://localhost:8080/api/project_manager/customers

http://localhost:8080/api/project_manager/customers/{id}

http://localhost:8080/api/project_manager/tasks

http://localhost:8080/api/project_manager/tasks/active

http://localhost:8080/api/project_manager/tasks/id/{id}

http://localhost:8080/api/project_manager/tasks/name/{topic}

http://localhost:8080/api/project_manager/reports

http://localhost:8080/api/project_manager/reports/id/{id}

http://localhost:8080/api/project_manager/employees

http://localhost:8080/api/project_manager/employees/id/{id}

http://localhost:8080/api/project_manager/employees/in_team/team_id/{id}

A megadott csapat id-vel rendelkező csapatban dolgozó fejlesztőket adja vissza.

http://localhost:8080/api/project_manager/employees/not_in_team/team_id/{id}

http://localhost:8080/api/project_manager/team_memberships

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}

- POST

http://localhost:8080/api/project_manager/projects

http://localhost:8080/api/project_manager/customers

http://localhost:8080/api/project_manager/tasks

http://localhost:8080/api/project_manager/reports

http://localhost:8080/api/project_manager/employees

http://localhost:8080/api/project_manager/teams

http://localhost:8080/api/project_manager/team_memberships

http://localhost:8080/api/project_manager/projects_teams

- DELETE

http://localhost:8080/api/project_manager/projects/{id}

http://localhost:8080/api/project_manager/customers/{id}

http://localhost:8080/api/project_manager/tasks/{id}

http://localhost:8080/api/project_manager/reports/{id}

http://localhost:8080/api/project_manager/employees/{id}

http://localhost:8080/api/project_manager/teams/{id}

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}

- PUT

http://localhost:8080/api/project_manager/projects/{id}

http://localhost:8080/api/project_manager/customers/{id}

http://localhost:8080/api/project_manager/tasks/{id}

http://localhost:8080/api/project_manager/reports/{id}

http://localhost:8080/api/project_manager/employees/{id}

http://localhost:8080/api/project_manager/teams/{id}

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}