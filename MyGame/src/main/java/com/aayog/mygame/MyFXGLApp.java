package com.aayog.mygame;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MyFXGLApp extends GameApplication {
	private Entity player1,player2,ball;
	public int count=2;
	private int y;
	
	public enum EntityType {
	    PLAYER, PLAYER2, Ball;
	}
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("My App");
		settings.setWidth(1280);
	    settings.setHeight(780);
	    settings.setVersion("0.1");
	    settings.setIntroEnabled(false);
	    settings.setMenuEnabled(false); 
	}

	@Override
	protected void initInput() {
		super.initInput();
	    
	}

	@Override
	protected void initGame() {
		super.initGame();
		
	}

	@Override
	protected void initUI() {
		super.initUI();
		
	}

	@Override
	protected void initGameVars(Map<String, Object> vars) {
		super.initGameVars(vars);
	}

	@Override
	protected void initPhysics() {
		getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.Ball) {
	        @Override
	        protected void onCollisionBegin(Entity player1, Entity ball) {

	        }
	    });
		
		
		super.initPhysics();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
