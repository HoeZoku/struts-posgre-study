package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.MemberBean;

public class DbConnection {

	// クラス変数
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * DB接続初期化メソッド
	 * JDNIを利用
	 * @return Connection
	 */
	private Connection initConnection()  {

		// JNDIでぐぐれ
		Connection con = null;
		InitialContext ic = null;
		DataSource ds = null;

		try {
			if(con == null) {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/postgres");
				con = ds.getConnection();
			}

		} catch ( Exception e ) {
			e.printStackTrace() ;
		}
		return con;
	}


	/**
	 * SELECT文作成、実行メソッド(メンバーテーブルに登録されている全てのレコードを取得する)
	 *
	 * @return List<MemberBean>
	 */
	public List<MemberBean> selectExecute() {

		Connection sCon = initConnection();

		List<MemberBean> list = new ArrayList<MemberBean>();

		try {

			String sql = "select id, name, namekana, yakusyoku, nickname from gflmember";
			ps = sCon.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean slb = new MemberBean();
				slb.setId(rs.getInt("id"));
				slb.setName(rs.getString("name"));
				slb.setNameKana(rs.getString("namekana"));
				slb.setYakusyoku(rs.getString("yakusyoku"));
				slb.setNickName(rs.getString("NickName"));

				list.add(slb);
			}

		} catch (Exception e) {
			System.out.println("社員一覧の取得に失敗しました。");

		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
	}

	/**
	 * SELECT文作成、実施メソッド(メンバーテーブルに登録されている最大ID+1の値を取得する)
	 * ここは元のまま
	 * @return　int MaxID
	 */
	public int selectMaxID() {

		// 取得IDの初期化
		int maxID = 1;

		// DB接続初期化メソッド
		Connection sCon = initConnection();

		try {
			// テーブルへ実施するSQLを作成する。
			String sql = "select id from gflmember order by id desc";

			// SQL実施、結果を格納する。
			Statement stmt = sCon.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				maxID = rs.getInt("id") + 1;
				break;
			}

			// DB接続、またはSQL実施にてエラーになった場合はコンソールにエラーメッセージを出力する。
			// エラーの場合は「1」を返却する。
		} catch (Exception e) {
			System.out.println("社員ＩＤの取得に失敗しました。");
			maxID = 1;

			// DB接続の後処理
			// 処理が正常、異常関係なく必ず実行される。
		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// 処理完了、取得したIDを返却する。
		return maxID;
	}


	/**
	 * INSERT文作成、実施メソッド(追加画面から呼び出す)
	 *
	 * @return　ResultSet
	 */
	public int insertExecute(MemberBean bean) {

		// DBへの接続処理　開始
		Connection sCon = initConnection();
		PreparedStatement ps = null;

		// 処理の結果値
		int result = -1;

		try{

			// INSERT文の作成
			String sql = "INSERT INTO gflmember VALUES(?,?,?,?,?)";
			sCon.prepareStatement(sql);

			// テーブルへ実施するSQLを作成する。
			ps = sCon.prepareStatement(sql);

			// 「？」へ値を設定する。右から１～で設定する。
			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getNameKana());
			ps.setString(4, bean.getYakusyoku());
			ps.setString(5, bean.getNickName());

			// SQL実施、結果は数値で帰ってくる。
			// 正常終了の場合は、処理したレコード数（複数レコードは処理されないので基本「１」）が返却される。
			result = ps.executeUpdate();

			// DB接続、またはSQL実施にてエラーになった場合はコンソールにエラーメッセージを出力する。
			// エラーの場合は「-1」を返却する。
		}catch(Exception e){
			System.out.println("社員情報の登録に失敗しました。");
			result = -1;

			// DB接続の後処理
			// 処理が正常、異常関係なく必ず実行される。
		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// 処理完了、登録処理結果を返却する。
		return result;
	}


