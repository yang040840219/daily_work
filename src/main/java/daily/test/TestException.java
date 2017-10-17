package daily.test;

public class TestException {

	ClassLoader cl = getClass().getClassLoader();
	ClassLoader cl1 = Thread.currentThread().getContextClassLoader();
	
	public int hello(){
		
		try{
			System.out.println(cl);
			System.out.println(cl1);
			return 0 ;
		}catch(Exception e){
			e.printStackTrace();
			return 1 ;
		}finally {
			System.out.println("finally");
		}
		
	}
	
	public static void main(String args[]){
		 System.out.println(new TestException().hello());
	}
	
	
}
