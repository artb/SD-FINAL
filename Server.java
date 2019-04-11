import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.Remote;

public class Server {
  Server(String ip){
    try{
      System.setProperty("java.rmi.server.hostname", "192.168.1.142");
      LocateRegistry.createRegistry(1099);
      ExampleServer es = new ExampleServer();
      Naming.bind("Moedas", (Remote) es);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Server(args[0]);
  }
}
