package savelog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveLog {
    private FileWriter arq          = null;
    private PrintWriter gravarArq   = null;
    private String   local          = "";
    public SaveLog(String local){
        this.local = local;
    }
    public void insereLog(String log){
        String dadosArquivo = lerArquivo();
        try{
            arq = new FileWriter(this.local);
            gravarArq = new PrintWriter(arq);
            log = getDateTime()+ "-"+log;
            log += dadosArquivo;
            gravarArq.printf(log);
            arq.close();
        }catch(Exception e){
            arq = null;
            gravarArq = null;
            e.printStackTrace();
        }
    }
    private String getDateTime() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date(); 
	return dateFormat.format(date);
    }
    private String lerArquivo(){
        try {
            FileReader arq = new FileReader(this.local);
            BufferedReader lerArq = new BufferedReader(arq);
            String texto = "";
            String linha = lerArq.readLine();
            while (linha != null) {
              texto += "\n" + linha;
              linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
            return texto;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "";
    }
}
