package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorrida extends Thread {
	private int carro;
	private Semaphore semaforo;
	private Semaphore mutex;

	public ThreadCorrida(int IdCarro, Semaphore semaforo, Semaphore mutex) {
		this.carro = IdCarro;
		this.semaforo = semaforo;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		String[] escuderias = { " McLaren ", " Mercedes ", " Red Bull ", " Williams ", " ferrari ", " Aston Martin ",
				" Sauber " };

		for (int carro = 1; carro < 2; carro++) {
			for (int i = 0; i < escuderias.length; i++) {
				try {
//----------------<<início da seção crítica: 2 carros da mesma escuderia>>------------------------					
					if (carro == 2) {
						mutex.acquire();
					}
//----------------<<Inicio da seção crítica: apenas 5 carros na corrida>>-------------------------
					semaforo.acquire();

					double tempo = corrida(escuderias[i], carro);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
//-----------------<<Fim da seção crítica: apenas 5 carros na corrida>>-----------------------------
					mutex.release();
//----------------<<fim da seção crítica: 2 carros da mesma escuderia>>-----------------------------
				}
			}
		}
	}

	private double corrida(String escuderia, int idCarro) {
		int circuito = 6000; // 6km de extensão da pista
		int voltas = 3; // quantidade de voltas
		int corrida = voltas * circuito; // extensão da corrida
		double deslTotal = 0.00; // deslocamento total que o piloto irá percorrer
		int tempo = 1000; // 1 segundo
		double deslocamento = 0;

		double ti = (System.nanoTime() / Math.pow(10, 9));// tempo de largada !!

		while (deslocamento < corrida) {

			deslTotal += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Carro #" + idCarro + " da equipe " + escuderia + " percorreu " + deslTotal);
		}
		double tf = (System.nanoTime() / Math.pow(10, 9));// Tempo de chegada!!
		double tt = tf - ti;// Tempo total !!

		System.out.println("Carro #" + idCarro + " cruzou a chegada, Tempo: " + tt + ".s");
		return tt;
	}

}
