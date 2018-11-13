package modelo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import util.AudioManager;
import util.ImageManager;
import util.LoopSteps;
import util.MainLoop;

public class Game extends JFrame implements LoopSteps {
    private MainLoop loop = new MainLoop(this, 60);
	
	private long previous = System.currentTimeMillis();
	
        CollisionDetector collisionDetector;
        CollisionDetectorDynamic collisionDetectorDynamic;
	ArrayList<AbstractEntity> entities;
        ArrayList<DynamicEntity> dynamicEntities;
	boolean gameOver;
        BufferedImage imgCenario;
	
	public Game() {		
		super("Bouncing ball");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		setSize(800, 600);		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				//Se apertar o x, paramos o loop.
				loop.stop();
			}
		});
                
                entities = new ArrayList<>();
                dynamicEntities = new ArrayList<>();
		collisionDetector = new CollisionDetector(entities);
                collisionDetectorDynamic = new CollisionDetectorDynamic(dynamicEntities);
		gameOver = false;
	}
	
	public void startMainLoop()
	{
		//Iniciamos o main loop
		new Thread(loop, "Main loop").start();
	}

	@Override
	public void setup() {
        try {
            //Criamos a estrat�gia de double buffering
            createBufferStrategy(2);
            //Subtrai a decora��o da janela da largura e altura m�ximas
            //percorridas pela bola.
            
            imgCenario = ImageManager.getInstance().loadImage("estadio.png");
            AudioManager.getInstance().loadAudio("menina.wav").loop();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DynamicEntity j2 = new Player(500, 300, true, Characters.PERSONAGEM_GABI);
        DynamicEntity j1 = new Player(150, 300, false, Characters.PERSONAGEM_GABI);
        DynamicEntity b = new Ball(150, 60, 20);
        
        dynamicEntities.add(j2);
        dynamicEntities.add(j1);
        dynamicEntities.add(b);

        entities.add(j2);
        entities.add(j1);
        entities.add(b);
        
        entities.add(new Wall(0, 590, 800, 10));
        entities.add(new Wall(790, 0, 10, 600));
        entities.add(new Wall(0, 0, 10, 600));
        entities.add(new Wall(0, 0, 800, 10));
        entities.add(new Net(360, 400, 80, 200));

        for (AbstractEntity e : entities) {
                e.init();
        }
        }
	
	@Override
	public void processLogics() {
		//Calcula o tempo entre dois updates
		long time = System.currentTimeMillis() - previous;
		
		if (!gameOver) {
			for (AbstractEntity e : entities) {
				e.update((int) time);
			}
			collisionDetector.update((int) time);
                        collisionDetectorDynamic.update((int) time);
		}
		
		//Grava o tempo na sa�da do m�todo
		previous = System.currentTimeMillis();
	}

	@Override
	public void renderGraphics() {		
		Graphics g = getBufferStrategy().getDrawGraphics();
		
		//Criamos um contexto gr�fico que n�o leva em conta as bordas
		Graphics g2 = g.create(getInsets().right, 
				   getInsets().top, 
				   getWidth() - getInsets().left, 
				   getHeight() - getInsets().bottom);
		//Limpamos a tela
		g2.setColor(Color.BLACK);		
		g2.fillRect(0, 0, getWidth(), getHeight());
		
	
		g2.drawImage(imgCenario, 0, 0, null);
                g2.setColor(Color.white);
                //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		for (AbstractEntity e : entities) {
			e.render((Graphics2D) g2);
		}
		
		//Liberamos os contextos criados.
		g.dispose(); 
		g2.dispose();
	}
	
	@Override
	public void paintScreen() {
		if (!getBufferStrategy().contentsLost())
			getBufferStrategy().show();
	}

	@Override
	public void tearDown() {
		//N�o � realmente necess�rio, pois o jogo acaba.
		//Mas se fosse um fim de fase, seria.
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Game game = new Game();
				game.setVisible(true);
				game.startMainLoop();
                                System.out.println("Passaaaaaaaaa");
			}
		});
	}
}
