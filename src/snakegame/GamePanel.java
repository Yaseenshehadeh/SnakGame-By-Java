/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.*;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import javax.swing.JPanel;
import sun.java2d.loops.DrawLine;

/**
 *
 * @author AL-OLABY
 */
public class GamePanel extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form GamePanel
     */
    static final int SCREEN_WIDTH=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;
    static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DEALY =75;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    //long snake to number square
    int bodyParts=4;
    int applesEaten;
    int appleX;
    int appleY;
    char direction='R';
    boolean running =false;
    Timer timer;
    Random random;
    
    
    
     GamePanel() {
        random =new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newApple();
        running =true;
        timer= new Timer(DEALY,this);
        timer.start();
        
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
            /*
             for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                 g.setColor(Color.GRAY);
                 g.drawLine(i*UNIT_SIZE,0, i*UNIT_SIZE, SCREEN_HEIGHT);
                 g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            
             }
            */
             g.setColor(Color.RED);
             g.fillOval(appleX, appleY ,UNIT_SIZE, UNIT_SIZE);
        
            for(int i=0;i< bodyParts;i++){
              if(i==0){
                  g.setColor(Color.GREEN);
                  g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                 }
                 else{
                       // g.setColor(new Color(45,180,0));
                        g.setColor(new Color(random.nextInt(255)
                                ,random.nextInt(255),random.nextInt(255)));
                        g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
              }
             }
            
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics =getFontMetrics(g.getFont());
            g.drawString("Score:"+applesEaten,
                (SCREEN_WIDTH -metrics.stringWidth("Score:"+applesEaten))
               /2,g.getFont().getSize());
            }
        else{
            gameOver(g);
        }
    }
    public void newApple(){
        //show apple in random square width
        appleX =random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE ;
        //show apple in random square hig
        appleY =random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE ;
    }
    public void move(){
        for(int i =bodyParts;i>0;i--){
            x[i] =x[i-1];
            y[i] =y[i-1];
        }
        switch(direction){
            case 'U':
                y[0]=y[0]-UNIT_SIZE;
                break;
            case 'D':    
                y[0]=y[0]+UNIT_SIZE;
                break;
            case 'L':    
                x[0]=x[0]-UNIT_SIZE;
                break;
            case 'R':    
                x[0]=x[0]+UNIT_SIZE;
                break;
                
                
        }
        
    }
    public void checkApple(){
        if((x[0]==appleX)&& (y[0]==appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
            
        }
    }
    public void checkCollisios(){
        //check if head with body
        for(int i=bodyParts;i>0;i--){
            if((x[0]==x[i])&&(y[0]==y[i])){
                running =false;
             
            }
        }
        //check if head toches border left <--
        if(x[0]<0){
            running=false;
            
        }
        //check if head toches border reghit -->
        if(x[0]>SCREEN_WIDTH){
            running =false;
            
        }
        //check if head toches border top 1
         if(y[0]<0){
            running =false;
        }
         //check if head toches border bottom 2
         if(y[0]>SCREEN_HEIGHT){
            running =false;
        }
         
         if(!running){
             timer.stop();
         }
         
    }
    public void gameOver(Graphics g){
        //Score 
         g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics =getFontMetrics(g.getFont());
            g.drawString("Score:"+applesEaten,
                (SCREEN_WIDTH -metrics.stringWidth("Score:"+applesEaten))
               /2,g.getFont().getSize());
            
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics1 =getFontMetrics(g.getFont());
        g.drawString("Game Over",
                (SCREEN_WIDTH -metrics1.stringWidth("Game Over"))
               /2,SCREEN_WIDTH/2);
        //Game Over text
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction !='R'){
                        direction='L';
                    }
                    break;
                    case KeyEvent.VK_RIGHT:
                    if(direction !='L'){
                        direction='R';
                    }
                    break;
                     case KeyEvent.VK_UP:
                    if(direction !='D'){
                        direction='U';
                    }
                    break;
                     case KeyEvent.VK_DOWN:
                    if(direction !='U'){
                        direction='D';
                    }
                    break;
                    
            }
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisios();
            
        }
        repaint();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
