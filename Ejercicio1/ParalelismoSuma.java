class Sumador extends Thread {

    private int inicio;
    private int fin;
    private long suma = 0;

    public Sumador(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        for (int i = inicio; i <= fin; i++) {
            suma += i;
        }
        System.out.println("Suma parcial de " + inicio + " a " + fin + ": " + suma);
    }

    public long getSuma() {
        return suma;
    }
}
public class ParalelismoSuma {

    public static void main(String[] args) {

        // Crear los 4 hilos
        Sumador hilo1 = new Sumador(1, 250000);
        Sumador hilo2 = new Sumador(250001, 500000);
        Sumador hilo3 = new Sumador(500001, 750000);
        Sumador hilo4 = new Sumador(750001, 1000000);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            // Esperar que todos terminen
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sumar resultados
        long sumaTotal = hilo1.getSuma() + hilo2.getSuma() + hilo3.getSuma() + hilo4.getSuma();

        System.out.println("La suma total es: " + sumaTotal);
    }
}
