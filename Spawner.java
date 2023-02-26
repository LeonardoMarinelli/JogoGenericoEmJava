package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {  
    private int timer = 0;
    
    private List<RectObj> retangulos = new ArrayList<RectObj>();
    private List<Particula> particulas = new ArrayList<Particula>();
    
    private int dificuldade = 60;
    private int pontuacaoNivel = 350;
    public int nivel = 1;
    boolean isMaxLevel = false;
    
    Random gerador = new Random();
    
    public void update() throws Exception {
        timer++;
        
       if (Game.pontuacao > pontuacaoNivel && dificuldade > 15){
            nivel += 1;
            Game.playSE(3);
           
           if (nivel == 10){
                dificuldade -= 5;
                Game.stopMusic();
                Game.playMusic(4);
                isMaxLevel = true;
           }
           
           pontuacaoNivel = 350 * nivel;
           dificuldade -= 5;
        }
        
        if(timer % dificuldade == 0){
            retangulos.add(new RectObj(0, new Random().nextInt(480-40)));
        }
        
         for(int i = 0; i < retangulos.size(); i++){
            RectObj atual = retangulos.get(i);
            
             retangulos.get(i).update();
             
             if(atual.x > Game.WIDTH){
                 retangulos.remove(atual);
                 Game.contador-=3;
             }
             
             if(Game.clicado) {
                 if(Game.mx >= atual.x && Game.mx < atual.x + atual.width) {
                    if(Game.my >= atual.y && Game.my < atual.y + atual.height) {
                        Game.playSE(1);
                        Game.pontuacao += (atual.width * atual.height) / 100;
                        retangulos.remove(atual);   
                        
                        for (int n = 0 ; n < 50 ; n++) {
                            int side = gerador.nextInt(8);
                            particulas.add(new Particula(atual.x + 20, atual.y, side, side, atual.color, isMaxLevel));
                        }
                    }
                 }
             }
         }
         
         for(int i = 0; i < particulas.size() ; i++) {
             particulas.get(i).update();
             
             Particula part = particulas.get(i);
             if(part.timer >= 60) {
                 particulas.remove(part);
             }
         }
    }
    
    public void render(Graphics g){      
        for(int i = 0; i < retangulos.size(); i++){
            RectObj current = retangulos.get(i);
            Color color = new Color(255, 255, 255, 0);
            g.setColor(color);
                    
            g.drawImage(current.img, current.x, current.y, null);
           
            g.fillRect(current.x, current.y, current.width, current.height);
        }
        for(int i = 0; i < particulas.size() ; i++) {
             particulas.get(i).render(g);
         }
    }
    
}
