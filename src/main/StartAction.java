package main;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.MemberBean;
import dao.DbConnection;

/**
 * ログイン成功後、社員一覧画面を表示させるためのAction
 */
public class StartAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//DAOから接続取得
		DbConnection db = new DbConnection();

		//全レコードを取得
		List<MemberBean> list = db.selectExecute();

		// 画面メッセージを初期化する。(いるのかわからないのでとりあえず残す）
		//登録等した後このアクション呼び出す仕様にしたので、これあるとメッセージ消える・・・
		request.setAttribute("errorMsg", "");

		//一覧の入ったコレクションをMemberListの名でJsp側で使えるようにする
		 request.setAttribute("MemberList", list);

		// 社員一覧画面にフォワード
		return mapping.findForward("success");
	}

}
