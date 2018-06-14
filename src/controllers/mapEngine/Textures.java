package controllers.mapEngine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Textures {

    final private String textureFile = "src/textures.json";

    public Texture[] getTextureList(){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(textureFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert bufferedReader != null;
        return new Gson().fromJson(bufferedReader,  Texture[].class);
    }


    public void addTexture(String name, double high, double low){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(textureFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert bufferedReader != null;
        var texture = new Gson().fromJson(bufferedReader,  Texture[].class);
        ArrayList<Texture> TextureList = new ArrayList<>(Arrays.asList(texture));

        var tex = new Texture();
        tex.setName(name);
        tex.setHigh(high);
        tex.setLow(low);
        tex.setID(texture.length);
        TextureList.add(tex);

        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(textureFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert writer != null;
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(new GsonBuilder().setPrettyPrinting().create().toJson(TextureList));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

