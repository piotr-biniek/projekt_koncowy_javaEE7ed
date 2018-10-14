<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Course</title>
            <link rel="stylesheet" type="text/css" href="/aProjektKoncowy025/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Course</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{course.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="CityOfCours:"/>
                    <h:inputText id="cityOfCours" value="#{course.course.cityOfCours}" title="CityOfCours" required="true" requiredMessage="The cityOfCours field is required." />
                    <h:outputText value="Phone:"/>
                    <h:inputText id="phone" value="#{course.course.phone}" title="Phone" />
                    <h:outputText value="Email:"/>
                    <h:inputText id="email" value="#{course.course.email}" title="Email" />
                    <h:outputText value="Info:"/>
                    <h:inputText id="info" value="#{course.course.info}" title="Info" />
                    <h:outputText value="AddressOfRunOffice:"/>
                    <h:inputText id="addressOfRunOffice" value="#{course.course.addressOfRunOffice}" title="AddressOfRunOffice" />
                    <h:outputText value="CityOfRunOffice:"/>
                    <h:inputText id="cityOfRunOffice" value="#{course.course.cityOfRunOffice}" title="CityOfRunOffice" />
                    <h:outputText value="Climb:"/>
                    <h:inputText id="climb" value="#{course.course.climb}" title="Climb" />
                    <h:outputText value="DateOfStart (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="dateOfStart" value="#{course.course.dateOfStart}" title="DateOfStart" required="true" requiredMessage="The dateOfStart field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="ShortName:"/>
                    <h:inputText id="shortName" value="#{course.course.shortName}" title="ShortName" required="true" requiredMessage="The shortName field is required." />
                    <h:outputText value="RunnersLimit:"/>
                    <h:inputText id="runnersLimit" value="#{course.course.runnersLimit}" title="RunnersLimit" />
                    <h:outputText value="Distance:"/>
                    <h:inputText id="distance" value="#{course.course.distance}" title="Distance" required="true" requiredMessage="The distance field is required." />
                    <h:outputText value="Descent:"/>
                    <h:inputText id="descent" value="#{course.course.descent}" title="Descent" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{course.course.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="BusinessKey:"/>
                    <h:outputText value="#{course.course.businessKey}" title="BusinessKey" />
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{course.course.id}" title="Id" />
                    <h:outputText value="BusinessKey:"/>
                    <h:outputText value="#{course.course.businessKey}" title="BusinessKey" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{course.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{course.listSetup}" value="Show All Course Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
