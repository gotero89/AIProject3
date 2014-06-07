package fourinaline;

public class StopThread extends Thread {

	private long time = 0;
	private boolean stopped;
	
	public StopThread(long time) {
		this.time = time;
	}
	
	@Override
	public void run() {
		long startAt = 0;
		 while(true) {
			startAt = System.currentTimeMillis();
			   try {
			    Thread.sleep(this.time);
			   }catch (InterruptedException e) {
				   this.time -= (System.currentTimeMillis() - startAt);
				   if(this.time > 0)
					   continue; 
			   }
			   setStop(true);
			   break;
		 }
	}
	
	public void setStop(boolean stopped) {
		this.stopped = stopped;
	}
	
	public boolean isStop() {
		return stopped;
	}
	
}
