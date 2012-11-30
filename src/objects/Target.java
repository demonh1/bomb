
package objects;

import coordinate.Coord;
import java.util.Random;

/**
 *
 * @author demonh1
 */
public class Target {
    
    static int number = 0;
    static String names[] = {"Tank", "Bunker", "Rocket"};

    int num ;
     Coord coord;
     String name;
     final Random generator;
     
      public Target () {
         Target.number += 1;
        this.num = Target.number;
        this.generator = new Random();
        this.name = Target.names[this.generator.nextInt(Target.names.length)] ;
        this.coord = new Coord();
        this.sayAbout();
     }
      
       public Coord getCoord(){
        return this.coord;
    }
    // Возвращает координты цели в виде строки.
    public String getCoordString(){
        return this.coord.toString();
    }
    // Возвращает тип цели.
    public String getName(){
        return this.name;
    }
    //Печатает сообщение от имени этой цели
    public void say(String s){
        System.out.println("Target("+ this.num +"): "  + ": (" + s + ");");
    }

     // Печатает сообщение о запуске цели и ее кординатах
    public final void sayAbout(){
        System.out.println("Target("+ this.num +"): "
                + this.name  + ": (" + this.coord.toString()+");");
    }
    
}
