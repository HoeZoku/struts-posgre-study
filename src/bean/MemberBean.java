package bean;

import org.apache.struts.action.ActionForm;

//社員情報を格納しておくBean。表示とか受け渡しとか用。
//Sturutsのアクション・フォームBeanの動作させるためActionForm継承。
public class MemberBean extends ActionForm {

	private int id;
	private String name;
	private String nameKana;
	private String yakusyoku;
	private String nickName;
	private String iIndex;

	// IDのゲッター、セッター
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// 名前のゲッター、セッター
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// カナのゲッター、セッター
	public String getNameKana() {
		return nameKana;
	}
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	// 役職のゲッター、セッター
	public String getYakusyoku() {
		return yakusyoku;
	}
	public void setYakusyoku(String yakusyoku) {
		this.yakusyoku = yakusyoku;
	}

	// あだ名のゲッター、セッター
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	// ラジオボタンのゲッター、セッター
	// 何番目のラジオボタンが選択されたか取得できる。
	public String getiIndex() {
		return iIndex;
	}
	public void setiIndex(String iIndex) {
		this.iIndex = iIndex;
	}
}
