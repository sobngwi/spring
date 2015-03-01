<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <table border="1">
            <thead>
                <tr>
                    <th><spring:message code="colonne.pays.code"/></th>
                    <th><spring:message code="colonne.pays.nom"/></th>
                    <th><spring:message code="colonne.pays.langue"/></th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeDesPays}" var="pays">
                    <tr>
                        <td><c:out value="${pays.code}"/></td>
                        <td><c:out value="${pays.nom}"/></td>
                        <td><c:out value="${pays.langue}"/></td>
                        <td>
                            <c:url value="/supprimerSuppressionListePays" var="url">
                                <c:param name="code" value="${pays.code}"/>
                            </c:url>
                            <a href="${url}">
                                <spring:message code="suppression.supprimer.libelle" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

