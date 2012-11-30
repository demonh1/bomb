
package objects;

import coordinate.Coord;
import java.util.Random;

/**
 *
 * @author demonh1
 */
public class Bomber {
    
     static int number = 0;
    static String names[] = {"T-50", "S-37", "MIG-31", "SU-24", "SU-27"};

    int num = 0;
     String name;
     Random generator;
     Coord coord;

   public Bomber () {
       Bomber.number += 1;
        this.num = Bomber.number;
        this.generator = new Random();
        this.name = Bomber.names[this.generator.nextInt(Bomber.names.length)] ;
        this.coord = new Coord();
        this.sayAbout();
   }

   public String getName(){
        return this.name;
    }

   public void say(String s){
        System.out.println("Bomber("+ this.num +"): " + ": (" + s + ");");
    }

    private void sayAbout() {
         System.out.println("Bomber("+ this.num +"): " +
                 this.name  +  ": (" + this.coord.toString()+");");
    }

    
}
