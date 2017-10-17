package daily.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import scala.concurrent.util.Unsafe;

public class Test1 {

	public static Unsafe getUnsafe() {
		Constructor<Unsafe> unsafeConstructor = null;
		try {
			unsafeConstructor = Unsafe.class.getDeclaredConstructor();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		unsafeConstructor.setAccessible(true);
		Unsafe unsafe = null;
		try {
			unsafe = unsafeConstructor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unsafe;
	}

	public static void main(String[] args) {

	}
}
