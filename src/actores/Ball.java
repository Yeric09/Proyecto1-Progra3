/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actores;

import Observer.Model;
import static java.lang.Math.pow;

/**
 *
 * @author david
 */
public class Ball extends actor {

    public int r;
    public boolean limite;
    public boolean raqueta;

    public Ball(int x, int y, double dx, double dy, int r) {
        super(x, y, dx, dy);
        this.r = r;
        this.limite = false;
    }

    @Override
    public void move(Model b) {
        limite = false;
        raqueta = false;
//        if ((y < b.a.y) && (dy > 0) && (y + r >= b.a.y) && (b.a.x < x + r) && x - r < b.a.x + b.a.w || (y <= b.a.y + b.a.w && dy < 0 && y + r >= b.a.y && (b.a.x < x + r) && x - r < b.a.x + b.a.w)) {
//            dy = -dy;
//            raqueta = true;
//        }
        ////////////////
//        if(Math.pow(this.x - b.r.x, 2)+Math.pow(b.r.y-this.y, 2)>=Math.pow(this.r+b.r.r, 2)){
//         dy= -dy;
//        }

         dx = this.x - b.b.r;
         dy = this.y - b.b.r;
        double distanceFromCenter = Math.sqrt(dx *dx + dy* dx);
        if (distanceFromCenter >= b.r.r - this.r) {
            double normalMagnitude = distanceFromCenter;
            double normalX = dx / normalMagnitude;
            double normalY = dy / normalMagnitude;
            double tangentX = -normalY;
            double tangentY = normalX;
            double normalSpeed = -(normalX * this.dx + normalY * this.dy);
            double tangentSpeed = tangentX * this.dx + tangentY * this.dy;
            this.dx =   normalX +   tangentX;
            this.dy =   normalY +   tangentY;
        }
          //https://bit.ly/2PnDC6G       
        //https://jsfiddle.net/sfarbota/wd5aa1wv
        this.x += this.dx;
        this.y += this.dy;
    }

    public double pitagorasH(int a, int b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public void calculo(Model m) {
        double distanceFromCenter = Math.sqrt(dx * dx + dy * dy);
        if (distanceFromCenter >= m.r.r - this.r) {
            double normalMagnitude = distanceFromCenter;
            double normalX = dx / normalMagnitude;
            double normalY = dy / normalMagnitude;
            double tangentX = -normalY;
            double tangentY = normalX;
            double normalSpeed = -(normalX * this.dx + normalY * this.dy);
            double tangentSpeed = tangentX * this.dx + tangentY * this.dy;
            this.dx = normalSpeed * normalX + tangentSpeed * tangentX;
            this.dy = normalSpeed * normalY + tangentSpeed * tangentY;
        }

    }
}
