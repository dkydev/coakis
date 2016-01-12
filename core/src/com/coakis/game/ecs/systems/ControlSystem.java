package com.coakis.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.coakis.game.ecs.components.ControlComponent;
import com.coakis.game.ecs.components.VelocityComponent;

public class ControlSystem extends IteratingSystem {

	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<ControlComponent> cm = ComponentMapper.getFor(ControlComponent.class);

	@SuppressWarnings("unchecked")
	public ControlSystem() {
		super(Family.all(ControlComponent.class, VelocityComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		VelocityComponent velocity = vm.get(entity);

		float x = (Gdx.input.isKeyPressed(Keys.D) ? 1 : 0) - (Gdx.input.isKeyPressed(Keys.A) ? 1 : 0);
		float y = (Gdx.input.isKeyPressed(Keys.W) ? 1 : 0) - (Gdx.input.isKeyPressed(Keys.S) ? 1 : 0);

		velocity.x += x;
		velocity.y += y;

		velocity.rot -= (x == 1 ? 1 : -1) * Math.abs(y) + x;
	}

}
