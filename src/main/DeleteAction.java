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
 * ユーザ削除クラス
 */
public class DeleteAction extends Action{

	//なぜActionForwardなのかわかってない。ロジックを含まないってあるけど思いっきり含んでいる気がする・・・。
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//自作フォームを使用する準備
		MemberBean formBean = (MemberBean)form;

		//DAOから接続取得
		DbConnection db = new DbConnection();

		// 「gflmember」の該当レコードを削除する。
		int result = db.deleteExecute(formBean.getId());

		// 更新が正常に行われたかを判定する。
		if (result == 1) {

			// ☆☆☆正常ケース☆☆☆

			// 画面に表示するため「request」に設定する。
			//今は画面に送ってないのでNullになるけど
			//エラー処理実装まで放置
			request.setAttribute("msg", "削除");

			// OK画面にフォワード
			return mapping.findForward("success");


		} else {
			System.out.println("削除失敗");

			// ◆◆◆異常ケース◆◆◆

			// 異常の場合は「社員一覧画面」へ遷移するので、再度社員一覧を取得する。
			List<MemberBean> list = db.selectExecute();

			// 画面に表示するため「request」に設定する。
			request.setAttribute("members", list);
			request.setAttribute("errorMsg", "削除処理に失敗しました。");

			//ひとまず成功とする
			return mapping.findForward("success");

		}
	}
}
