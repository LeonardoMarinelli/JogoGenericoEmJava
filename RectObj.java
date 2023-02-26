package model;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class RectObj extends Rectangle{
    
    Random gerador = new Random();
    Color color = new Color(gerador.nextInt(255), gerador.nextInt(255), gerador.nextInt(255));
    String files[] = {"aluminio", "caneca", "copo", "garrafa", "garrafaPlastico", "garrafaPlasticoDois", "garrafaVidro", "jornal", "lata", "latinha", "papelao", "sacola"};
    BufferedImage img = ImageIO.read(new File(getClass().getResource(files[new Random().nextInt(files.length)] + ".png").getPath()));  
    
    int sentido = 1;
    int repeticaoDelta = 0;
    int deltaY = 0;
    
    public int speed = 0;
    
    public RectObj(int x, int y) throws IOException{
        super(x, y, 0, 0);
        setSize(img.getWidth(), img.getHeight());
        speed = new Random().nextInt(15) + 4;
    }
    
    public void update(){
        
        //Movimentação de Y
        if (gerador.nextInt(500) < 10 && x > 200)
                sentido = -1;
        if (x < 20)
            sentido = 1;
        
        //Movimentação de X
        if (repeticaoDelta == 0){
            repeticaoDelta = gerador.nextInt(10) + 10;
            deltaY = gerador.nextInt(3) - 1;
            deltaY *= 2;
        } else {
            repeticaoDelta--;
        }
        
        if (y >= 440)
            deltaY = -2;
        
        if (y <= 64)
            deltaY = 2;
        
        //Atualizar movimento
        x += speed * sentido;
        y += deltaY;
    }
}
