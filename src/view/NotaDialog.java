package view;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Nota;
import operacao.Login;
import operacao.NotasRec;

public class NotaDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  
  private JButton btnNovaNota;
  private JList<JLabel> listaNotas;
  
  public NotaDialog(Frame parent) {
    
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    listaNotas = new JList<JLabel>();
    
    try {
      NotasRec.populaNotas(Login.getLogado());
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(NotaDialog.this, "Erro ao recuperar notas do usuário.",
          "Cadastro", JOptionPane.ERROR_MESSAGE);
      dispose();
    }
    List<Nota> notasUsuario = Login.getLogado().getNotas();
    if(notasUsuario.size() > 0) {
      for(Nota n : notasUsuario) {
        JLabel jl = new JLabel(n.getNome());
        listaNotas.add(jl);
      }
    }
    
    
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    panel.add(listaNotas, cs);
    
    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
    
  }

}
