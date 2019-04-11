import java.rmi.*;
import java.util.*;

public interface Example extends Remote {
  public void initMoedas() throws RemoteException;
  public ArrayList<Moeda> getMoedas() throws RemoteException;
  public void adicionaMoeda (String nome, double cotacao) throws RemoteException;
  public void mudaCotacao (String nome, double novaCotacao) throws RemoteException;
  public void removeMoeda (String nome) throws RemoteException;
  public String showMoedas(ArrayList<Moeda> moedas) throws RemoteException;
}
