package bean;

import org.apache.struts.validator.ValidatorForm;

/**
 * ログイン処理で使うBean
 * Validatorを使用するためValidatorFormクラスを継承
 */
public class LoginBean extends ValidatorForm {

	private int id;
	private String pass;

	// idアクセサ
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	// passアクセサ
	public String getPass() {return pass;}
	public void setPass(String pass) {this.pass = pass;}
}
