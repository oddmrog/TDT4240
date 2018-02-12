package oddmrog.copter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import java.util.Observable;
import java.util.Observer;

import oddmrog.copter.game.mvc.Model;
import oddmrog.copter.game.mvc.View;


public class CopterGame extends ApplicationAdapter implements Observer {
	private View view;
	private Model model;

	@Override
	public void create (){
		this.model = Model.getInstance();
		model.initMusic();
		this.view = new View(model);
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

	}
}
