package savelog;

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
        try{
            arq = new FileWriter(this.local);
            gravarArq = new PrintWriter(arq);
            log = getDateTime()+ "-"+log;
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
}
