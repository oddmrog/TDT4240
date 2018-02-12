package oddmrog.copter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.Observable;
import java.util.Observer;

import oddmrog.copter.game.mvc.Model;
import oddmrog.copter.game.mvc.View;
import oddmrog.copter.game.sprites.Copter;


public class CopterGame extends ApplicationAdapter implements Observer{
	private View view;
	private Model model;

	@Override
	public void create (){
		this.model = Model.getInstance();
		Model.initMusic();
		this.view = new View(model);
//		view.addObserver(model);
		view.addObserver(this);
	}


	@Override
	public void render() {
		view.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		model.getSb().dispose();
	}

    @Override
    public void update(Observable observable, Object o) {
        Array<Copter> copters = model.getCopters();
        for(Copter copter : copters) {
            copter.turn();
            System.out.println("turned");
        }
    }
}
