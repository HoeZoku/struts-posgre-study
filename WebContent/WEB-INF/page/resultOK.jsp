<?xml version ="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C // DTD XHTML 1.0 Transitional //EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11-transitional.dtd">
<!-- 画面の文字コード指定　UTF-8で表示する -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<% String msg = (String)request.getAttribute("msg"); %>

<!-- Strutsタグ -->
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!-- 画面作成の開始 -->
<html>
<head>
	<meta http-equiv="Content-Type" content ="text/html" charset="UTF-8" />
	<title>社員管理システム</title>
	<link rel="stylesheet" href="color.css"type="text/css" >
</head>

	<body>
	<center>
		<table>
				<colgroup span="5" width="150" align="center">
					<!-- テーブル（明細）部 -->
					<tbody>
						<tr><td><%=msg %>が完了しました。(今のところnullで問題ない)
						</td></tr>
					</tbody>
		</table>

	<!-- 改行タグ -->
	<br>
		<!-- ボタン部 -->
		<div style="display:inline-flex">
			<html:form method="post" action="start.do">
				<input type="submit" value="一覧に戻る"></input>
			</html:form>
		</div>
	</center>

</body>
</html>
<!-- 画面作成の終了 -->