package com.coakis.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.coakis.game.ecs.components.PositionComponent;
import com.coakis.game.ecs.components.SpriteComponent;
import com.coakis.game.ecs.components.VelocityComponent;
import com.coakis.game.ecs.systems.RenderSystem;

public class Coin extends Entity {

	public Coin(float x, float y) {
		super();
		this.add(new PositionComponent(x, y));
		this.add(new VelocityComponent(-2, 0));
		this.add(new SpriteComponent(RenderSystem.getAtlas(), "sprites/c", 0.1f));
		this.getComponent(VelocityComponent.class).frictionX = 1;
	}

}
