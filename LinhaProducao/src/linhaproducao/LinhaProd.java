
package linhaproducao;

import java.sql.Time;
import java.util.Comparator;

public class LinhaProd{
    
    private String nome;
    private int tempo;

    public LinhaProd() {
    }
        
    public LinhaProd(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }
    
    public class ComparatorLinhaProd implements Comparator<LinhaProd>{
        public int compare(LinhaProd l1,LinhaProd l2) {
          return l1.tempo < l2.tempo ? -1 : (l1.tempo > l2.tempo ? +1 : 0);
     }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

}
