
<%@ page import="bloomhealthdemo.ZipCode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'zipCode.label', default: 'ZipCode')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-zipCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-zipCode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <div class="tagCloud">
				<g:each in="${zipCodeMap}" status="i" var="stateInstance">
						<span style="font-size: ${stateInstance.value + 7}pt">${stateInstance.key}</span>
				</g:each>
            </div>

            <g:form>
                <fieldset class="buttons">
                    <g:if test="${zipCodeMap.size() == 0}">
                    <g:link class="edit" action="createZip">Create Cloud</g:link>
                    </g:if>
                    <g:else>
                    <g:link class="delete" action="delete">Delete Cloud</g:link>
                    </g:else>
                </fieldset>
            </g:form>
		</div>
	</body>
</html>
