package Java_Timing;

import java.lang.Runnable;
import java.lang.Thread;

public class Test_Thread implements Runnable {
	public void run() {
		return;
	}

	public static void main(String args[]) {
		(new Thread(new Test_Thread())).start();
	}
}
