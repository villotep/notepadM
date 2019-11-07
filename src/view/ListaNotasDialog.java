package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import operacao.Login;
import operacao.NotasOp;

public class ListaNotasDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  private JButton btnNovaNota;
  private JButton btnSair;
  private JLabel lbNotas;
  private JList<String> listaNotas;

  public ListaNotasDialog(Frame parent) {
    super(parent, "Notas | Usuário: " + Login.getLogado().getNome(), true);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    lbNotas = new JLabel("Notas: ");
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    panel.add(lbNotas, cs);

    try {
      NotasOp.populaNotas(Login.getLogado());
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(ListaNotasDialog.this, "Erro ao recuperar notas do usuário.", "Cadastro",
          JOptionPane.ERROR_MESSAGE);
      dispose();
    }

    List<String> nomesNotas = NotasOp.nomesNotas(Login.getLogado());
    String nomes[] = new String[nomesNotas.size()];
    nomes = nomesNotas.toArray(nomes);
    listaNotas = new JList<String>(nomes);

    listaNotas.addMouseListener(new DoubleClickNota());

    cs.gridx = 0;
    cs.gridy = 1;
    cs.gridwidth = 2;
    panel.add(listaNotas, cs);

    btnNovaNota = new JButton("Nova nota");
    
    btnNovaNota.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        NotaDialog nd = new NotaDialog(null, "", "sem nome");
        nd.setVisible(true);
      }      
    });
    
    
    btnSair = new JButton("Sair");
    
    btnSair.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    
    });

    JPanel bp = new JPanel();
    bp.add(btnNovaNota);
    bp.add(btnSair);
    
    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(bp, BorderLayout.PAGE_END);

    pack();
    setResizable(true);
    setLocationRelativeTo(parent);
  }
  
  public class DoubleClickNota extends MouseAdapter {
    public void mouseClicked(MouseEvent evt) {
      JList<String> list = (JList<String>) evt.getSource();
      if (evt.getClickCount() == 2) {
        NotaDialog nd = new NotaDialog(null, NotasOp.conteudoNota(Login.getLogado(), list.getSelectedValue()), list.getSelectedValue());
        nd.setVisible(true);
      }      
    }
  };

}
