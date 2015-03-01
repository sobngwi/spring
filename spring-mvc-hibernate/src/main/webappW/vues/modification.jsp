<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <form:form method="post" modelAttribute="modification" action="modifierModificationListePays">
            <table border="1">
                <thead>
                    <tr>
                        <th><spring:message code="colonne.pays.code"/></th>
                        <th><spring:message code="colonne.pays.nom"/></th>
                        <th><spring:message code="colonne.pays.langue"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${modification.listeDesPays}" var="pays" varStatus="status">
                        <tr>
                            <td>
                                <c:out value="${pays.code}"/>
                                <input type="hidden" name="listeDesPays[${status.index}].code" value="${pays.code}"/>
                            </td>
                            <td>
                                <c:out value="${pays.nom}"/>
                                <input name="listeDesPays[${status.index}].nom" value="${pays.nom}"/>
                            </td>
                            <td>
                                <input type="text" name="listeDesPays[${status.index}].langue" value="${pays.langue}"/><br/>
                                
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit"/>
        </form:form>

