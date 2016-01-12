package com.coakis.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.coakis.game.ecs.entities.Coin;
import com.coakis.game.ecs.entities.Hero;
import com.coakis.game.ecs.systems.ControlSystem;
import com.coakis.game.ecs.systems.MovementSystem;
import com.coakis.game.ecs.systems.RenderSystem;

public class Game extends ApplicationAdapter {

	Engine engine;
	Hero hero;

	@Override
	public void create() {
		engine = new Engine();
		engine.addSystem(new ControlSystem());
		engine.addSystem(new RenderSystem());
		engine.addSystem(new MovementSystem());
		
		hero = new Hero(0.0f, 0.0f);
		
		engine.getSystem(RenderSystem.class).setCamTargetEntity(hero);
		
		engine.addEntity(hero);
		engine.addEntity(new Coin(100.0f, 100.0f));
	}

	@Override
	public void render() {
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
    public void resize(int width, int height) {
		engine.getSystem(RenderSystem.class).resize(width, height);
    }
	
}
