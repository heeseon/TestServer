<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Multiple File Upload Result</title>
</head>
<body>
    <p>Uploading of file(s)
        <c:forEach items="${files}" var="file">
            ${file.getOriginalFilename()},
        </c:forEach>
   are successful </p>
</body>
</html>