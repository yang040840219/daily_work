package daily.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * A GC micro benchmark running a given number of threads that create objects of a given size and dereference them immediately
 * 
 * @author jsound
 */
public class GarbageOnly {


		static class OOMObject {
		}

		public static void main(String[] args) {
			List<OOMObject> list = new ArrayList<OOMObject>();

			while (true) {
				list.add(new OOMObject());
			}
		}

	
}