package core.managers;

import java.util.ArrayList;

import core.model.Process;
import core.model.Process.ProcessState;

public class ProcessManager{
private ArrayList<Process> ProcList;
	
	public ProcessManager() {
		ProcList = new ArrayList<Process>(5);
	}
	
	public ArrayList<Process> getProcList(){
		return ProcList;
	}
	//��������� ������� � ������, ��������� true. ���� � ������ ���� ����� ������� ������ false.
	public boolean addProc(Process p) {
		if(!ProcList.contains(p)){
			ProcList.add(p);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeProc(Process p) {
		return ProcList.remove(p);	
	}
	//���������� ������ �������� � ������, ���� ������ �������� ��� ������ -1;
	public int getIndexOf(Process p){
		return ProcList.indexOf(p);
	}
	//�������� ��� �������� �� ������, ������ true. ���� ������ ���� ������ false.
	public boolean startAll() {
		if(ProcList.isEmpty())
			return false;
		
		for(Process p: ProcList){
			p.start();
		}
		return true;
	}
	
	//���������� ��� �������� �� ������ ������ true. ������ false ���� ������ ��� ����.
	public boolean killAll() {
		if(ProcList.isEmpty())
			return false;
		for(Process p: ProcList){
			p.stop();
		}
		return ProcList.removeAll(ProcList);
	}
	
	
	//���������������� ��� �������� ������ true. ������ false ���� ������ ��� ����.
	public boolean stopAll() {
		if(ProcList.isEmpty())
			return false;
		
		for(Process p: ProcList){
				if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return true;
	}
	
	//���������������� �������
	public void stop(Process p){
		if(ProcList.contains(p)){
			if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return;
	}
	//��������� �������
	public void start(Process p){
		if(ProcList.contains(p)){
				p.start();
		}
		return;
	}
	
	//���������� �������
	public void kill(Process p){
		if(ProcList.contains(p)){
				p.stop();
		}
		return;
	}
}
