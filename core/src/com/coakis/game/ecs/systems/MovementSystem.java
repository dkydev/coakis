package com.coakis.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.coakis.game.ecs.components.ControlComponent;
import com.coakis.game.ecs.components.PositionComponent;
import com.coakis.game.ecs.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Family.all(PositionComponent.class, VelocityComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		PositionComponent position = pm.get(entity);
		VelocityComponent velocity = vm.get(entity);
		
		position.x += velocity.x;
		position.y += velocity.y;
		position.rot = velocity.rot;
		
		velocity.x *= velocity.frictionX;
		velocity.y *= velocity.frictionY;
		velocity.rot *= velocity.frictionRot;
	}

}
