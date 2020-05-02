module MyGame {
	requires jdk.jshell;
	requires javafx.fxml;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires javafx.graphics;
	
	exports main;
	exports graphics.sprites;
	exports controllers;
	opens controllers;
	exports graphics;
}