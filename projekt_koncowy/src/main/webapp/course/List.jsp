<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Course Items</title>
            <link rel="stylesheet" type="text/css" href="/aProjektKoncowy025/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Course Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Course Items Found)<br />" rendered="#{course.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{course.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{course.pagingInfo.firstItem + 1}..#{course.pagingInfo.lastItem} of #{course.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{course.prev}" value="Previous #{course.pagingInfo.batchSize}" rendered="#{course.pagingInfo.firstItem >= course.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{course.next}" value="Next #{course.pagingInfo.batchSize}" rendered="#{course.pagingInfo.lastItem + course.pagingInfo.batchSize <= course.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{course.next}" value="Remaining #{course.pagingInfo.itemCount - course.pagingInfo.lastItem}"
                                   rendered="#{course.pagingInfo.lastItem < course.pagingInfo.itemCount && course.pagingInfo.lastItem + course.pagingInfo.batchSize > course.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{course.courseItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="CityOfCours"/>
                            </f:facet>
                            <h:outputText value="#{item.cityOfCours}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Phone"/>
                            </f:facet>
                            <h:outputText value="#{item.phone}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Email"/>
                            </f:facet>
                            <h:outputText value="#{item.email}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Info"/>
                            </f:facet>
                            <h:outputText value="#{item.info}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="AddressOfRunOffice"/>
                            </f:facet>
                            <h:outputText value="#{item.addressOfRunOffice}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="CityOfRunOffice"/>
                            </f:facet>
                            <h:outputText value="#{item.cityOfRunOffice}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Climb"/>
                            </f:facet>
                            <h:outputText value="#{item.climb}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DateOfStart"/>
                            </f:facet>
                            <h:outputText value="#{item.dateOfStart}">
                                <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ShortName"/>
                            </f:facet>
                            <h:outputText value="#{item.shortName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="RunnersLimit"/>
                            </f:facet>
                            <h:outputText value="#{item.runnersLimit}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Distance"/>
                            </f:facet>
                            <h:outputText value="#{item.distance}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Descent"/>
                            </f:facet>
                            <h:outputText value="#{item.descent}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="BusinessKey"/>
                            </f:facet>
                            <h:outputText value="#{item.businessKey}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="BusinessKey"/>
                            </f:facet>
                            <h:outputText value="#{item.businessKey}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{course.detailSetup}">
                                <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['pl.java.biniek.DAO.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][course.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{course.editSetup}">
                                <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['pl.java.biniek.DAO.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][course.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{course.remove}">
                                <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['pl.java.biniek.DAO.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][course.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{course.createSetup}" value="New Course"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
