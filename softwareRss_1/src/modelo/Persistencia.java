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
        log     = new SaveLog(local);
        conexao.connect(nomeBanco);
        log.insereLog("setNomeBanco-"+nomeBanco);
    }
    public void inserirSite(String url){
        String sql = "INSERT INTO SITE (siturl) " +
                       "VALUES ('"+url+"')";
        conexao.insert(sql);
        log.insereLog("inserirSite-"+sql);
    }
    public void deletarSite(String url){
        String sql = "DELETE from site where siturl='"+url+"'";
        conexao.delete(sql);
        log.insereLog("deletarSite-"+sql);
    }
    public String consultarSite(){
        String sql = "Select * from site";
        ResultSet rs = conexao.Select(sql);
        try{
            String texto = "";
            while ( rs.next() ) {
                int id = rs.getInt("sitcod");
                String url = rs.getString("siturl");
                texto += "Código: " + id + " Url: " +url + "\n"; 
            }
            rs.close();
            return texto;
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarSite-"+sql);
        return "";
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
    public void inserirAssunto(String ass){
        String sql = "INSERT INTO assunto (assass) " +
                       "VALUES ('"+ass+"')";
        conexao.insert(sql);
        log.insereLog("inserirAssunto-"+sql);
    }
    public void deletarAssunto(String ass){
        String sql = "DELETE from assunto where assass='"+ass+"'";
        conexao.delete(sql);
        log.insereLog("deletarAssunto-"+sql);
    }
    public String consultarAssunto(){
        String sql = "Select * from assunto";
        ResultSet rs = conexao.Select(sql);
        try{
            String texto = "";
            while (rs.next()){
                int id = rs.getInt("asscod");
                String ass = rs.getString("assass");
                texto += "Código: " + id + " Assunto: " +ass + "\n";
            }
            return texto;
        }catch(Exception e){
            e.printStackTrace();
        }
        log.insereLog("consultarAssunto-"+sql);
        return "";
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
    public void inserirHorario(String hor){
        String sql = "INSERT INTO horario (horhor) " +
                       "VALUES ('"+hor+"')";
        conexao.insert(sql);
        log.insereLog("inserirHorario-"+sql);
    }
    public void deletarHorario(String hor){
        String sql = "DELETE from horario where horhor='"+hor+"'";
        conexao.delete(sql);
        log.insereLog("deletarHorario-"+sql);
    }
    public String consultarHorario(){
        String sql = "Select * from horario";
        ResultSet rs = conexao.Select(sql);
        try{
            String texto = "";
            while (rs.next()){
                int id = rs.getInt("horcod");
                String hor = rs.getString("horhor");
                texto += "Código: " + id + " Horário: " +hor + "\n";
            }
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
    public void deletarNoticia(String nottit){
        String sql = "DELETE from noticia where nottit='"+nottit+"'";
        conexao.delete(sql);
        log.insereLog("deletarNoticia-"+sql);
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
