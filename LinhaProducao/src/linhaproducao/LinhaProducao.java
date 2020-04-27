
package linhaproducao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LinhaProducao {

    public static void main(String[] args) {
        
        ArrayList<LinhaProd> linhaDeMontagens = new ArrayList();
        ArrayList<String> ordArray = new ArrayList();
        
        String dir = "D:\\temp\\";
        File file = new File(dir);
        
        for(String arq : file.list()){
            if(arq.endsWith(".txt")){
                String linha = null;                
                try {
                    String camArq = dir+arq;
                    FileReader leitura = new FileReader(camArq);
                    BufferedReader ler = new BufferedReader(leitura);
                    
                    while((linha = ler.readLine()) != null) {
                    	LinhaProd linMont = new LinhaProd();
                    	if (linha.substring(linha.length()-11,linha.length()).equals("maintenance")) {                		
                            linMont = new LinhaProd(linha, 5);                            
                    	}else{            
                            linMont = new LinhaProd(linha, Integer.parseInt(linha.substring(linha.length()-5, linha.length()-3)));
                        }
                        linhaDeMontagens.add(linMont);
                    }
                } catch (Exception e) { }
            }
        }
        Collections.sort(linhaDeMontagens, Comparator.comparing(LinhaProd::getTempo));
        Collections.reverse(linhaDeMontagens);
       
        LocalTime hora = LocalTime.of(9,0);
        int i = 1;
        int j = 0;
        int totMinAM = 180;
        int totMinProc = 0;
        String escrever = "";
        System.out.println(linhaDeMontagens.size());
        try {
            FileOutputStream arquivo = new FileOutputStream("D:/temp/arquivo.txt");
            PrintWriter pr = new PrintWriter(arquivo);
            
            for (LinhaProd c : linhaDeMontagens) {                
                if (hora.equals(LocalTime.of(9, 0))){
                    escrever = "Linha de montagem " + i;
                    ordArray.add(escrever);
                    i++;
                }
                escrever = hora + " " + c.getNome();
                if (totMinAM > totMinProc) {   
                    ordArray.add(escrever);
                    hora = hora.plusMinutes(c.getTempo()); 
                    totMinProc = totMinProc + c.getTempo();     
                }else if (totMinAM == totMinProc){
                    escrever = hora +" Almoço";
                    ordArray.add(escrever);
                    hora = hora.plusMinutes(60);
                    totMinProc = totMinProc + c.getTempo();
                }else if ( hora.getHour() > 15){
                        escrever = hora +" Ginástica laboral";                                            
                        ordArray.add(escrever);
                        hora = LocalTime.of(9,0);    
                        totMinProc = 0;
                    
                }else if ( hora.getHour() > 16){
                } else {
                    escrever = hora + " " + c.getNome();
                    ordArray.add(escrever);
                    hora = hora.plusMinutes(c.getTempo());  
                }
            }
            
            for (String d : ordArray) {
                System.out.println(d);
                pr.println(d); 
            }
            pr.close();
            arquivo.close();
        } catch (Exception e) {
        }
        
        
    }
    
    
}
