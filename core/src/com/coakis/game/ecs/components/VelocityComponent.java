package com.coakis.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
	
	public float x = 0.0f;
    public float y = 0.0f;
    public float rot = 0.0f;
    public float frictionX = 0.7f;
    public float frictionY = 0.7f;
    public float frictionRot = 0.9f;
    
    public VelocityComponent(float x2, float y2) {
		super();
		this.setVelocity(x2, y2);
	}
    
    public void setVelocity(float x2, float y2) {
    	this.x = x2;
    	this.y = y2;
    }
}
