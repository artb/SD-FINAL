  
import java.lang.reflect.Constructor;
import java.io.Serializable;


class Moeda implements Serializable {
  public double cotacao;
  public String nome;
  
  public Moeda(String nome, double cotacao) {
    this.nome = nome;
    this.cotacao = cotacao;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCotacao(double cotacao) {
    this.cotacao = cotacao;
  }

  public String getNome() {
    return nome;
  }

  public double getCotacao() {
    return cotacao;
  }
}
