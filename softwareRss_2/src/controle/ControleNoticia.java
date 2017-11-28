package controle;

import envioemail.EnvioEmailGmail;
import java.util.ArrayList;
import leitornoticia.Feed;
import leitornoticia.FeedMessage;
import leitornoticia.RSSFeedParser;
import modelo.Persistencia;
import savelog.SaveLog;

public class ControleNoticia implements Runnable{
    private Persistencia per;
    private SaveLog log;
    private String usuarioEmail = "usuarioDoSeuEmailGmail";
    private String senhaEmail   = "senhaDoSeuEmailGmail";
    private String paraEmail    = "emailDestino";
    private String nomeBanco    = "teste";
    private String local        = "C:\\Users\\Vinicius Cardoso\\Desktop\\Desenvolvimentos\\log01.txt";
    private int    tempo        = 1;

    public ControleNoticia() {
        setNomeBanco();
        String vValor = per.consultarHorario();
        if (!vValor.equalsIgnoreCase(""))
            tempo = Integer.parseInt(vValor);
        log.insereLog("Valor do tempo será:"+tempo);
    }
    public void setNomeBanco() {
        per = new Persistencia(nomeBanco, local);
        log = per.PersistenciaLog();
    }
    
    private void verificarNoticia(){
        log.insereLog("Inciando método de Verificar Noticias");
        ArrayList<String> listSite      = per.consultarSiteList();
        ArrayList<String> listAssunto   = per.consultarAssuntoList();
        for(String site : listSite){
            for(String assunto : listAssunto){
                log.insereLog("Localizando Url "+site+" e Assunto " +assunto);
                RSSFeedParser rssFeed = new
                RSSFeedParser(site);
                Feed feed = rssFeed.readFeedSearch(assunto);
                for(FeedMessage f : feed.getMessages()){
                    log.insereLog("Localizado noticia:" + f.toString());
                    String link  =  f.getLink().replace("'", "");
                    String title = f.getTitle().replace("'", "");
                    if (!per.consultarNoticia().contains(title)){
                        per.inserirNoticia(link, title);
                        log.insereLog("Inserido noticia no Banco de Dados");
                        envioEmail(link, title);
                        log.insereLog("Enviado noticia para " + this.paraEmail);
                    }
                }
            }
        }
    }
    
    private void envioEmail(String link, String assunto){
        EnvioEmailGmail email = new EnvioEmailGmail(this.usuarioEmail, this.senhaEmail);
        email.enviarEmail(paraEmail, link, assunto);
    }

    @Override
    public void run() {
        try{
            while(true){
                
                verificarNoticia();
                Thread.sleep(tempo*60*1000);
                //sleep(tempo*1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
