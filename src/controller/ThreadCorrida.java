package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorrida extends Thread {
	private int IdCarro;
	private Semaphore semaforo;
	private Semaphore mutex;
	private static String[] escuderias = {" McLaren ", " Mercedes ", " Red Bull ", " Williams ", " ferrari ", " Aston Martin ", " Sauber " };

	public ThreadCorrida(int IdCarro, Semaphore semaforo, Semaphore mutex) {
		this.IdCarro = IdCarro;
		this.semaforo = semaforo;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		double tempo = corrida(escuderias[i], IdCarro);
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
