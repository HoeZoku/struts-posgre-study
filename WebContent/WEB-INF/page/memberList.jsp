<?xml version ="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C // DTD XHTML 1.0 Transitional //EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<!-- jspで書かれたメンバーリスト等のimportは消した -->

<% String errorMsg = (String)request.getAttribute("errorMsg"); %>

<!-- Strutsタグ -->
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<!-- 画面作成の開始 -->
<html:html>

<!-- name属性がradioIDのものを取得→チェックが入っている要素の数だけ引数要素の値に要素の値をぶち込む 。アラートはデバック-->
<script type="text/javascript">
	function checkRadioButton(sid) {
            var elemList = document.getElementsByName("radioID");
            for ( var i = 0 ; i < elemList.length ; i++) {
                if (elemList[i].checked) {
                	var str = elemList[i].value ;
                	alert(str);
                    document.getElementById(sid).value = elemList[i].value;
                    return true;
                }
            }
            return false;
        }
</script>
<head>
	<meta http-equiv="Content-Type" content ="text/html" charset="UTF-8" />
	<title>社員管理システム</title>
	<link rel="stylesheet" href="color.css"type="text/css" >
</head>

	<body>
	<center>
		<table border="1">
			<caption><b>社員一覧</b></caption>

				<colgroup span="5" width="150" align="center">
				<font color="red"><%=errorMsg %></font>
				<!-- テーブルヘッダ -->
				<thead>
					<tr style="background-color:#EDF7FF;">
						<th>選択</th>
						<th>ID</th>
						<th>名前</th>
						<th>名前カナ</th>
						<th>役職</th>
						<th>愛称</th>
					</tr>
				</thead>

					<!-- テーブル（明細）部 -->
					<!-- ラジオボタンがよく分かってない -->
					<tbody>
						<logic:iterate id="onedata" name="MemberList">
						  <tr>
						  	<td><input type="radio" name="radioID"
						  				value =<bean:write name="onedata" property="id"/>
						  			/>
						  	</td>
						    <td><bean:write name="onedata" property="id"/></td>
						    <td><bean:write name="onedata" property="name"/></td>
						    <td><bean:write name="onedata" property="nameKana"/></td>
						    <td><bean:write name="onedata" property="yakusyoku"/></td>
						    <td><bean:write name="onedata" property="nickName"/></td>
						  </tr>
						</logic:iterate>
					</tbody>
		</table>

	<!-- 改行タグ -->
	<br>

		<!-- ボタン部 -->
		<div style="display:inline-flex">

		<!-- アクションに遷移したいだけなのにStrutsタグのFormを使うとBeanがないため例外になるので、通常のFormタグを利用  -->
		<!--フォームである必要も特にないけど一応見た目とEnter反応させるため・・・  -->
		<form method="post" action="goInsert.do">
			<input type="submit" value="社員登録" />
		</form>

		<!-- なぜか分からんがいじってたら動いた。かなり詰まった。各属性が何をさしていて対応関係がどうなるか勉強しなくては。。。
				hiddeenのvalueはJavaScriptでぶち込んでる。その場合””になるらしい？
				 JavaScript見てみたらidの値に/が入っていた。それが原因。ラジオボタンのとこ改行したら行けた。なぜかは分からん・・・ -->
		<html:form method="post" action="deleteCheck" onsubmit="checkRadioButton('checkDelId')">
			<html:hidden property="id" name="checkDelId" styleId="checkDelId" value="" />
			<input type="submit" value="社員削除"></input>
		</html:form>

		<html:form method="post" action="updateCheck" onsubmit="checkRadioButton('checkUplId')">
			<html:hidden property="id" name="checkUplId" styleId="checkUplId" value="" />
			<input type="submit" value="社員更新"></input>
		</html:form>



		</div>
	</center>

</body>
</html:html>
<!-- 画面作成の終了 -->