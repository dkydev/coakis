package com.coakis.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
	public float x = 0.0f;
    public float y = 0.0f;
    public float rot = 0.0f;
    
	public PositionComponent(float x2, float y2) {
		super();		
		this.setPosition(x2, y2);
	}

	public void setPosition(float x2, float y2) {
		this.x = x2;
		this.y = y2;
	}
	
	@Override
	public String toString() {
		return x + ", " + y;
	}
}
