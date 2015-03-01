<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
        <form:form method="post" modelAttribute="creation" action="creerCreationPays">
              <spring:message code="creation.pays.libelle.code" />
            <form:input path="code"/>
            <b><i><form:errors path="code" cssclass="error"/></i></b><br>
            <spring:message code="creation.pays.libelle.nom" />
            <form:input path="nom"/>
            <b><i><form:errors path="nom" cssclass="error"/></i></b><br>
              <spring:message code="creation.pays.libelle.langue"/>
            <form:input path="langue"/>
            <b><i><form:errors path="langue" cssclass="error"/></i></b><br>
            <input type="submit"/>
        </form:form>
        <table border="1">
            <thead>
                <tr>
                    <th><spring:message code="colonne.pays.code"/></th>
                    <th><spring:message code="colonne.pays.nom"/></th>
                    <th><spring:message code="colonne.pays.langue"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeDesPays}" var="pays">
                    <tr>
                        <td><c:out value="${pays.code}"/></td>
                        <td><c:out value="${pays.nom}"/></td>
                        <td><c:out value="${pays.langue}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

