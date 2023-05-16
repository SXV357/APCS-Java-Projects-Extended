import java.awt.*;
import javax.swing.*;

public class Animal implements Comparable<Animal>
{
    private String name;
    private Image image;

    public Animal(String n)
    {    
        name  = n;
        image = new ImageIcon("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/HorsingAround/Animals/" + name + ".png").getImage();
    }

    @Override
    public int compareTo(Animal other){
        if (this.getHeight() > other.getHeight()){
            return 1;
        }
        else if (this.getHeight() < other.getHeight()){
            return - 1;
        }
        return 0;
    }

    public String getName()
    {
        return name;
    }

    public int getWidth()
    {
        return image.getWidth(null);
    }

    public int getHeight()
    {
        return image.getHeight(null);
    }

    public Image getImage()
    {
        return image;
    }
}