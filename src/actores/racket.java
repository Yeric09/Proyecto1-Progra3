/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actores;

import Observer.Model;

/**
 *
 * @author david
 */
public class racket extends actor {

    public int h;
    public int w;

    public racket(int h, int w, int x, int y, double dx, double dy) {
        super(x, y, dx, dy);
        this.h = h;
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

//    public void arriba(Model b) {
//        dy = 0;
//        if(y>b.r.y){
//         dy = -5;
//        }
//          y +=dy;
//    }
//    public void abajo(Model b) {
//         dy = 0;
//        if(y< b.r.y+b.r.h-50){
//         dy = 5;        
//        }
//         y +=dy;
//       
//    }
//    public void derecha(Model b) {
//        dx = 0;
//        if(x<b.r.x+b.r.w-110){
//         dx =5;
//        }
//                 x +=dx;
//   }
//   public void izquierda(Model b) {
//       dx=0;
//      if(x >b.r.x){
//         dx = -5;
//        } 
//      x +=dx;
//    }
    @Override
    public void move(Model m) {
        if ((x + dx + w < m.r.x + m.r.r && y + dy + h < m.r.x + m.r.r)) {
            x = x + (int) dx;
            y = y + (int) dy;           
        }


    }
}