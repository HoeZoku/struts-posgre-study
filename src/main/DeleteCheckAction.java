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
 * 削除画面遷移前に削除対象IDがあるか確認するクラス。
 *
 */
public class DeleteCheckAction extends Action{

	//MemberBeanを使ってるが遷移前にはIDしか利用しない・・・。
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//自作フォームを使用する準備
		MemberBean formBean = (MemberBean)form;

		//DAOから接続取得
		DbConnection db = new DbConnection();

		// 「社員一覧画面」で削除対象のＩＤが選択されているかチェックする。
		if (formBean.getId() > 0) {

			// ☆☆☆正常ケース☆☆☆

			// 削除対象のＩＤを条件に「gflmember」からレコードを1件取得する。
			MemberBean bean = db.selectUserInfo(formBean);
			//画面に返すBeanを更新
			formBean.setNameKana(bean.getNameKana());
			formBean.setYakusyoku(bean.getYakusyoku());
			formBean.setNickName(bean.getNickName());

			// 画面メッセージを初期化する。
			request.setAttribute("errorMsg", "");

			// 社員削除画面にフォワード
			return mapping.findForward("success");

		} else {

			System.out.println("ID取得失敗・・・");
			System.out.println("ID= " + formBean.getId());
			//未実装なのでバグる。ひとまず放置

			// ◆◆◆異常ケース◆◆◆

			// 画面に表示するため「request」に設定する。
			request.setAttribute("errorMsg", "削除するメンバーを選択してください。");

			return mapping.findForward("");

		}
	}
}

