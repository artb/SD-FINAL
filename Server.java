import java.rmi.*;

public class Server {
  public static void main(String[] args) throws RemoteException, java.net.MalformedURLException {
    ExampleServer es = new ExampleServer();
    Naming.rebind("rmi://localhost/Example", es);
    System.out.println("Running server in rmi://localhost/Example");
  }
}