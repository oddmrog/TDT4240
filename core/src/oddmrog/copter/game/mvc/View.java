package oddmrog.copter.game.mvc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import oddmrog.copter.game.CopterGame;
import oddmrog.copter.game.sprites.Copter;


/**
 * Created by oddmrog on 08.02.18.
 */

public class View extends Observable{

    private Model model;
    private ArrayList<Observer> observers;

    public View(Model model){
        this.model = model;
        observers = new ArrayList<Observer>();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(255, 0, 254, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        draw();
        handleInput();
        model.update(delta);
    }

    private void draw() {
        SpriteBatch sb = model.getSb();
        Array<Copter> copters = model.getCopters();
        sb.begin();
        model.getFont().draw(sb, copters.get(0).getPosition().toString(), 25, Model.HEIGHT - 25);
        for(Copter copter : copters) {
            sb.draw(copter.getTexture(), copter.getPosition().x, copter.getPosition().y,
                    copter.getTexture().getWidth(), copter.getTexture().getHeight(), 0, 0,
                    copter.getTexture().getWidth(), copter.getTexture().getHeight(), !copter.isFlipped(), false);
        }
        sb.end();
    }

    public void handleInput(){
        Array<Copter> copters = model.getCopters();
        if(Gdx.input.justTouched()){
            notifyObservers();
        }

    }

    @Override
    public synchronized void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        super.deleteObserver(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(this, "lol");
        }
    }
}
