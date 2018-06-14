package controllers.mapEngine;

public class Texture {

    private String textureName    = "";
    private double High           = 0;
    private double Low            = 0;
    private int ID                = 0;

    public Texture(){ }

    public Texture(String name, int High, int Low){
        this.textureName = textureName;
    }

    public void setLow(double Low){
        this.Low = Low;
    }

    public double getLow(){
        return this.Low;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public void setHigh(double High){
        this.High = High;
    }

    public double getHigh(){
        return this.High;
    }

    public void setName(String textureName){
        this.textureName = textureName;
    }

    public String getName(){
        return this.textureName;
    }


}
