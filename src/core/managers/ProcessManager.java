package core.managers;

import java.util.ArrayList;
import java.util.Random;

import core.model.Process;
import core.model.Process.ProcessState;

public class ProcessManager{
	private int INIT_SIZE_PROCESSLIST = 5;
	private ArrayList<Process> ProcessList;
	
	public ProcessManager() {
		ProcessList = new ArrayList<Process>(INIT_SIZE_PROCESSLIST);
	}
	
	public ArrayList<Process> getProcessList(){
		return ProcessList;
	}
	
	//��������� ������� � ������, ��������� true. ���� � ������ ���� ����� ������� ������ false.
	public boolean addProc(Process p) {
		if(!ProcessList.contains(p)){
			ProcessList.add(p);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeProc(Process p) {
		return ProcessList.remove(p);	
	}
	
	//���������� ������ �������� � ������, ���� ������ �������� ��� ������ -1;
	public int getIndexOf(Process p){
		return ProcessList.indexOf(p);
	}
	
	//�������� ��� �������� �� ������, ������ true. ���� ������ ���� ������ false.
	public boolean startAll() {
		if(ProcessList.isEmpty())
			return false;
		
		for(Process p: ProcessList)
				p.start();
		return true;
	}
	
	//���������� ��� �������� �� ������ ������ true. ������ false ���� ������ ��� ����.
	public boolean killAll() {
		if(ProcessList.isEmpty())
			return false;
		for(Process p: ProcessList){
			p.stop();
		}
		return ProcessList.removeAll(ProcessList);
	}
	
	//���������������� ��� �������� ������ true. ������ false ���� ������ ��� ����.
	public boolean stopAll() {
		if(ProcessList.isEmpty())
			return false;
		
		for(Process p: ProcessList){
				if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return true;
	}
	
	//���������������� �������
	public void stop(Process p){
		if(ProcessList.contains(p)){
			if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return;
	}
	
	//��������� �������
	public void start(Process p){
		if(ProcessList.contains(p)){
				p.start();
		}
		return;
	}
	
	//���������� �������
	public void kill(Process p){
		if(ProcessList.contains(p)){
				p.stop();
		}
		ProcessList.remove(p);
		return;
	}
	public int generateID(){
		if(ProcessList.isEmpty()){
			return 0;
		}
		int id = new Random().nextInt(ProcessList.size()+INIT_SIZE_PROCESSLIST);
		boolean checkunique = false;
		while(!checkunique){
			id = new Random().nextInt(ProcessList.size()+INIT_SIZE_PROCESSLIST);
			checkunique = true;
			for(Process p: ProcessList)
				if(p.getId() == id) 
					checkunique=false;
		}
		return id;
	}
}
