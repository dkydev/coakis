package com.coakis.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.coakis.game.ecs.components.PositionComponent;
import com.coakis.game.ecs.components.SpriteComponent;
import com.coakis.game.ecs.components.VelocityComponent;

public class RenderSystem extends EntitySystem {

	private static final float camZoomSpeed = 0.02f;
	private static final float camRotationSpeed = 1.0f;
	private static final float camTranslateSpeed = 5.0f;
	
	private ImmutableArray<Entity> entities;
	private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

	private static TextureAtlas atlas;
	private SpriteBatch batch;

	private OrthographicCamera cam;
	private Entity camTargetEntity;
	private PositionComponent camTargetPositionComponent;
	
	public RenderSystem() {
		RenderSystem.atlas = new TextureAtlas(Gdx.files.internal("textures_compiled/textures.atlas"));
		batch = new SpriteBatch();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width, height);
		cam.position.set(width / 2f, height / 2f, 0);
		cam.update();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
	}

	@Override
	public void update(float deltaTime) {
		
		handleCamInput();
		
		Entity hero = entities.first();
		PositionComponent heroPosition = hero.getComponent(PositionComponent.class);
		
		Gdx.app.log("coakis", heroPosition.toString());
		
		//cam.lookAt(0, 0, 0);
				
		if (camTargetPositionComponent != null) {
			cam.lookAt(camTargetPositionComponent.x, camTargetPositionComponent.y, 0);
		}
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		for (int i = 0; i < entities.size(); ++i) {
			Entity entity = entities.get(i);
			SpriteComponent sprite = sm.get(entity);
			PositionComponent position = pm.get(entity);
			TextureRegion region = sprite.getKeyFrame(deltaTime);
			batch.draw(region, position.x, position.y, region.getRegionWidth() * 0.5f, region.getRegionHeight() * 0.5f,
					region.getRegionWidth(), region.getRegionHeight(), 1, 1, position.rot);
		}

		// batch.draw(currentFrame, x, y);
		batch.end();

	}

	private void handleCamInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            cam.zoom += camZoomSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            cam.zoom -= camZoomSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            cam.rotate(-camRotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.V)) {
            cam.rotate(camRotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-camTranslateSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(camTranslateSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -camTranslateSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, camTranslateSpeed, 0);
        }
	}

	public static TextureAtlas getAtlas() {
		return RenderSystem.atlas;
	}

	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.update();
	}

	public Entity getCamTargetEntity() {
		return camTargetEntity;
	}

	public void setCamTargetEntity(Entity camTargetEntity) {
		camTargetPositionComponent = camTargetEntity.getComponent(PositionComponent.class);
		if (camTargetPositionComponent != null) {
			this.camTargetEntity = camTargetEntity;
		}
	}
}
