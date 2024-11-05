package controller;

public class ControllerLogin {
	
	public boolean validarLogin(String login, String senha)
	{
		if(login.equals("admin") && senha.equals("123456"))
			return true;
		return false;
	}

}
