import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ExampleServer extends UnicastRemoteObject implements Example {
  private String stringState;
  private ArrayList<Moeda> moedasRegistradas = new ArrayList<Moeda>();

  public ExampleServer() throws RemoteException{};

  public ArrayList<Moeda> getMoedas() throws RemoteException {
    return this.moedasRegistradas;
  }

  public void initMoedas() throws RemoteException {
      Moeda real = new Moeda("Real",3.4);

  }

  public String showMoedas(ArrayList<Moeda> moedas) throws RemoteException{
      String todasMoedas = "";
      for(int i=0; i < moedas.size();i++){
          Moeda unidade = moedas.get(i);
          String nome = unidade.nome;
          String cotacao = Double.toString(unidade.cotacao);
          String frase = nome + " cotado em: R$" + cotacao;
          todasMoedas = todasMoedas + "\n" + frase;
      }

      return todasMoedas;
  }

  public void adicionaMoeda (String nome, double cotacao) throws RemoteException {
    Moeda novo = new Moeda(nome, cotacao);
    this.moedasRegistradas.add(novo);
  }

    public static int stringCompare(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        }
        else {
            return 0;
        }
    }

  public void mudaCotacao (String nome, double novaCotacao) throws RemoteException {
    Moeda novo = new Moeda(nome, novaCotacao);
    for(int i=0; i< this.moedasRegistradas.size(); i++) {
      if (stringCompare(moedasRegistradas.get(i).getNome(),nome) == 0) {
        this.moedasRegistradas.set(i, novo);
      }
    }
  }

  public void removeMoeda (String nome) throws RemoteException {
    for(int i=0; i< this.moedasRegistradas.size(); i++) {
      if (stringCompare(moedasRegistradas.get(i).getNome(),nome) == 0) {
        this.moedasRegistradas.remove(i);
      }
    }
  }
}
