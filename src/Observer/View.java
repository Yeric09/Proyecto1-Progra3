
package Observer;

import java.awt.event.KeyEvent;
import actores.Ball;
import actores.racket;
import actores.circulo;
import java.awt.Color;
//Usar JOptionPane
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Observable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;

public class View extends JFrame implements java.util.Observer {

    Model model;
    Controller controller;
    static final String CRASH_BALL = "uh.wav";
    static final String CRASH_RACKET = "golpe2.wav";
    Clip golpe;
    Clip golpe2;
    
    public View() {
        this.setSize(700, 650);
        this.setLocationRelativeTo(null);
         golpe = this.loadSound(CRASH_BALL);
         golpe2 = this.loadSound(CRASH_RACKET);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                fromKeyPressed(evt);
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                fromKeyReleased(evt);
            }
        });

    }

    Clip loadSound(String path) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
            AudioFormat soundFormat = audioInputStream.getFormat();
            int soundSize = (int) (soundFormat.getFrameSize() * audioInputStream.getFrameLength());
            byte[] soundData = new byte[soundSize];
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat, soundSize);
            audioInputStream.read(soundData, 0, soundSize);
            Clip clip = (Clip) AudioSystem.getLine(soundInfo);
            clip.open(soundFormat, soundData, 0, soundSize);
            return clip;

        } catch (Exception e) {
            return null;
        }
    }

    private void fromKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.move(Model.ARR);
                break;
            case KeyEvent.VK_DOWN:
                controller.move(Model.ABA);
                break;
            case KeyEvent.VK_LEFT:
                controller.move(Model.IZQ);
                break;
            case KeyEvent.VK_RIGHT:
                controller.move(Model.DER);
                break;
        }
    }

    private void fromKeyReleased(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                controller.stopHor();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                controller.stopVer();
                break;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.renderModel(model, g);
    }

    public void renderModel(Model m, Graphics media) {
        remderCirculo(m.r, media);
        renderBall(m.b, media);
        renderRacket(m.a, media);
    }

    public void remderCirculo(circulo circ, Graphics media) {
//        media.setColor(Color.BLACK);
//        media.drawRect(r.x, r.y, r.w, r.h);
//        ImageIcon dibujo = new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/escenario.jpg")).getImage());
//        media.drawImage(dibujo.getImage(), r.x, r.y, r.r, r.r, null);
        media.setColor(Color.BLACK);
        media.fillOval(circ.x - circ.r, circ.y - circ.r, 2 * circ.r, 2 * circ.r);
    }

    public void renderBall(Ball b, Graphics media) {
//        media.setColor(Color.RED);
//        media.fillOval(b.x - b.r, b.y - b.r, 2 * b.r, 2 * b.r);
        ImageIcon dibujo = new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/dragonBall2.png")).getImage());
        media.drawImage(dibujo.getImage(), b.x - b.r, b.y - b.r, 2 * b.r, 2 * b.r, null);
       
        if (b.limite) {
            golpe.setFramePosition(0);
            golpe.start();
        }
    }

    public void renderRacket(racket r, Graphics media) {

        ImageIcon dibujo = new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/nube.png")).getImage());
        media.drawImage(dibujo.getImage(), r.x, r.y, r.w, r.h, null);
        
        if (model.b.raqueta) {
            golpe2.setFramePosition(0);
            golpe2.start();
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
