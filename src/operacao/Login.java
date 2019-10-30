package operacao;

import modelo.Usuario;

public class Login {
  
  private static Usuario logged;

  public static boolean authenticate(String user, 
      String password) {
    for(Usuario u : Cadastro.getCadastrados()) {
      if(u.getEmail().equals(user)) {
        String salt = u.getSenha().getSalt();
        if(PasswordUtil.verifyPassword(password, u.getSenha().getHash(), salt)) {
          logged = u;
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

}
