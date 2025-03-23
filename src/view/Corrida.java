package view;

import java.util.concurrent.Semaphore;
import controller.ThreadCorrida;

public class Corrida {
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		Semaphore semaforo = new Semaphore(5);
		
		for(int IdCarro = 1; IdCarro < 15; IdCarro++) {
			Thread t = new ThreadCorrida(IdCarro, semaforo, mutex);
			t.start();
		}
	}
}
