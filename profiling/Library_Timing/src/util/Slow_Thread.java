package Java_Timing;

import java.lang.Runnable;
import java.lang.Thread;

public class Slow_Thread implements Runnable {
	long sleepytime;

	Slow_Thread(long sleepytime) {
		this.sleepytime = sleepytime;
	}

	private void take_nap() {
		try {
			Thread.sleep(this.sleepytime);
		} catch (InterruptedException e) {
			return;
		}
	}

	public void run() {
		take_nap();
	}
}