	/**
	 * SELECT文作成、実施メソッド(引数で指定されたBeanのIDを持つレコードを取得する)
	 * 更新画面・削除画面の表示時に使用
	 *
	 * @return　ResultSet
	 */
	public MemberBean selectUserInfo(MemberBean form) {

		// DB接続初期化メソッド
		Connection sCon = initConnection();

		try {

			// テーブルへ実施するSQLを作成する。
			String sql = "select id, name, namekana, yakusyoku, nickname from gflmember where id = ?";
			sCon.prepareStatement(sql);

			// テーブルへ実施するSQLを作成する。
			ps = sCon.prepareStatement(sql);
			ps.setInt(1, form.getId());

			// SQL実施、結果を「ResultSet」に格納する。
			rs = ps.executeQuery();
			while (rs.next()) {
				form.setId(rs.getInt("id"));
				form.setName(rs.getString("name"));
				form.setNameKana(rs.getString("namekana"));
				form.setYakusyoku(rs.getString("yakusyoku"));
				form.setNickName(rs.getString("NickName"));
			}

			// DB接続、またはSQL実施にてエラーになった場合はコンソールにエラーメッセージを出力する。
		} catch (Exception e) {
			System.out.println("社員情報の取得に失敗しました。");

			// DB接続の後処理
			// 処理が正常、異常関係なく必ず実行される。
		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// 処理完了、SQL実施結果を返却する。
		return form;
	}

	/**
	 * DELETE文作成、実施メソッド(削除画面から呼び出す)
	 * 特に書き換えなし
	 *
	 * @return　ResultSet
	 */
	public int deleteExecute(int id) {

		// DBへの接続処理　開始
		Connection sCon = initConnection();
		PreparedStatement ps = null;

		// 処理の結果値
		int result = -1;

		try{

			// DELETE文の作成
			String sql = "DELETE FROM gflmember where id=?";
			sCon.prepareStatement(sql);

			// テーブルへ実施するSQLを作成する。
			ps = sCon.prepareStatement(sql);

			// 「？」へ値を設定する。
			ps.setInt(1, id);

			// SQL実施、結果は数値で帰ってくる。
			// 正常終了の場合は、処理したレコード数（複数レコードは処理されないので基本「１」）が返却される。
			result = ps.executeUpdate();

			// DB接続、またはSQL実施にてエラーになった場合はコンソールにエラーメッセージを出力する。
			// エラーの場合は「-1」を返却する。
		}catch(Exception e){
			System.out.println("社員情報の削除に失敗しました。");
			result = -1;

			// DB接続の後処理
			// 処理が正常、異常関係なく必ず実行される。
		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// 処理完了、削除処理結果を返却する。
		return result;
	}


	/**
	 * UPDATE文作成、実施メソッド(更新画面から呼び出す)
	 *
	 * @return　ResultSet
	 */
	public int update(MemberBean bean){

		// DBへの接続処理　開始
		Connection sCon = initConnection();
		PreparedStatement ps = null;

		// 処理の結果値
		int result = -1;

		try{

			// UPDATE文の作成
			String sql = "UPDATE gflmember SET name=?,namekana=?,yakusyoku=?,nickname=? WHERE id=?";
			sCon.prepareStatement(sql);

			// テーブルへ実施するSQLを作成する。
			ps = sCon.prepareStatement(sql);

			// 「？」へ値を設定する。右から１～で設定する。
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getNameKana());
			ps.setString(3, bean.getYakusyoku());
			ps.setString(4, bean.getNickName());
			ps.setInt(5, bean.getId());

			// SQL実施、結果は数値で帰ってくる。
			// 正常終了の場合は、処理したレコード数（複数レコードは処理されないので基本「１」）が返却される。
			result = ps.executeUpdate();

			// DB接続、またはSQL実施にてエラーになった場合はコンソールにエラーメッセージを出力する。
			// エラーの場合は「-1」を返却する。
		}catch(Exception e){
			System.out.println("社員情報の更新に失敗しました。");
			result = -1;

			// DB接続の後処理
			// 処理が正常、異常関係なく必ず実行される。
		} finally {
			if (sCon != null) {
				try {
					sCon.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// 処理完了、更新処理結果を返却する。
		return result;
	}



}
