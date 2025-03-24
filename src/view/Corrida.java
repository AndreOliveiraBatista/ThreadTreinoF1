package view;

import java.util.concurrent.Semaphore;
import controller.ThreadCorrida;

public class Corrida {
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);//permissões de carros de mesma equipe na corrida 
		Semaphore semaforo = new Semaphore(5);//permissões de carros na corrida
		
		for(int IdCarro = 0; IdCarro < 2; IdCarro++) {// primeiro e segundo carros da escuderia
			for(int idEscuderia = 0; idEscuderia < 7; idEscuderia++) {//escuderias 
				Thread t = new ThreadCorrida(IdCarro, semaforo, mutex);
				t.start();
			}
		}
	}
}
