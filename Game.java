package model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable, MouseListener {
    
    public static final int WIDTH = 640,HEIGHT = 480;
    
    public static int contador = 100;
    
    public static int pontuacao = 0;
    
    public static int mx, my;
    public static boolean clicado = false;
    
    public Spawner spawner;
    
    private static Sound sound = new Sound();
    private static Sound soundBackground = new Sound();
    
    public boolean gameOver = false;
    
    public static BufferedImage imagemFundo;
    
    public Game(){
        Dimension dimension = new Dimension(WIDTH,HEIGHT);
        this.setPreferredSize(dimension);
        
        this.addMouseListener(this);
        
        spawner = new Spawner();
    }
    
    public void update() throws Exception {
        if (gameOver == false) {
            spawner.update();
            if (contador <= 0) {
                contador = 100;
                gameOver = true;
                stopMusic();
                playSE(2);    
            }
        }
    }
    
    public void render () throws IOException, InterruptedException {
        BufferStrategy bs = this.getBufferStrategy();
       
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        imagemFundo = ImageIO.read(new File(getClass().getResource("fundo.png").getPath()));  
        
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(imagemFundo, 0, 0, null);
            if (gameOver == false) {  
                g.setColor(Color.green);
                g.fillRect(Game.WIDTH/2 - 100 - 70, 20, contador*3, 20);
                g.setColor(Color.white);
                g.drawRect(Game.WIDTH/2 - 100 - 70, 20, 300, 20);
       
                g.setColor(Color.white);
                g.setFont(new Font("Arial",Font.BOLD, 18));
                g.drawString("Pontuação: " + this.pontuacao, WIDTH/2 + 150, HEIGHT/2 - 203);
              
                g.setColor(Color.white);
                g.setFont(new Font("Arial",Font.BOLD, 18));
                
                String nivel = Integer.toString(spawner.nivel);
                
                if (spawner.nivel == 10){
                    nivel = "MAX!";
                }

                g.drawString("Nível: " + nivel , WIDTH/2 -280, HEIGHT/2 - 203);

                spawner.render(g);
            } else {
                g.setColor(Color.white);
                g.setFont(new Font("Arial",Font.BOLD, 30));
                g.drawString("GAME OVER!", WIDTH/2 - 100, HEIGHT/2 - 50);
                g.drawString("Pontuação final: " + this.pontuacao, WIDTH/2 - 130, HEIGHT/2 + 80);            
            }
        bs.show();
    }
    
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame jframe = new JFrame("Jogo APS");
        jframe.add(game);
        jframe.setLocation(700,300);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setCursor(Cursor.CROSSHAIR_CURSOR);
        jframe.setVisible(true);
        
        new Thread(game).start();
        
        playMusic(0);
    }
	
    @Override
    public void run() {
        while(true) {
            try {
                update();
            } catch (Exception e) {

            }
            try {
                render();
            } catch (Exception ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicado = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicado = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    //Música
    public static void playMusic(int i) {
        soundBackground.setFile(i);
        soundBackground.play();
        soundBackground.loop();
    }
    
    public static void stopSE() {
        sound.stop();
    }
    
    public static void stopMusic() {
        soundBackground.stop();
    }
    
    public static void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}

