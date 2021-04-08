<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<% if (errorMsg == null) { errorMsg = ""; } %>

<%-- Strutsタグラ --%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員管理システム</title>
</head>
<body>



<html:form method="post" action="/login">

		<table align="center">
			<tr>
				<td>
					社員管理システム　ログイン
				</td>
			</tr>
		</table>

		<table align="center">

			<!-- エラー処理 -->
			<html:errors />

			<tr>
				<td align="right">
					ＩＤ：<html:text property="id" name="id" value="" size="20"/>(半角数字)
				</td>
			</tr>
			<tr>
				<td align="right">
					パスワード：<html:password property="pass" name="pass" value="" size="20"/>
				</td>
			</tr>
		</table>

		<table align="center">
			<tr>
				<td>
					<button type="submit">ログイン</button>
				</td>
			</tr>
		</table>

	</html:form>
</body>

</body>
</html:html>