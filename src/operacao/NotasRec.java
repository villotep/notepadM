package operacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import modelo.Nota;
import modelo.Usuario;

public class NotasRec {
  
  public static void populaNotas(Usuario u) throws IOException {
    File dir = new File("./"+u.getEmail());
    if(dir.exists() && dir.isDirectory()) {
      File[] directoryListing = dir.listFiles();
      if (directoryListing != null) {
        for (File nota : directoryListing) {
          Nota n = new Nota();
          n.setNome(nota.getName());
          BufferedReader br;
          br = new BufferedReader(new FileReader(nota));
          String st, cont = ""; 
          while ((st = br.readLine()) != null) 
            cont+=st;
          br.close();
          n.setConteudo(cont);
          u.adicionaNota(n);
        }
      }
    }
  }

}
