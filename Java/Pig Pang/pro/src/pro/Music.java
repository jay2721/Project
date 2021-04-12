package pro;

import java.io.FileInputStream;


import javazoom.jl.player.Player;
import java.util.Timer;
import java.util.TimerTask;
class Music {

	   Player _play = null;
	    Timer _timer = new Timer();
		public  void run(String fname) {		
			Timer _timer = new Timer(); 				
	    	_timer.schedule(new TimerTask() {
					public void run() {
						try {
							FileInputStream file=new FileInputStream(fname);	
							_play =new Player(file);
							_play.play();			
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, 0);
			}
		    public void stop() {
		    	_play.close();
		       _timer.cancel(); 
		    }


}

