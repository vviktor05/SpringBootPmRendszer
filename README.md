# Spring Boot Projektmenedzsment rendszer
Az első Spring Boot projektem.

## Helyi indítás:

A működéshez az üres adatbázisnak léteznie kell lokálisan PostgreSQL szerveren "pmrendszer" néven. 

Első indításnál src/main/resources/application-dev.properties fájlban át kell állítani jpa.hibernate.ddl-auto=create és datasource.initialization-mode=always állapotba, hogy automatikusan létrehozza az adatbázis struktúrát és feltöltse tesztadatokkal.

Második indítás elött a jpa.hibernate.ddl-auto=update és datasource.initialization-mode=never állapotba kell állítani.

## Teszt adatok:

felhasználónév: manager@gmail.com
jelszó: admin

felhasználónév: leader@gmail.com
jelszó: admin

felhasználónév: developer@gmail.com
jelszó: admin

## Live szerver URL: 

https://pmrendszer.herokuapp.com

###### Swagger UI:

https://pmrendszer.herokuapp.com/swagger-ui.html

A frontend része a projektnek React-ban készűl és a webapp mappában található. Ez még kezdetleges, fejlesztés alatt áll. Indításkor a Maven automatikusan legenerálja belőle a buildet és átmásolja a megfelelő helyre.

# CRUD REST-API útvonalak:

## GET

http://localhost:8080/api/project_manager/projects

http://localhost:8080/api/project_manager/projects/active

http://localhost:8080/api/project_manager/projects/id/{id}

http://localhost:8080/api/project_manager/projects/name/{name}

http://localhost:8080/api/project_manager/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

0-tól az összes paraméter használatával lehet szűrni, sorrend nem számít.

http://localhost:8080/api/team_leader/projects

http://localhost:8080/api/team_leader/projects/active

http://localhost:8080/api/team_leader/projects/id/{id}

http://localhost:8080/api/team_leader/projects/name/{name}

http://localhost:8080/api/team_leader/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

http://localhost:8080/api/developer/projects

Azokat a projekteket adja vissza amelyeken a bejelentkezett fejlesztő dolgozik.

http://localhost:8080/api/developer/projects/active

http://localhost:8080/api/developer/projects/id/{id}

http://localhost:8080/api/developer/projects/name/{name}

http://localhost:8080/api/developer/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1

http://localhost:8080/api/project_manager/teams

http://localhost:8080/api/project_manager/teams/id/{id}

http://localhost:8080/api/project_manager/teams/working_on/project_id/{id}

A megadott id-vel rendelkező projekten dolgozó csapatokat adja vissza.

http://localhost:8080/api/project_manager/teams/not_working_on/project_id/{id}

http://localhost:8080/api/team_leader/teams

http://localhost:8080/api/team_leader/teams/id/{id}

http://localhost:8080/api/team_leader/teams/working_on/project_id/{id}

http://localhost:8080/api/team_leader/teams/not_working_on/project_id/{id}

http://localhost:8080/api/project_manager/customers/customers

http://localhost:8080/api/project_manager/customers/customers/{id}

http://localhost:8080/api/project_manager/tasks

http://localhost:8080/api/project_manager/tasks/active

http://localhost:8080/api/project_manager/tasks/id/{id}

http://localhost:8080/api/project_manager/tasks/topic/{topic}

http://localhost:8080/api/team_leader/tasks

A csapatvezető csak azokat a feladatokat látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

http://localhost:8080/api/team_leader/tasks/active

http://localhost:8080/api/team_leader/tasks/id/{id}

http://localhost:8080/api/team_leader/tasks/topic/{topic}

http://localhost:8080/api/developer/tasks

A fejlesztő csak azokat a feladatokat látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

http://localhost:8080/api/developer/tasks/active

http://localhost:8080/api/developer/tasks/id/{id}

http://localhost:8080/api/developer/tasks/topic/{topic}

http://localhost:8080/api/project_manager/reports

http://localhost:8080/api/project_manager/reports/id/{id}

http://localhost:8080/api/team_leader/reports

A csapatvezető csak azokat a jelentéseket látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

http://localhost:8080/api/team_leader/reports/id/{id}

http://localhost:8080/api/project_manager/employees

http://localhost:8080/api/project_manager/employees/id/{id}

http://localhost:8080/api/project_manager/employees/in_team/team_id/{id}

A megadott csapat id-vel rendelkező csapatban dolgozó fejlesztőket adja vissza.

http://localhost:8080/api/project_manager/employees/not_in_team/team_id/{id}

http://localhost:8080/api/team_leader/employees

A csapatvezető csak azokat a fejlesztőket látja akik a csapatában vannak.

http://localhost:8080/api/team_leader/employees/id/{id}

http://localhost:8080/api/project_manager/team_memberships

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}

http://localhost:8080/api/api/project_manager/development_areas

http://localhost:8080/api/api/project_manager/development_areas

http://localhost:8080/api/api/project_manager/project_statuses

http://localhost:8080/api/api/project_manager/priorities

http://localhost:8080/api/api/project_manager/statuses

## POST

http://localhost:8080/api/project_manager/projects

http://localhost:8080/api/project_manager/customers

http://localhost:8080/api/project_manager/tasks

http://localhost:8080/api/team_leader/tasks

A csapatvezető csak a saját projektjéhez tud hozzáadni feladatot.

http://localhost:8080/api/project_manager/reports

http://localhost:8080/api/team_leader/reports

A csapatvezető csak a saját projektjéhez tud hozzáadni jelentést.

http://localhost:8080/api/project_manager/employees

A jelszót nem kell megadni, alapértelmezetten a kezdés dátuma lesz.

http://localhost:8080/api/project_manager/teams

http://localhost:8080/api/project_manager/team_memberships

http://localhost:8080/api/project_manager/projects_teams

## PUT

http://localhost:8080/api/project_manager/projects/{id}

http://localhost:8080/api/project_manager/customers/{id}

http://localhost:8080/api/project_manager/tasks/{id}

http://localhost:8080/api/team_leader/tasks/{id}

A csapatvezető csak a saját maga által felvitt feladatot tudja módosítani.

http://localhost:8080/api/project_manager/reports/{id}

http://localhost:8080/api/team_leader/reports/{id}

A csapatvezető csak a saját maga által felvitt jelentést tudja módosítani.

http://localhost:8080/api/project_manager/employees/{id}

http://localhost:8080/api/project_manager/teams/{id}

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}

## DELETE

http://localhost:8080/api/project_manager/projects/{id}

http://localhost:8080/api/project_manager/customers/{id}

http://localhost:8080/api/project_manager/tasks/{id}

http://localhost:8080/api/team_leader/tasks/{id}

A csapatvezető csak a saját maga által felvitt feladatot tudja törölni.

http://localhost:8080/api/project_manager/reports/{id}

http://localhost:8080/api/team_leader/reports/{id}

A csapatvezető csak a saját maga által felvitt jelentést tudja törölni.

http://localhost:8080/api/project_manager/employees/{id}

http://localhost:8080/api/project_manager/teams/{id}

http://localhost:8080/api/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}

http://localhost:8080/api/project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}