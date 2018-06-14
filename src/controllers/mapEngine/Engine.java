package controllers.mapEngine;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Random;

public class Engine{

    private GraphicsContext   gc;
    private GenerateMap       render;
    private Canvas            canvas;
    private ArrayList<Image>  images;
    private int               textureSize   = 32;
    private Image             textureNotFound;
    private Texture[]         textures;
    private String            textureParams = "";
    private int[][]           map;
    private int CameraX = 0,  CameraY = 0;

    private int RenderTime      = 0;
    private int MapX = 24, MapY = 15;
    private int FPS             = 0;

    private int mapSeed = 0;

    public Engine(Canvas canvas){
        this.canvas = canvas;
        gc          = canvas.getGraphicsContext2D();
    }

    public void autoInit(){
        mapSeed     = new Random().nextInt(900000) + 1;
        render      = new GenerateMap();

        canvas.setWidth(MapX * textureSize);
        canvas.setHeight(MapY * textureSize);

        new Thread(() -> {
            loadTextures();
            map           = generateWorld(mapSeed, 0.01, 0.01);
            update();
        }).start();
    }

    private void loadTextures(){
        images    = new ArrayList<>();
        textures  = new Textures().getTextureList();
        for (Texture texture : textures) {
            images.add(new Image("/textures/" + texture.getName()));
            textureParams += texture.getID() + " " + texture.getHigh() + " " + texture.getLow() + " ";
        }
        textureNotFound = new Image("/textures/notFound.png");
    }

    public void updateTextures(){
        images    = new ArrayList<>();
        textures  = new Textures().getTextureList();
        for (Texture texture : textures) {
            images.add(new Image("/textures/" + texture.getName()));
            textureParams += texture.getID() + " " + texture.getHigh() + " " + texture.getLow() + " ";
        }
        map = generateWorld(mapSeed, 0.01, 0.01);
    }

    private int[][] generateWorld(int seed, double X, double Y){
        return render.perlinArrayToImageID(render.perlinToArray(100,100 , seed, X,  Y), textureParams);
    }

    private void update(){
        Platform.runLater(this::userControls);
        new Thread(() -> {
                for(;;){
                    long startTime = System.currentTimeMillis();
                    try {
                        Thread.sleep(25 - RenderTime);
                        moveCamera();
                        Platform.runLater(this::renderWorld);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    FPS = (1000 / (int) (System.currentTimeMillis() - startTime));
                }
        }).start();
    }

    // Smooth camera controls
    private boolean W = false, D = false, S = false, A = false;
    private void moveCamera(){
        if(W) CameraY--;
        if(S) CameraY++;
        if(D) CameraX++;
        if(A) CameraX--;

        if(CameraX  < 0) CameraX = 0;
        if(CameraY  < 0) CameraY = 0;

        if(MapY + CameraY > map.length)    CameraY--;
        if(MapX + CameraX > map[0].length) CameraX--;
    }

    private void userControls(){
        // > getParent().getParent().getParent()
        canvas.getParent().getParent().getParent().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) W = true;
            if(e.getCode() == KeyCode.A) A = true;
            if(e.getCode() == KeyCode.S) S = true;
            if(e.getCode() == KeyCode.D) D = true;
        });

        canvas.getParent().getParent().getParent().setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W) W = false;
            if(e.getCode() == KeyCode.A) A = false;
            if(e.getCode() == KeyCode.S) S = false;
            if(e.getCode() == KeyCode.D) D = false;
        });
    }

    // Render Map
    private void renderWorld(){
        gc.clearRect(0,0 , canvas.getHeight(),canvas.getWidth());
        long startTime = System.currentTimeMillis();
        try{
            for(int x = CameraX; x < MapX + CameraX; x++){
                for(int y = CameraY; y < MapY + CameraY; y++){
                    if(textures.length == 0)
                        gc.drawImage( textureNotFound, textureSize * (x - CameraX), textureSize * (y - CameraY), textureSize, textureSize);
                    for(int a = 0; a < textures.length; a ++){
                        if(map[x][y] == textures[a].getID()){
                            gc.drawImage(images.get(a) , textureSize * (x - CameraX), textureSize * (y - CameraY), textureSize, textureSize);
                        }
                    }
                }
            }
        }catch ( Exception e){
            System.out.println(" :( ");
        }
        RenderTime = (int) (System.currentTimeMillis() - startTime);
    }

    public int getFPS(){
        return FPS;
    }
    public int getCameraX(){
        return CameraX;
    }
    public int getCameraY(){
        return CameraY;
    }
    public int getTextureSize(){
        return textureSize;
    }
    public int getMapX(){return MapX; }
    public int getMapY(){return MapY; }

    public void setCameraX(int CameraX){this.CameraX = CameraX;}
    public void setCameraY(int CameraY){this.CameraY = CameraY;}
    public void setTextureSize(int textureSize){this.textureSize = textureSize;}
    public void setMapX(int MapX){this.MapX = MapX;}
    public void setMapY(int MapY){this.MapY = MapY;}

}
