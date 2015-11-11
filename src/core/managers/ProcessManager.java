package core.managers;

import java.util.ArrayList;

import core.model.Process;
import core.model.Process.ProcessState;

public class ProcessManager{
private ArrayList<Process> ProcessList;
	
	public ProcessManager() {
		ProcessList = new ArrayList<Process>(5);
	}
	
	public ArrayList<Process> getProcList(){
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
		
		for(Process p: ProcessList){
			p.start();
		}
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
		return;
	}
}
