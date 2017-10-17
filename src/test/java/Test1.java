
class A {
	
	int num = 3 ;
	
	public int getNum(){
		return num ;
	}
	
}

class B extends A{

	int num = 4 ;
	
	
}

class Test1{
	public static void main(String [] args){
		B b = new B();
		int num = b.getNum();
		System.out.println(num);
		System.out.println(b.num);
	}
}