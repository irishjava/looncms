<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml" %>
<%@ page session="false"%>
<%@page import="uk.mafu.loon.fletcher.front.FletcherService"%>
<%@page import="uk.mafu.domain.fletcher.ProjectCategory"%>
<%@page import="uk.mafu.domain.fletcher.Project"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
 
<%
	FletcherService service = (FletcherService) request.getAttribute("fletcherService");
	List<ProjectCategory> projectCategories = service.listProjectCategories();
	Iterator <ProjectCategory> categoryIterator = projectCategories.iterator();
	ProjectCategory currentCategory;
	Project currentProject;
 %>

<%@page import="uk.mafu.domain.fletcher.Press"%><navigation>
	<sections>
		<section alias="homeSection">com.stephenfletcher.display.sections.HomeSection</section>
		<section alias="projectsByCategorySection">com.stephenfletcher.display.sections.ProjectsByCategorySection</section>
		<section alias="practiceSection">com.stephenfletcher.display.sections.PracticeSection</section>
		<section alias="pressSection">com.stephenfletcher.display.sections.PressSection</section>
		<section alias="contactSection">com.stephenfletcher.display.sections.ContactSection</section>
	</sections>
	
	<hierarchy>
		<childern>
			<child>
				<display>
					<title>Home</title>
				</display>
				<section>homeSection</section>
				<permalink>home</permalink>
				<autoselect>0</autoselect>
				<variables>
					<catId>1</catId>
				</variables>
			</child>
			<child>
				<display>
					<title>Projects</title>
				</display>
				<section/>
				<permalink>projects</permalink>
				<autoselect>0</autoselect>
				<childern>
				
				<% while(categoryIterator.hasNext()){%>
				<% currentCategory = (ProjectCategory) categoryIterator.next();%>
					<child>
						<class><%= currentCategory.getClass().getCanonicalName() %></class>
						<display>
							<title><%= currentCategory.getTitle() %></title>
						</display>
						<section>projectsByCategorySection</section>
						<permalink><%= currentCategory.getPermalink() %></permalink>
						<autoselect>0</autoselect>
						<variables>
							<catId><%= currentCategory.getPk() %></catId>
							<catPermalink><%= currentCategory.getPermalink() %></catPermalink>
						</variables>
						<childern>
							<% List<Project> projects = service.listProjectsByProjectCategory(currentCategory.getPk());
								Iterator <Project>projectIterator = projects.iterator();
								while(projectIterator.hasNext())
								{
									currentProject = (Project) projectIterator.next(); %>
							<child>
								<class><%= currentProject.getClass().getCanonicalName() %></class>
								<display>
									<title><%= currentProject.getTitle() %> </title>
								</display>
								<section>projectsByCategorySection</section>
								<permalink><%= currentProject.getPermalink()%></permalink>
								<autoselect>0</autoselect>
								<variables>
									<projectId><%= currentProject.getPk() %> </projectId>
								</variables>
								<childern></childern>
							</child>
							<%}%>
						</childern>
					</child>
				<%}%>
				
				</childern>
				</child>
			<child>
				<display>
					<title>Practice</title>
				</display>
				<section>practiceSection</section>
				<permalink>practice</permalink>
				<autoselect>0</autoselect>
			</child>
			<child>
				<display>
					<title>Press</title>
				</display>
				<section>pressSection</section>
				<permalink>press</permalink>
				<autoselect>0</autoselect>
				<childern>
				<% 	List<Press> pressItems = service.listPress();
					Iterator <Press>pressIterator = pressItems.iterator();
					while(pressIterator.hasNext()){
					Press currentPress = (Press) pressIterator.next();%>
					<child>
						<class><%= currentPress.getClass().getCanonicalName() %></class>
						<display>
							<title><%= currentPress.getTitle() %></title>
						</display>
						<section>pressSection</section>
						<permalink><%= currentPress.getPermalink()%></permalink>
						<autoselect>0</autoselect>
						<variables>
							<pressId><%= currentPress.getPk() %></pressId>
						</variables>
					</child>
					<%}%>
				</childern>
			</child>
			<child>
				<display>
					<title>Contact</title>
				</display>
				<section>contactSection</section>
				<permalink>contact</permalink>
				<autoselect>0</autoselect>
			</child>
		</childern>
	</hierarchy>
</navigation>