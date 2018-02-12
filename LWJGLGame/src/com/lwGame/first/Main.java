package com.lwGame.first;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Main {

	public static void main(String[] args) {
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(640, 480, " My first Game", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		
//		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//		glfwSetWindowPos(window, (videoMode.width() - 640)/2, (videoMode.height()-480)/2);		
		float x = 0.0f;
		float y = 0.0f;

		while(!glfwWindowShouldClose(window)) {
			if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) x += 0.01f;
			if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) x -= 0.01f;
			if (glfwGetKey(window, GLFW_KEY_W)==GL_TRUE)y += 0.01f;
			if (glfwGetKey(window, GLFW_KEY_S)==GL_TRUE)y -= 0.01f;
			
			if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE)
				glfwSetWindowShouldClose(window, true);

			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS);
				glColor3f(x, y, 0);
				glVertex2f(-0.1f+x, 0.1f+y);
				glVertex2f(0.1f+x, 0.1f+y);
				glVertex2f(0.1f+x, -0.1f+y);
				glVertex2f(-0.1f+x, -0.1f+y);
			glEnd();
			glfwSwapBuffers(window);

		}
		glfwTerminate();
	}
	

}
