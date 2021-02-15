# Spring Boot Projektmenedzsment rendszer

## Live szerver URL: 

**A szerver esetleges lassúsága az ingyenes host miatt fordulhat elő.**

**REST-API:** https://pmrendszer.herokuapp.com

**React:** https://pmrendszer-react.herokuapp.com

A frontend része a projektnek React-ban készül és a *webapp* mappában található. Ez jelenleg még nem használja ki a backend összes funkcióját (fejlesztés alatt áll).

###### Swagger UI: https://pmrendszer.herokuapp.com/swagger-ui.html

## Tesztadat:

e-mail: **manager@gmail.com**

jelszó: **admin**

## Helyi indítás:

**Backend:**

A működéshez az üres adatbázisnak léteznie kell lokálisan PostgreSQL szerveren "**pmrendszer**" néven. 

Első indításnál *src/main/resources/application-dev.properties* fájlban át kell állítani **jpa.hibernate.ddl-auto=create** és **datasource.initialization-mode=always** állapotba, hogy automatikusan létrehozza az adatbázis struktúrát és feltöltse tesztadatokkal.

Második indítás elött a **jpa.hibernate.ddl-auto=update** és **datasource.initialization-mode=never** állapotba kell állítani.

Indítás: `mvn spring-boot:run`

**Frontend:**

Futtatható környezet kialakítása: `npm install`

Indítás: `npm run dev`

# REST-API útvonalak:

*http://localhost:8080/api/...*

## GET

*project_manager/projects*

*project_manager/projects/active*

*project_manager/projects/id/{id}*

*project_manager/projects/name/{name}*

*project_manager/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1*

0-tól az összes paraméter használatával lehet szűrni projektekre, sorrend nem számít.

*project_manager/jobs*

*project_manager/skills*

*team_leader/projects*

*team_leader/projects/active*

*team_leader/projects/id/{id}*

*team_leader/projects/name/{name}*

*team_leader/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1*

*developer/projects*

Azokat a projekteket adja vissza amelyeken a bejelentkezett fejlesztő dolgozik.

*developer/projects/active*

*developer/projects/id/{id}*

*developer/projects/name/{name}*

*developer/projects/search?customerId=1&developmentAreaId=1&orderDateMin=1&orderDateMax=1&projectStatusId=1&priorityId=1&projectLeaderId=1&statusId=1*

*project_manager/teams*

*project_manager/teams/id/{id}*

*project_manager/teams/working_on/project_id/{id}*

A megadott id-vel rendelkező projekten dolgozó csapatokat adja vissza.

*project_manager/teams/not_working_on/project_id/{id}*

*team_leader/teams*

*team_leader/teams/id/{id}*

*team_leader/teams/working_on/project_id/{id}*

*team_leader/teams/not_working_on/project_id/{id}*

*project_manager/customers/customers*

*project_manager/customers/customers/{id}*

*project_manager/tasks*

*project_manager/tasks/active*

*project_manager/tasks/id/{id}*

*project_manager/tasks/topic/{topic}*

*team_leader/tasks*

A csapatvezető csak azokat a feladatokat látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

*team_leader/tasks/active*

*team_leader/tasks/id/{id}*

*team_leader/tasks/topic/{topic}*

*developer/tasks*

A fejlesztő csak azokat a feladatokat látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

*developer/tasks/active*

*developer/tasks/id/{id}*

*developer/tasks/topic/{topic}*

*project_manager/reports*

*project_manager/reports/id/{id}*

*team_leader/reports*

A csapatvezető csak azokat a jelentéseket látja, amelyik olyan projekthez kapcsolódik, amin dolgozik.

*team_leader/reports/id/{id}*

*project_manager/employees*

*project_manager/employees/id/{id}*

*project_manager/employees/in_team/team_id/{id}*

A megadott csapat id-vel rendelkező csapatban dolgozó fejlesztőket adja vissza.

*project_manager/employees/not_in_team/team_id/{id}*

*project_manager/employees/team_leaders*

*team_leader/employees*

A csapatvezető csak azokat a fejlesztőket látja akik a csapatában vannak.

*team_leader/employees/id/{id}*

*project_manager/team_memberships*

*project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}*

*project_manager/projects_teams*

*project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}*

*project_manager/development_areas*

*project_manager/development_areas*

*project_manager/project_statuses*

*project_manager/priorities*

*project_manager/statuses*

*logout*

## POST

*auth/signin*

*project_manager/projects*

*project_manager/customers*

*project_manager/tasks*

*team_leader/tasks*

A csapatvezető csak a saját projektjéhez tud hozzáadni feladatot.

*project_manager/reports*

*team_leader/reports*

A csapatvezető csak a saját projektjéhez tud hozzáadni jelentést.

*project_manager/employees*

A jelszót nem kell megadni, alapértelmezetten a kezdés dátuma lesz.

*project_manager/teams*

*project_manager/team_memberships*

*project_manager/projects_teams*

## PUT

*project_manager/projects/{id}*

*project_manager/customers/{id}*

*project_manager/tasks/{id}*

*team_leader/tasks/{id}*

A csapatvezető csak a saját maga által felvitt feladatot tudja módosítani.

*project_manager/reports/{id}*

*team_leader/reports/{id}*

A csapatvezető csak a saját maga által felvitt jelentést tudja módosítani.

*project_manager/employees/{id}*

*project_manager/employees/team_membership/team_id/{id}*

Elmenti a dolgozókat a csapatba.

*team_leader/employees/team_membership/team_id/{id}*

*project_manager/teams/{id}*

*project_manager/teams/working_on/project_id/{id}*

Elmenti a projekten dolgozó csapatokat.

*team_leader/teams/working_on/project_id/{id}*

*project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}*

*project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}*

## DELETE

*project_manager/projects/{id}*

*project_manager/customers/{id}*

*project_manager/tasks/{id}*

*team_leader/tasks/{id}*

A csapatvezető csak a saját maga által felvitt feladatot tudja törölni.

*project_manager/reports/{id}*

*team_leader/reports/{id}*

A csapatvezető csak a saját maga által felvitt jelentést tudja törölni.

*project_manager/employees/{id}*

*project_manager/teams/{id}*

*project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}*

*project_manager/projects_teams/project_id/{projectId}/team_id/{teamId}*