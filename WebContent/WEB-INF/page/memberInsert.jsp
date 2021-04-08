<?xml version ="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C // DTD XHTML 1.0 Transitional //EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11-transitional.dtd">
<!-- 画面の文字コード指定　UTF-8で表示する -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<!-- Strutsタグ -->
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<!-- 画面作成の開始 -->
<html:html>
<head>
	<meta http-equiv="Content-Type" content ="text/html" charset="UTF-8" />
	<title>GFL 社員管理システム</title>
	<link rel="stylesheet" href="color.css"type="text/css" >
</head>

<body>
	<html:form method="post" action="/insert">
	<center>
		<table border="1">
			<caption><b>GFL　社員登録</b></caption>

				<colgroup span="5" width="150" align="center">

				<!-- テーブルヘッダ -->
				<thead>
					<tr style="background-color:#EDF7FF;">
						<th>名前</th>
						<th>名前カナ</th>
						<th>役職</th>
						<th>愛称</th>
					</tr>
				</thead>

					<!-- テーブル（明細）部 -->
					<tbody>
						  <tr>
						    <td><html:text property="name" name="name" value=""/></td>
						    <td><html:text property="nameKana" name="nameKana" value="" /></td>
						    <td><html:text property="yakusyoku" name="yakusyoku" value=""/></td>
						    <td><html:text property="nickName" name="nickName" value=""/></td>
						  </tr>
					</tbody>
		</table>

	<!-- 改行タグ -->
	<br>

		<!-- ボタン部 -->
		<div style="display:inline-flex">
			<input type="submit" value="社員登録"></input>
			<button type="button" onclick="history.back()">戻る</button>
		</div>
	</center>
</html:form>
</body>
</html:html>
<!-- 画面作成の終了 -->