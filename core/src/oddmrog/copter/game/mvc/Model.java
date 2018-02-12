package oddmrog.copter.game.mvc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Observable;
import java.util.Observer;

import oddmrog.copter.game.sprites.Copter;

/**
 * Created by oddmrog on 08.02.18.
 */

public class Model implements Observer {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private SpriteBatch sb;
    private BitmapFont font;
    private Array<Copter> copters;
    private static Music music;
    private static Model instance = null;

    public BitmapFont getFont() {
        return font;
    }

    public Array<Copter> getCopters() {
        return copters;
    }

    public SpriteBatch getSb() {
        return sb;
    }

    private Model(){
        copters = new Array<Copter>();
        copters.add(new Copter("n1"), new Copter("n2"), new Copter("n3"));
        font = new BitmapFont();
        sb = new SpriteBatch();
        music = null;
    }

    public static Model getInstance() {
        if(instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public static void initMusic(){
        music = Gdx.audio.newMusic(Gdx.files.internal("roflcopter.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public void update(float dt){

        for(Copter copter : copters){
            copter.update(dt);
            for (int i = 0; i < copters.size; i++ ){
                if(copter != copters.get(i))
                    overlapping(copter, copters.get(i));
            }
        }

    }

    public void overlapping(Copter c1, Copter c2){
        if(c1.getBounds().overlaps(c2.getBounds())){
            c1.setVelocity(-c1.getVelocity().x, -c1.getVelocity().y, 0);
            c2.setVelocity(-c2.getVelocity().x, -c2.getVelocity().y, 0);
            c1.bounce();
            c2.bounce();
        }


    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
