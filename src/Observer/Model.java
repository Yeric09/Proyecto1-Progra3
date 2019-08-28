/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.util.Observable;
import actores.Ball;
import actores.racket;
import actores.circulo;
import java.awt.event.KeyEvent;

/**
 *
 * @author david
 */
public class Model extends Observable {

    public circulo r;
    public Ball b;
    public racket a;
    static final int ARR = KeyEvent.VK_UP;
    static final int ABA = KeyEvent.VK_DOWN;
    static final int DER = KeyEvent.VK_RIGHT;
    static final int IZQ = KeyEvent.VK_LEFT;

    public Model() {
        r = new circulo(350, 350, 295);
        b = new Ball(410, 500, 5, 5, 60);
        a = new racket(60, 120, 410, 500, 0, 0);

    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public void step() {
        b.move(this);
        a.move(this);
        this.setChanged();
        this.notifyObservers();
    }

    public void start() {
        final int delay = 90;

        Runnable code = new Runnable() {
            public void run() {
                while (true) {
                    step();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(delay);//Para que es esto?? No entiendo lo de los hilos
                    } catch (InterruptedException ex) {

                    }
                }
            }
        };
        Thread threat = new Thread(code);
        threat.start();
    }

    public void move(int flecha) {
        switch (flecha) {
            //NO ES MEJOR UTILIZAR EL SET PARA CADA UNO DE ELLOS??
            case KeyEvent.VK_UP:
                a.dy=-10;
                break;

            case KeyEvent.VK_DOWN:
                a.dy= 10;
                break;
            case KeyEvent.VK_RIGHT:
                a.dx=10;
                break;
            case KeyEvent.VK_LEFT:
                a.dx = -10;
                break;
        }
    }

    public void stopVer() {
        a.setDy(0);
    }

    public void stopHor() {
        a.setDx(0);
    }

}
