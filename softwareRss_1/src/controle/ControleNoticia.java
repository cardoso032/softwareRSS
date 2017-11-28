package controle;

import envioemail.EnvioEmailGmail;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import leitornoticia.Feed;
import leitornoticia.FeedMessage;
import leitornoticia.RSSFeedParser;
import modelo.Persistencia;

public class ControleNoticia {
    private String nomeBanco;
    private Persistencia per;
    private String usuarioEmail = "";
    private String senhaEmail   = "";
    public ControleNoticia() {
    }

    public void setNomeBanco() {
        this.nomeBanco = JOptionPane.showInputDialog("Informe o nome do Banco de dados");
        per = new Persistencia(nomeBanco, "C:\\Users\\Vinicius Cardoso\\Desktop\\Desenvolvimentos\\log01.txt");
    }

    public void menu() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Informe: "
                + "\n00 - Sair"
                + "\n01 - Site Inserir"
                + "\n02 - Site Deletar"
                + "\n03 - Site Consultar"
                + "\n04 - Assunto Inserir"
                + "\n05 - Assunto Deletar"
                + "\n06 - Assunto Consultar"
                + "\n07 - Horario Inserir"
                + "\n08 - Horario Deletar"
                + "\n09 - Horario Consultar"
                + "\n10 - Consultar Noticias encontradas"
                + "\n11 - Verificar Manualmente"
                + "\n12 - Enviar por e-mail"
                + "\n13 - Limpar Dados E-mail"
                ));
        while (op != 0) {
            switch (op) {
                case 1:
                    inserirSite();
                    break;
                case 2:
                    deletarSite();
                    break;
                case 3:
                    consultarSite();
                    break;
                case 4:
                    inserirAssunto();
                    break;
                case 5:
                    deletarAssunto();
                    break;
                case 6:
                    consultarAssunto();
                    break;
                case 7:
                    inserirHorario();
                    break;
                case 8:
                    deletarHorario();
                    break;
                case 9:
                    consultarHorario();
                    break;
                case 10:
                    consultarNoticia();
                    break;
                case 11:
                    verificarNoticia();
                    break;
                case 12:
                    envioEmailNoticias();
                    break;
                case 13:
                    envioEmailLimpar();
                    break;
            }
            op = Integer.parseInt(JOptionPane.showInputDialog("Informe: "
                    + "\n00 - Sair"
                    + "\n01 - Site Inserir"
                    + "\n02 - Site Deletar"
                    + "\n03 - Site Consultar"
                    + "\n04 - Assunto Inserir"
                    + "\n05 - Assunto Deletar"
                    + "\n06 - Assunto Consultar"
                    + "\n07 - Horario Inserir"
                    + "\n08 - Horario Deletar"
                    + "\n09 - Horario Consultar"
                    + "\n10 - Consultar Noticias encontradas"
                    + "\n11 - Verificar Manualmente"
                    + "\n12 - Enviar por e-mail"
                    + "\n13 - Limpar Dados E-mail"
                    ));
        }
    }
    private void inserirSite(){
        String url = JOptionPane.showInputDialog("Informe o site (RSS)");
        per.inserirSite(url);
    }
    private void deletarSite(){
        String url = JOptionPane.showInputDialog("Informe o site (RSS) a ser removido");
        per.deletarSite(url);
    }
    private void consultarSite(){
        JOptionPane.showConfirmDialog(null, per.consultarSite());
    }
    private void inserirAssunto(){
        String ass = JOptionPane.showInputDialog("Informe o assunto para pesquisar:");
        per.inserirAssunto(ass);
    }
    private void deletarAssunto(){
        String ass = JOptionPane.showInputDialog("Informe o assunto a ser removido");
        per.deletarAssunto(ass);
    }
    private void consultarAssunto(){
        JOptionPane.showConfirmDialog(null, per.consultarAssunto());
    }
    private void inserirHorario(){
        String hor = JOptionPane.showInputDialog("Informe o horario para executar as buscas");
        per.inserirHorario(hor);
    }
    private void deletarHorario(){
        String hor = JOptionPane.showInputDialog("Informe o horário a ser removido");
        per.deletarHorario(hor);
    }
    private void consultarHorario(){
        JOptionPane.showConfirmDialog(null, per.consultarHorario());
    }
    private void consultarNoticia(){
        JOptionPane.showConfirmDialog(null, per.consultarNoticia());
    }
    private void verificarNoticia(){
        int envia = Integer.parseInt(JOptionPane.showInputDialog("Informe: 1 - Enviar e 0 - Não Enviar"));
        ArrayList<String> listSite      = per.consultarSiteList();
        ArrayList<String> listAssunto   = per.consultarAssuntoList();
        for(String site : listSite){
            for(String assunto : listAssunto){
                RSSFeedParser rssFeed = new
                RSSFeedParser(site);
                Feed feed = rssFeed.readFeedSearch(assunto);
                for(FeedMessage f : feed.getMessages()){
                    per.inserirNoticia(f.getLink(), f.getTitle());
                    if (envia == 1){
                        autenticaDadosEmail();
                        envioEmail(f.getLink(), f.getTitle());
                    }
                }
            }
        }
    }
    private void envioEmailNoticias(){
        envioEmail("Todas as Noticias Cadastradas", per.consultarNoticia());
    }
    private void envioEmail(String link, String assunto){
        autenticaDadosEmail();
        String para = JOptionPane.showInputDialog("Informe o e-mail destino(Para enviar para mais de 1, separe-os por vígurla)");
        EnvioEmailGmail email = new EnvioEmailGmail(this.usuarioEmail, this.senhaEmail);
        email.enviarEmail(para, link, assunto);
    }
    private void autenticaDadosEmail(){
        while((usuarioEmail.equalsIgnoreCase(""))||(senhaEmail.equalsIgnoreCase(""))){
            if (usuarioEmail.equalsIgnoreCase(""))
                usuarioEmail = JOptionPane.showInputDialog("Informe o usuário de E-mail(Gmail)");
            if (senhaEmail.equalsIgnoreCase(""))
                senhaEmail = JOptionPane.showInputDialog("Informe a senha do E-mail(Gmail)");
        }   
    }
    private void envioEmailLimpar(){
        this.usuarioEmail = "";
        this.senhaEmail   = "";
        autenticaDadosEmail();
    }
}
