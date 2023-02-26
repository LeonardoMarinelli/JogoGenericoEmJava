package model;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;

public class Particula extends Rectangle{
    
    public Color color;
    Random gerador = new Random();
    
    private int speed = 0;
    private int rotation = 0;
    public int timer = 0;
    
    public double xa, ya;
    public double dx, dy;
    
    private boolean isMaxLevel = false;
    
    public Particula(int x, int y, int width, int height, Color color, boolean isMaxLevel){
        super(x, y, width, height);
        
        this.color = color;
        this.isMaxLevel = isMaxLevel;
        
        xa = x;
        ya = y;
        
        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    
        speed = 8;
    }
    
    public void update(){
        xa += dx*speed;
        ya += dy*speed;
        
        timer++;
    }
    
    public void render(Graphics g) {

        if(!isMaxLevel)
            g.setColor(this.color);
        else
            g.setColor(new Color (gerador.nextInt(255), gerador.nextInt(255), gerador.nextInt(255)));
        
        g.fillRect((int)xa, (int)ya, width, height);
    }
}
