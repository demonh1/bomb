
package coordinate;

import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author demonh1
 */
public class Coord {
    
    TreeMap <String, Integer> data;
    TreeMap <String, Integer> range;
    Random generator;
    
    // constructor
    public Coord(){
    this.generator = new Random();
    this.data = new TreeMap <>();
    this.range = new TreeMap <>();
    this.setRange();
    this.data.put("x", this.generator.nextInt(this.range.get("x")));
    this.data.put("y", this.generator.nextInt(this.range.get("y")));
}
    
    // задание координат
    public final void setRange (){
        this.range.put("x", new Integer(100));
        this.range.put("y", new Integer(100));
    }
    
    // расстояние мкжду двумя точками
public double distance(Coord coord){

    double xPart = (coord.data.get("x")- this.data.get("x"))^2;
    double yPart = (coord.data.get("y")- this.data.get("y"))^2;

    return (Math.sqrt(xPart + yPart)) ;
}

//коорд -> строка x,y

    @Override
 public String toString(){
        return ""
                + this.data.get("x").toString()
                + ","
                + this.data.get("y").toString();
    }
 //коорд -> строка [x,y]
   public String toBrackets(){
        return "["
                + this.data.get("x").toString()
                + ", "
                + this.data.get("y").toString()
                + "]";
    }

    // перевод строки х,у в коорд
    public void fromString(String str){
        String format[] = str.split(",");
        this.data.put("x", Integer.parseInt(format[0]));
        this.data.put("y", Integer.parseInt(format[1]));

    }
    
}
