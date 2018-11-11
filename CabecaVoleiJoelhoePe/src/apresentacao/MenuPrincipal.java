package apresentacao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel implements ActionListener {
    //Atributos
    private Apresentacao app;
//    private MenuJogador menuJogador;
    
    private KeyListerner keyListerner;
    
    private Image ajuda;
    private Image configuracoes;
    private Image creditos;
    private Image background;
    private Image btnVoltar;
    
    private Image[] btnOn;
    private Image[] btnOff;
    
    private int nav = 0;
    private int sel = 0;
     
    public MenuPrincipal(Apresentacao app){
        this.app = app;
        
        ImageIcon image;
        
        keyListerner = new KeyListerner();
        addKeyListener(keyListerner);
        
        setFocusable(true);
        setDoubleBuffered(true);
        
        btnOn = new Image[5];
        image = new ImageIcon("imagens/botoes/button_jogar_ON.png");        
        btnOn[0] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_configuracoes_ON.png");        
        btnOn[1] = image.getImage(); 
        
        image = new ImageIcon("imagens/botoes/button_ajuda_ON.png");        
        btnOn[2] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_creditos_ON.png");        
        btnOn[3] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_sair_ON.png");        
        btnOn[4] = image.getImage();
     
        
        btnOff = new Image[5];
        image = new ImageIcon("imagens/botoes/button_jogar_OFF.png");        
        btnOff[0] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_configuracoes_OFF.png");        
        btnOff[1] = image.getImage(); 
        
        image = new ImageIcon("imagens/botoes/button_ajuda_OFF.png");        
        btnOff[2] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_creditos_OFF.png");        
        btnOff[3] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/button_sair_OFF.png");        
        btnOff[4] = image.getImage();
        
        image = new ImageIcon("imagens/menu/MenuPrincipal.png");        
        this.background = image.getImage();

        image = new ImageIcon("imagens/menu/Ajuda.png");        
        this.ajuda = image.getImage();
        
        image = new ImageIcon("imagens/menu/Creditos.png");        
        this.creditos = image.getImage();
        
        image = new ImageIcon("imagens/menu/Configuracoes.png");        
        this.configuracoes = image.getImage();
                
        image = new ImageIcon("imagens/botoes/button_voltar_ON.png");        
        this.btnVoltar = image.getImage();

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(sel){
            case 0:
            g.drawImage(this.background, 0, 0, null);
            for (int i = 0; i < 5; i++){
                if (nav == i){
                    g.drawImage(btnOn[i], (800/2)-200, 150+(i*75), null);            
                }
                else{
                    g.drawImage(btnOff[i], (800/2)-200, 150+(i*75), null);                            
                }
            }
            break;
            case 1: // Configuracoes
                g.drawImage(this.configuracoes, 0, 0, null);
                g.drawImage(btnVoltar, (800/2)-125, (600-85), null); 
                System.out.println(sel);
            break;
            case 2: // Ajuda
                g.drawImage(this.ajuda, 0, 0, null);
                g.drawImage(btnVoltar, (800/2)-125,(600-85), null);
                System.out.println(sel);
            break;
            case 3: // Créditos
                g.drawImage(this.creditos, 0, 0, null);
                g.drawImage(btnVoltar, (800/2)-125, (600-85), null);
                System.out.println(sel);
            break;

        }  
        

        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
    }
    

    public void pressed(KeyEvent e) {

        int key = e.getKeyCode();  

        
        if (sel == 0 ){
            if (key == KeyEvent.VK_ENTER) {
                if (nav == 0){    
//                    add(menuJogador);                               
                }
                else if(nav == 1){                
                    sel = 1;                
                }            
                else if(nav == 2){                                
                    sel = 2;
                }             
                else if (nav == 3){
                    sel = 3;
                } 
                else if (nav == 4){
                    System.exit(0);
                }  
            }            
            if (key == KeyEvent.VK_UP) {
                nav--;
                if (nav < 0){
                    nav = 0;
                }
            }        

            if (key == KeyEvent.VK_DOWN) {
                nav++;
                if (nav > 4){
                    nav = 4;
                }
            }
        }
        else{
            if (key == KeyEvent.VK_ENTER) {
                sel = 0;
            }
        }

        repaint();      
        
    }
    
    private class KeyListerner extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {            
            pressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}
