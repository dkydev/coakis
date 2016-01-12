package com.coakis.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {

	private Animation animation;
	private float stateTime = 0.0f;

	public SpriteComponent(TextureAtlas atlas, String spriteName, float frameRate) {
		animation = new Animation(frameRate, atlas.findRegions(spriteName));
	}

	public TextureRegion getKeyFrame(float deltaTime) {
		stateTime += deltaTime;
		return animation.getKeyFrame(stateTime, true);
	}

}
