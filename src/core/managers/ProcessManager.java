package core.managers;

import java.util.ArrayList;
import core.model.Process;

public class ProcessManager extends Thread{
	private ArrayList<Process> ProcList;
	
	public ProcessManager() {
		ProcList = new ArrayList<Process>(5);
	}
	
	public ArrayList<Process> getProcList(){
		return ProcList;
	}
	
	public void addProc(Process p) {
		if(!ProcList.contains(p)){
			ProcList.add(p);
		}else{
			System.out.println("this elemen enter is list");
		}
	}
	
	public void removeProc(Process p) {
		if(ProcList.remove(p)){
			System.out.println("Remoce Pro: ");
		}
		
	}
	
	public int getIndexOf(Process p){
		return ProcList.indexOf(p);
	}
	
	public void startAll() {
		if(ProcList.isEmpty()){
			System.out.println("not start");
			return;
		}
		for(Process p: ProcList){
			p.start();
		}
	}
	
	public void killAll() {
		for(Process p: ProcList){
			p.stop();
		}
		ProcList.removeAll(ProcList);
	}
	
	public void stopAll() {
		for(Process p: ProcList){
			try {
				p.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
