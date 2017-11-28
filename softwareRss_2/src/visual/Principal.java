package visual;
import controle.ControleNoticia;
public class Principal {
    public static void main(String[]agrs){
        ControleNoticia c = new ControleNoticia();
        (new Thread(c)).start();
    }
}
