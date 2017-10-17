package daily.jvm.singleton;

public class Singleton2 {
	
	private Singleton2(){
		
	}
	
	// 静态内部类
	public static class InstanceHolder {
		public static final Singleton2 instance = new Singleton2();
	}
	
	public static Singleton2 getInstance(){
		return InstanceHolder.instance ;
	}

}
