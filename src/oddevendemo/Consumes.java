package oddevendemo;

import java.util.LinkedList;

class Problem{
	
	LinkedList<Integer> list=new LinkedList<>();
	int Pvalue=0;
	int capacity=5;

	
	public void put() throws InterruptedException {
		
			synchronized(this){
						
				if(list.size()==capacity) {
					wait();
				}
					list.add(++Pvalue);
					System.out.println("put"+Pvalue );
				
					notify();
				
		}
		
			}
			
	public void get() throws InterruptedException {
		
		synchronized(this) {
			if(list.isEmpty()) {
				
				wait();
			}
			System.out.println("Got"+list.pop());
			
			notify();
			}	
	
	}
}

class Producers implements Runnable {
	
	int i=0;
	Problem problem;
	Producers(Problem p)  {
		this.problem=p;		
	}
	
	public void run() {

		for(i=0;i<10;i++) {
		try {
			problem.put();
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
}

class Consumers implements Runnable {
	
	Problem problem;
	int i=0;
	Consumers(Problem p) {
		
		this.problem=p;
		
	}
	
	public void run() {
		
		for(i=0;i<10;i++){

		try {
			problem.get();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	
	}
	
}

public class Consumes{
	   public static void main(String[] args) {

		   
		   Problem prob=new Problem();
		   Thread t1=new Thread(new Producers(prob));
		   Thread t2=new Thread(new Consumers(prob));
		   
		   t1.start();
		   t2.start();
	   }
	}
	