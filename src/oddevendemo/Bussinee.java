package oddevendemo;

class Print{
	
	int number;
	Boolean value=false;
	
	synchronized public void put(int num) {

		if(value){	
			
			try {
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		System.out.println("Put:"+num);
		this.number=num;
		value=true;
		notifyAll();
	
}
	
	synchronized public void get(){
		
		if(!value){
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Get:"+number);
		value=false;
		notifyAll();
	}
}

class Producer implements Runnable{
	
	int i;
	Print p;
	
	Producer(Print print) {
		
		this.p=print;	
	}
	
	public void run(){
		
		for(i=1;i<=10;i++) {
			
			p.put(i);
			
		}
	}
}

class Consumer implements Runnable{
	
	int i;
	Print p;
	
	Consumer(Print print) {
		
		this.p=print;
	}
	
	public void run(){
		
			p.get();
			
		
	}
}

public class Bussinee {

	public static void main(String[] args) {
	
		Print print1=new Print();
		
		Thread t1=new Thread(new Producer(print1));
		Thread t2=new Thread(new Consumer(print1));
		
		t1.start();
		t2.start();
		
		

	}

}
