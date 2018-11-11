package apresentacao;

import javax.swing.JFrame;

public class Apresentacao extends JFrame {
    private MenuPrincipal menu;
    
    public Apresentacao(){
        menu = new MenuPrincipal(this);
        
        add(menu);

        setSize(800, 600);

        setTitle("Cabeça Vôlei Joelho e Pé!");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Apresentacao app = new Apresentacao();
        app.setVisible(true);
    }
}
