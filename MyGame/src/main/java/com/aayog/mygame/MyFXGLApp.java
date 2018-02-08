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
	private Entity player,player2;
	public int count=2;
	
	public enum EntityType {
	    PLAYER, PLAYER1
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
		Input input = getInput();
	    input.addAction(new UserAction("Player1 Move Right") {
	        @Override
	        protected void onAction() {
	            player.translateX(5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);   
	            if(count!=7 || count == 4)count++;
		    		else count = 2;
		    		player.setViewFromTextureWithBBox("cat"+count+".png");
	        }
	    }, KeyCode.D);
	    input.addAction(new UserAction("Player1 Move Left") {
	        @Override
	        protected void onAction() {
	            player.translateX(-5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);  
	            if(count!=7 || count == 4)count++;
		    		else count = 2;
	            
		    		player.setViewFromTextureWithBBox("cat"+count+".png");
	        }
	    }, KeyCode.A);
	    input.addAction(new UserAction("Player1 Move Up") {
	        @Override
	        protected void onAction() {
	            player.translateY(-5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);   
	            if(count!=7  || count == 4)count++;
		    		else count = 2;
		    		player.setViewFromTextureWithBBox("cat"+count+".png");
	        }
	    }, KeyCode.W);
	    input.addAction(new UserAction("Player1 Move Down") {
	        @Override
	        protected void onAction() {
	            player.translateY(5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);
	            if(count!=7 || count == 4)count++;
		    		else count = 2;
		    		player.setViewFromTextureWithBBox("cat"+count+".png");

	        }
	    }, KeyCode.S);
	    input.addAction(new UserAction("Player2 Move Up") {
	        @Override
	        protected void onAction() {
	            player2.translateY(-5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);
	            
	        }
	    }, KeyCode.UP);
	    input.addAction(new UserAction("Player2 Move Down") {
	        @Override
	        protected void onAction() {
	            player2.translateY(5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);    
	        }
	    }, KeyCode.DOWN);
	    input.addAction(new UserAction("Player2 Move Right") {
	        @Override
	        protected void onAction() {
	            player2.translateX(5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);    
	        }
	    }, KeyCode.RIGHT);
	    input.addAction(new UserAction("Player2 Move Left") {
	        @Override
	        protected void onAction() {
	            player2.translateX(-5); // move right 5 pixels
	            getGameState().increment("pixelsMoved", +5);    
	        }
	    }, KeyCode.LEFT);
		

	    
	}

	@Override
	protected void initGame() {
		super.initGame();
		player = Entities.builder().at(300, 300)
		        .type(EntityType.PLAYER)
		        .with(new CollidableComponent(true))
				.viewFromTextureWithBBox("cat2.png")
				.buildAndAttach(getGameWorld());
		
//		player.setScaleX(0.4);
//		player.setScaleY(0.4);

		player2 = Entities.builder().at(700, 500)
		        .type(EntityType.PLAYER1)
		        .with(new CollidableComponent(true))
				.viewFromTextureWithBBox("bird.png")
				.buildAndAttach(getGameWorld());
		
//		player2.setScaleX(.2);
//		player2.setScaleY(.2);

		
	}

	@Override
	protected void initUI() {
		super.initUI();
		Text textPixels = new Text();
	    textPixels.setTranslateX(50); // x = 50
	    textPixels.setTranslateY(100); // y = 100
	    getGameScene().addUINode(textPixels);
	    textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());
	}

	@Override
	protected void initGameVars(Map<String, Object> vars) {
	    vars.put("pixelsMoved", 0);
		super.initGameVars(vars);
	}

	@Override
	protected void initPhysics() {
		getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.PLAYER1) {

	        // order of types is the same as passed into the constructor
	        @Override
	        protected void onCollisionBegin(Entity player, Entity player2) {
	            getAudioPlayer().playSound("explosion.wav");
	            player2.removeFromWorld();
	            getGameWorld().addEntity(player2);

	        }
	    });
		super.initPhysics();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
