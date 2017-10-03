<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Integer Spiral</title>
</head>
<body>
	<form id="spiralForm" method="post" action="DoSpiral">
	<br />
		<label for="targetVal" >Target Value:</label>
		<input id="targetVal" name="targetVal" type="text" value="${ param.targetVal }" />
		<span>${ requestScope.inputError }</span><br />
		<br />
		<label for="spiralDir" >Spiral Direction:</label><br />
		<input id="spiralDirR" name="spiralDir" type="radio" value="True" ${param.spiralDir != 'False' ? 'checked' : ''} /> <label for="spiralDirR" >Right</label><br />
		<input id="spiralDirL" name="spiralDir" type="radio" value="False" ${param.spiralDir == 'False' ? 'checked' : ''} /> <label for="spiralDirL" >Left</label><br />
		<br />
		<input type="submit" value="Do Spiral"/><br />
		<span>${ requestScope.error }</span><br />
	</form>
	<hr />
	<br />
	<pre>${ requestScope.spiral }</pre>
</body>
</html>