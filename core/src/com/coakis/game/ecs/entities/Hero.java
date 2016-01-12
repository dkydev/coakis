package com.coakis.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.coakis.game.ecs.components.ControlComponent;
import com.coakis.game.ecs.components.PositionComponent;
import com.coakis.game.ecs.components.SpriteComponent;
import com.coakis.game.ecs.components.VelocityComponent;
import com.coakis.game.ecs.systems.RenderSystem;

public class Hero extends Entity {

	public Hero(float x, float y) {
		super();
		this.add(new PositionComponent(x, y));
		this.add(new VelocityComponent(0, 0));
		this.add(new ControlComponent());
		this.add(new SpriteComponent(RenderSystem.getAtlas(), "sprites/cat", 0.2f));
	}

}
