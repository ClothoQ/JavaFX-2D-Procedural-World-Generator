package controllers.mapEngine;
import java.io.*;

public class GenerateMap{

    double[][] perlinToArray(int mapSizeX, int mapSizeY, int seed, double Posx, double Posy){
        var perlin_y   = 0.1;
        var perlin_x   = 0.1;
        var mapSize    = 0;
        double Map[][] = new double[mapSizeX][mapSizeY];

        long startTime = System.currentTimeMillis();

        for(int x = 0; x != mapSizeX; x++){
            for(int y = 0; y != mapSizeY;  y++ ){
                Map[y][x]     = PerlinNoise.noise(perlin_x + seed + Posx, perlin_y + seed + Posy);
                perlin_y     += 0.1;
                mapSize++;
            }
            perlin_y = 0.1; perlin_x += 0.1;
        }

        System.out.println("perlinToArray[ " + (System.currentTimeMillis() - startTime) + "ms [ MapSize: "+mapSize+" ] - Done");
        return Map;
    }

    public void imageArrayToFile(String mapName, int[][] map){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(mapName+".txt") ));
            for(int x = 0; x !=map.length; x++ ){
                for(int y = 0; y !=map[x].length; y++ ) {
                    writer.write(map[x][y] +  " ");
                }   ((BufferedWriter) writer).newLine();
            }
        } catch (IOException ignored) {
        } finally { try {
            assert writer != null;
            writer.close();} catch (Exception ignored) {} }
    }

    int[][] perlinArrayToImageID(double[][] perlinArray, String MapCreation){
            var imageID          = new int[perlinArray.length][perlinArray[0].length];
            var startTime        = System.currentTimeMillis();
            var MapCreationSplit = MapCreation.split(" ");
            for(int x = 0; x !=perlinArray.length; x++ ){
                for(int y = 0; y !=perlinArray[x].length; y++ ) {
                    for(int params = 0; params != MapCreationSplit.length / 3; params++){
                        try {
                            if (perlinArray[x][y] < Double.parseDouble(MapCreationSplit[1 + (params * 3)]) && perlinArray[x][y] > Double.parseDouble(MapCreationSplit[2 + (params * 3)]))
                                imageID[x][y]     = Integer.parseInt(MapCreationSplit[(params * 3)]);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            System.out.println("perlinArrayToImageID[ " + (System.currentTimeMillis() - startTime) + "ms ] - Done");
            return imageID;
    }

    public void printPerlinArray(double[][] perlinArray){
        for(double[] perlin : perlinArray){
            for(double val  : perlin){
                System.out.println(val);
            }
        }
    }

}
