
package apresentacao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuArena extends JPanel implements KeyListener{
    private Apresentacao app;
    
    private Image background;
    private Image[] btnOn;
    private Image[] btnOff;
    
    private int nav = 0;
    private int sel = 0;
    private int arenaSelecionada = 0;
    
    public MenuArena(Apresentacao app){
        this.app = app;
        
        ImageIcon image;
  
        addKeyListener(this);
        
        setFocusable(true);
        setDoubleBuffered(true);
        
        btnOn = new Image[8];
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[0] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[1] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[2] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[3] = image.getImage();
       
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[4] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[5] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[6] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_on.png");        
        btnOn[7] = image.getImage();
        
        btnOff = new Image[8];
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[0] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[1] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[2] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[3] = image.getImage();
       
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[4] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[5] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[6] = image.getImage();
        
        image = new ImageIcon("imagens/botoes/botao_imagem_off.png");        
        btnOff[7] = image.getImage();
       
        image = new ImageIcon("imagens/menu/Escolha_arena.png");
        this.background = image.getImage();
        
               
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        switch(sel){
//            case 0:
            g.drawImage(this.background, 0, 0, null);
            for (int i = 0; i < 4; i++){
                if (nav == i){
                    g.drawImage(btnOn[i], (34*(i+1))+(157*i), 177, null);            
                }
                else{
                    g.drawImage(btnOff[i], (34*(i+1))+(157*i), 177, null);                            
                }
            }
            for (int i = 4; i < 8; i++){
                if (nav == i){
                    g.drawImage(btnOn[i], (34*(i+1-4))+(157*(i-4)), 354, null);            
                }
                else{
                    g.drawImage(btnOff[i], (34*(i+1-4))+(157*(i-4)), 354, null);                            
                }
            }        
            
            Toolkit.getDefaultToolkit().sync();
    }
    
   
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
     @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();  

        
        if (sel == 0 ){
            if (key == KeyEvent.VK_ENTER) {
                if (nav == 0){    
                  arenaSelecionada = 0;                               
                }
                else if(nav == 1){                
                    arenaSelecionada = 1;               
                }            
                else if(nav == 2){                                
                    arenaSelecionada = 2;
                }             
                else if (nav == 3){
                    arenaSelecionada = 3;
                } 
                else if (nav == 4){
                    arenaSelecionada = 4;
                } 
                else if (nav == 5){
                    arenaSelecionada = 5;
                } 
                else if (nav == 6){
                    arenaSelecionada = 6;
                } 
                else if (nav == 7){
                    arenaSelecionada = 7;
                }
                
                app.showMenuJogador();
            }            
            if (key == KeyEvent.VK_LEFT) {
                nav--;
                if (nav < 0){
                    nav = 0;
                }
            }        

            if (key == KeyEvent.VK_RIGHT) {
                nav++;
                if (nav > 7){
                    nav = 7;
                }
            }
            
            if (key == KeyEvent.VK_DOWN) {
                nav += 4;
                if (nav > 7){
                    nav -=4;
                }
            }
            
            if (key == KeyEvent.VK_UP) {
                nav -= 4;
                if (nav < 0){
                    nav += 4;
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
}
