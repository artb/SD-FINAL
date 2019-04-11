import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.BoxLayout.X_AXIS;

public class ExampleClient {

  public static void Frame(Example example, ArrayList<Moeda> moedasRegistradas){
      JFrame frame = new JFrame("RMI");
      Container panel = frame.getContentPane();
      JLabel q0_label = new JLabel("Insira o nome da Moeda: ");
      final JTextField inputNome = new JTextField(15);
      JLabel qf_label = new JLabel("Insira o valor da Moeda: ");
      final JTextField inputCotacao = new JTextField(5);

      JButton ok = new JButton("Inserir");
      JButton janelaEditar = new JButton("Editar");
      final JTextArea area = new JTextArea(30, 30);

      //Janela de edicao
      JFrame frame2 = new JFrame("Editar Moeda");
      Container panelEdit = frame2.getContentPane();
      JLabel num = new JLabel("Insira o numero da moeda: ");
      //PASSAR ESSE ID PRA EDITAR O VALOR DENTRO DO VECTOR
      final JTextField inputID = new JTextField(5);
      JLabel newVal = new JLabel("Insira o novo valor da Moeda: ");
      final JTextField inputNCotacao = new JTextField(5);
      JButton editarJanelaEditar = new JButton("Editar Moeda");
      JButton apagar = new JButton("Apagar Moeda");
      //-----------

      panel.setLayout(new FlowLayout());
      panel.add(q0_label);
      panel.add(inputNome);
      panel.add(qf_label);
      panel.add(inputCotacao);
      panel.add(ok);
      panel.add(janelaEditar);
      panel.add(area);

      ok.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              try {
                  String s1 = inputNome.getText();
                  String s2 = inputCotacao.getText();
                  double cotacao = Double.valueOf(s2);
                  example.initMoedas();
                  example.adicionaMoeda(s1,cotacao);
                  final ArrayList<Moeda> moedasRegistradas = new ArrayList<Moeda>(example.getMoedas());
                  String texto = example.showMoedas(moedasRegistradas);
                  area.setText(texto);

              } catch (NumberFormatException e1) {
                  e1.printStackTrace();
              } catch (IOException e1) {
                  e1.printStackTrace();
              }
          }
      });

      janelaEditar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

              panelEdit.setLayout(new BoxLayout(panelEdit,BoxLayout.Y_AXIS));
              panelEdit.add(num);
              panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
              panelEdit.add(inputID);
              panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
              panelEdit.add(newVal);
              panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
              panelEdit.add(inputNCotacao);
              panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
              panelEdit.add(editarJanelaEditar);
              panelEdit.add(apagar);

              // Display the window.
              frame2.setLocation(500,500);
              frame2.pack();
              frame2.setVisible(true);
              frame2.toFront();

          }

      });
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));



      // Display the window.
      frame.setLocation(500,500);
      frame.pack();
      frame.setVisible(true);
      frame.toFront();

      frame.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
              System.exit(0);
          }
      });

  }

  public static void main(String[] args) {
    try {
      Example example = (Example) Naming.lookup("rmi://localhost/Example");
      ArrayList<Moeda> moedasRegistradas = example.getMoedas();




      // Iniciaolizando se precisar
      if(moedasRegistradas.size() == 0) {
        example.initMoedas();
      }
      moedasRegistradas = example.getMoedas();

      // Adicionando uma moeda aqui 
      //example.adicionaMoeda("Amauri", 99999.001);
      //example.adicionaMoeda("Arthur",33.2);
      //example.adicionaMoeda("Dolar",45.2);
      //moedasRegistradas = example.getMoedas(); // tem que fazer isso pq ele não dá o get do atualizado





      // Editando a cotacao do dola aqui
      example.mudaCotacao("dolar", 123.1);
      moedasRegistradas = example.getMoedas(); // tem que fazer isso pq ele não dá o get do atualizado

      // removendoo real daqui
      example.removeMoeda("real");
      moedasRegistradas = example.getMoedas(); // tem que fazer isso pq ele não dá o get do atualizado

      // Mostrando as moedas
      for(int i=0; i< moedasRegistradas.size(); i++) {
        //System.out.println("moeda: " + moedasRegistradas.get(i).getNome() + " cotacao: " + moedasRegistradas.get(i).getCotacao());
      }
      //System.out.println("----------------------------------");

      String motherfucekr = example.showMoedas(moedasRegistradas);
      //System.out.println(motherfucekr);

      Frame(example,moedasRegistradas);


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    }





  }
}
