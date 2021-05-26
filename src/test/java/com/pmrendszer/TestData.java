package com.pmrendszer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pmrendszer.domain.Customer;
import com.pmrendszer.domain.DevelopmentArea;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.domain.Job;
import com.pmrendszer.domain.Priority;
import com.pmrendszer.domain.Project;
import com.pmrendszer.domain.ProjectStatus;
import com.pmrendszer.domain.Skill;
import com.pmrendszer.domain.Status;

public interface TestData {
	public Customer CUSTOMER = new Customer(1, "Takacs Kft.", "06302425344", "takacs@gmail.com", "www.takacs.hu",
			"2322", "Foktő", "Petőfi Sándor utca 230.");

	public DevelopmentArea DEVELOPMENT_AREA = new DevelopmentArea(1, "Asztali alkalmazás");

	public ProjectStatus PROJECT_STATUS = new ProjectStatus(1, "Vevői Specifikáció");

	public Priority PRIORITY = new Priority(1, "Alacsony");

	public Job JOB = new Job(1, "Projektvezető");

	public Skill SKILL = new Skill(5, "Arhitect");
	
	public Status STATUS = new Status(3, "Folyamatban");

	public Employee EMPLOYEE = new Employee(
			1, 
			"Horváth Krisztina", 
			"manager@gmail.com",
			"$2a$10$PMZZVq5qVaC7BrvUKx889O/OpSR15ojylURVvSx.b61t/tZRtQ7jC", 
			JOB, DEVELOPMENT_AREA, 
			SKILL,
			LocalDate.of(2020, 02, 01), 
			"06701122345", 
			LocalDateTime.of(2020, 03, 07, 19, 28, 22)
			);

	public Project PROJECT = new Project(
			1, 
			"Nyílvántartó rendszer", 
			TestData.CUSTOMER, 
			TestData.DEVELOPMENT_AREA,
			LocalDate.of(2020, 02, 01),
			LocalDate.of(2020, 07, 21),
			TestData.PROJECT_STATUS,
			TestData.PRIORITY,
			TestData.EMPLOYEE,
			TestData.STATUS,
			"A BMR ügyviteli rendszer a magyar kisvállalkozások igényeire kialakított megoldás, amely ötvözi a nagyvállalati rendszerek kényelmi funkciót az egyszerűsített, logikus folyamatokkal és gyors, költséghatékony bevezetéssel. Fontosabb funkciói: tételek egyszerű, gyors felvitele; korlátlan számú vállalkozás könyvelése; eredménykimutatás; bérszámfejtés; 30 napos áfa figyelése; tárgyieszköz nyilvántartás."
			);
}