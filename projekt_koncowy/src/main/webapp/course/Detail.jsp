<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Course Detail</title>
            <link rel="stylesheet" type="text/css" href="/aProjektKoncowy025/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Course Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="CityOfCours:"/>
                    <h:outputText value="#{course.course.cityOfCours}" title="CityOfCours" />
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{course.course.id}" title="Id" />
                    <h:outputText value="Phone:"/>
                    <h:outputText value="#{course.course.phone}" title="Phone" />
                    <h:outputText value="Email:"/>
                    <h:outputText value="#{course.course.email}" title="Email" />
                    <h:outputText value="Info:"/>
                    <h:outputText value="#{course.course.info}" title="Info" />
                    <h:outputText value="AddressOfRunOffice:"/>
                    <h:outputText value="#{course.course.addressOfRunOffice}" title="AddressOfRunOffice" />
                    <h:outputText value="CityOfRunOffice:"/>
                    <h:outputText value="#{course.course.cityOfRunOffice}" title="CityOfRunOffice" />
                    <h:outputText value="Climb:"/>
                    <h:outputText value="#{course.course.climb}" title="Climb" />
                    <h:outputText value="DateOfStart:"/>
                    <h:outputText value="#{course.course.dateOfStart}" title="DateOfStart" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="ShortName:"/>
                    <h:outputText value="#{course.course.shortName}" title="ShortName" />
                    <h:outputText value="RunnersLimit:"/>
                    <h:outputText value="#{course.course.runnersLimit}" title="RunnersLimit" />
                    <h:outputText value="Distance:"/>
                    <h:outputText value="#{course.course.distance}" title="Distance" />
                    <h:outputText value="Descent:"/>
                    <h:outputText value="#{course.course.descent}" title="Descent" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{course.course.name}" title="Name" />
                    <h:outputText value="BusinessKey:"/>
                    <h:outputText value="#{course.course.businessKey}" title="BusinessKey" />
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{course.course.id}" title="Id" />
                    <h:outputText value="BusinessKey:"/>
                    <h:outputText value="#{course.course.businessKey}" title="BusinessKey" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{course.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['pl.java.biniek.DAO.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][course.course][course.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{course.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['pl.java.biniek.DAO.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][course.course][course.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{course.createSetup}" value="New Course" />
                <br />
                <h:commandLink action="#{course.listSetup}" value="Show All Course Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
