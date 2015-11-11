package core.managers;

import java.util.ArrayList;
import core.model.Process;

public class ProcessManager extends Thread{
private ArrayList<Process> ProcessList;
	
	public ProcessManager() {
		ProcessList = new ArrayList<Process>(5);
	}
	
	public ArrayList<Process> getProcList(){
		return ProcessList;
	}
	
	public void addProc(Process p) {
		if(!ProcessList.contains(p)){
			ProcessList.add(p);
		}else{
			System.out.println("This element is already in the list");
		}
	}
	
	public void removeProc(Process p) {
		if(ProcessList.remove(p)){
		}
		
	}
	
	public int getIndexOf(Process p){
		return ProcessList.indexOf(p);
	}
	
	public void startAll() {
		if(ProcessList.isEmpty()){
			System.out.println("ProcessLIst is empty");
			return;
		}
		for(Process p: ProcessList){
			p.start();
		}
	}
	
	public void killAll() {
		for(Process p: ProcessList){
			p.stop();
		}
		ProcessList.removeAll(ProcessList);
	}
	
	public void stopAll() {
		for(Process p: ProcessList){
			try {
				p.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
