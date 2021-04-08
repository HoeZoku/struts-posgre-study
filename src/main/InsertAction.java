package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.MemberBean;
import dao.DbConnection;

/**
 * 新規登録用のAction
 */
public class InsertAction extends Action{

	//なぜActionForwardなのかわかってない。ロジックを含まないってあるけど思いっきり含んでいる気がする・・・。
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//自作フォームを使用する準備
		MemberBean formBean = (MemberBean)form;

		//DAOから接続取得
		DbConnection db = new DbConnection();

		//現状BeanのIDはnullなのでDBから新規用IDを取得してぶち込む
		int newID =  db.selectMaxID();
		formBean.setId(newID);

		// 登録処理
		int result = db.insertExecute(formBean);

		// 登録が正常に行われたかを判定する。
		if (result == 1) {

			// ☆☆☆正常ケース☆☆☆

			// 画面に表示するため「request」に設定する。
			request.setAttribute("msg", "登録");

			// 社員一覧画面にフォワード
			return mapping.findForward("success");

		} else {

			// ◆◆◆異常ケース◆◆◆

			// 画面に表示するため「request」に設定する。
			request.setAttribute("errorMsg", "登録処理に失敗しました。");

			// エラー処理実装するまでとりあえず成功とする・・・。
			System.out.println("登録ミスってますよ・・・");
			return mapping.findForward("success");
		}
	}

}
