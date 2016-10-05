<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
    	<jsp:include page="common/head-component.jsp" flush="true"/>
    </head>
    <body>
    	<div id="wrapper">
    		<jsp:include page="common/navbar-component.jsp" flush="true"/>
    		<div id="page-wrapper" class="gray-bg">
    		<jsp:include page="common/pagehead-component.jsp" flush="true"/>
      		<jsp:include page="app/dataimport-content.jsp" flush="true"/>
      			<jsp:include page="common/pagefooter-component.jsp" flush="true"/>
    		</div>
    		<jsp:include page="common/rightsidebar-component.jsp" flush="true"/>
    	</div>
    <jsp:include page="common/foot-component.jsp" flush="true"/>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
 	</body>
</html>