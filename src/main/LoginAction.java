package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.LoginBean;

/**
 *ログイン用Actionクラス
 *この処理だけDAOとは切り離してるけど理由が分からん
 */
public class LoginAction extends Action{

	//なぜActionForwardなのかわかってない。ロジックを含まないってあるけど思いっきり含んでいる気がする・・・。
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		//自作フォームを使用するにはexecuteメソッド引数のActionFormを、
		//自作のフォームとしてキャスト(ActionForm継承してる)して、前に作成した取得メソッドを適用する。らしい。
		LoginBean formBean = (LoginBean)form;
		int id = formBean.getId();
		String pass = formBean.getPass();

		// JNDIでぐぐれ
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		boolean loginNG = true;

		try {
			if (con == null) {

				//JDNIでぐぐれ
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/postgres");
				con = ds.getConnection();

				// PreparedStatementが主流らしい
				String sql = "select * from logininfo where id = ? and password = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, pass);

				ResultSet rs = ps.executeQuery();

				// レコードがある＝ログイン成功
				while (rs.next()) {
					loginNG = false;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		if (loginNG) {

			// ◆◆◆異常ケース◆◆◆エラー処理入れるまではひとまず成功時の遷移とする！
			System.out.println("ログインは失敗してる・・・。");
			return mapping.findForward("success");

		} else {
			// ☆☆☆正常ケース☆☆☆
			//フォワード先ではさらにアクションを呼ぶ
			return (mapping.findForward("success"));
		}
	}

}
