package daily.concurrent;

import java.util.concurrent.Callable;

public class WorkerThreadCallable implements Callable<String> {

	private String command;

	public WorkerThreadCallable(String s) {
		this.command = s;
	}

	@Override
	public String call() throws Exception {

		System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
		String value = processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
		return value;
	}

	private String processCommand() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "callable-" + command;
	}

	@Override
	public String toString() {
		return this.command;
	}

}
