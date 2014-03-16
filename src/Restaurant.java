package mftoth.restaurantsim.logic;

import java.util.ArrayList;

public class Restaurant{

	private ArrayList<Employee> employees;
	private ArrayList<Customer> customers;
	private Scheduler scheduler;
	private Time timer;
	//Constructor()
	public Restaurant(){

		//initialize timer object which will keep track of and organize time
		timer = new Time();

		employees = new ArrayList<Employee>();
		customers = new ArrayList<Customer>();

		Customer c1 = new Customer();
		customers.add(c1);
		//Scheduler taskQueue = new Scheduler;

		Employee e1 = new Employee();
		Employee e2 = new Employee();

	
		employees.add(e1);
		employees.add(e2);

		scheduler = new Scheduler();

	}//end: Constructor()

	//loop(): main restaurant loop
	public void loop(){

	}//end: loop()

	public void update(int delta){

		//add ms to timer object
		timer.addMilliSecond(delta);

		if(timer.getMilliSeconds()>5000 && timer.getMilliSeconds()<5025){
			//customers.add(new Customer());
		}

		//System.out.println(timer.getSeconds() + " seconds");

		//Loop through each employee and update task progress
		for(int i = 0; i < employees.size(); i++){

			Employee emp = employees.get(i);
			//Employee is not busy
			if(emp.isBusy() != true){
				
				//employee is not busy, look for next task
				Task task = scheduler.getNextTask(emp.getPreferredTask());

				if(task!=null)
					emp.setTask(task); //set task

			//Employee is working on a task
			}else{

				//consume time on task. This will automatically set employee to not busy if the task runs out
				emp.doTask(delta);

			}//end if employee not busy
		}//end for
		
		
	}//end: Update();

	public void addCustomer(){
		customers.add(new Customer());
	}

	public ArrayList<Customer> getCustomers(){
		return customers;
	}

}//end class restaurant