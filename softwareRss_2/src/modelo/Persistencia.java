package modelo;

import connect.Connect;
import java.sql.ResultSet;
import java.util.ArrayList;
import savelog.SaveLog;

public class Persistencia {
    private Connect conexao     = null;
    private SaveLog log         = null;   
    
    public Persistencia(String nomeBanco, String local){
        conexao = new Connect();
        conexao.connect(nomeBanco);
        log = new SaveLog(local);
        log.insereLog("setNomeBanco-"+nomeBanco);
    }
    public SaveLog PersistenciaLog(){
        return log;
    }
 
    public ArrayList<String> consultarSiteList(){
        ArrayList<String> listSite = new ArrayList<>();
        String sql = "Select * from site";
        ResultSet rs = conexao.Select(sql);
        try{
            while (rs.next())
                listSite.add(rs.getString("siturl"));
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarSiteList-"+sql);
        return listSite;
    }

    public ArrayList<String> consultarAssuntoList(){
        ArrayList<String> listAssunto = new ArrayList<>();
        String sql = "Select * from assunto";
        ResultSet rs = conexao.Select(sql);
        try{
            while (rs.next())
                listAssunto.add(rs.getString("assass"));
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarAssuntoList-"+sql);
        return listAssunto;
    }

    public String consultarHorario(){
        String sql = "Select * from horario";
        ResultSet rs = conexao.Select(sql);
        try{
            String texto = "";
            while (rs.next())
                texto = rs.getString("horhor");
            return texto;
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarHorario-"+sql);
        return "";
    }
    public void inserirNoticia(String notlin, String nottit){
        notlin = notlin.replace("'", "");
        nottit = nottit.replace("'", "");
        String sql = "INSERT INTO noticia (notlin, nottit) " +
                       "VALUES ('"+notlin+"','"+nottit+"')";
        conexao.insert(sql);
        log.insereLog("inserirNoticia-"+sql);
    }
    public String consultarNoticia(){
        String sql = "Select * from noticia";
        ResultSet rs = conexao.Select(sql);
        try{
            String texto = "";
            while (rs.next()){
                int id = rs.getInt("notcod");
                String notlin = rs.getString("notlin");
                String nottit = rs.getString("nottit");
                texto += "Código: " + id + " Link: " +notlin + " Título: "+nottit+"\n";
            }
            return texto;
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarNoticia-"+sql);
        return "";
    }
}
