package daily.param;

public class App {
	
	public static void changePerson(Person person){
		 //person = new Person();
		 person.name = "10000";
	}
	
	public static void main(String[] args) {
		Person p = new Person();
		p.name = "100" ;
		
		changePerson(p) ;
		
		System.out.println(p.name);
				
	}

}
