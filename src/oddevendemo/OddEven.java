package oddevendemo;

class Demo {
	
	Boolean value=false;
	
	synchronized public void even(int num) {
		
		
		if(value){
			
			try {
				wait();
			} 
			catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("Even:"+num);
		
		value=true;
		notifyAll();
	}
	synchronized public void odd(int num){
		
		if(!value){
			try {
				
				wait();
			}
			catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Odd:"+num);
		value=false;
		notifyAll();
	}
}

class Mythread  implements Runnable{
	
	Demo d;
	int i;
	Mythread(Demo demo1) {
		
		this.d=demo1;
	}
	
	public void run(){
		
		for(i=0;i<=100;i++){
			
			if(i%2==0) 
				
				d.even(i);
		}

		
			
	}
}


class Mythread2  implements Runnable{
	
	Demo d;
	int i;
	Mythread2(Demo demo1) {
		
		this.d=demo1;
	}
	
	public void run(){
		
		for(i=0;i<=100;i++){
			
			if(i%2!=0) 
				
				d.odd(i);
		}
		
			
	}
}
public class OddEven {

	public static void main(String[] args) {
			
			Demo demo=new Demo();
			
			Thread t1=new Thread(new Mythread(demo));
			Thread t2=new Thread(new Mythread2(demo));
			
			t1.start();
			t2.start();
	}	
}
