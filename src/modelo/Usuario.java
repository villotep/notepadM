package modelo;


public class Usuario {
  private String nome;
  private String email;
  private Senha senha;
  
  public Usuario(String nome, String email, Senha senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Senha getSenha() {
    return senha;
  }
  public void setSenha(Senha senha) {
    this.senha = senha;
  }

}
