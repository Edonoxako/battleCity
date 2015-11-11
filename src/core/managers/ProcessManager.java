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
	//Добавляет процесс в список, возвращая true. Если в списке есть такой процесс вернет false.
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
	//Возвращает индекс процесса в списке, если такого процесса нет вернет -1;
	public int getIndexOf(Process p){
		return ProcessList.indexOf(p);
	}
	//Запустит все процессы из списка, вернув true. Если список пуст вернет false.
	public boolean startAll() {
		if(ProcessList.isEmpty())
			return false;
		
		for(Process p: ProcessList){
			p.start();
		}
		return true;
	}
	
	//Уничтожает все процессы из списка вернув true. Вернет false если список был пуст.
	public boolean killAll() {
		if(ProcessList.isEmpty())
			return false;
		for(Process p: ProcessList){
			p.stop();
		}
		return ProcessList.removeAll(ProcessList);
	}
	
	
	//Приостанавливает все процессы вернув true. Вернет false если список был пуст.
	public boolean stopAll() {
		if(ProcessList.isEmpty())
			return false;
		
		for(Process p: ProcessList){
				if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return true;
	}
	
	//Приостанавливает процесс
	public void stop(Process p){
		if(ProcessList.contains(p)){
			if(p.getProcessState() != ProcessState.waiting.getState())
				p.pause();
		}
		return;
	}
	//Запускает процесс
	public void start(Process p){
		if(ProcessList.contains(p)){
				p.start();
		}
		return;
	}
	
	//Уничтожает процесс
	public void kill(Process p){
		if(ProcessList.contains(p)){
				p.stop();
		}
		return;
	}
}
