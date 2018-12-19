public class Main {
	
    public static void main(String[] args) {





    	problem_plecakowy p=new problem_plecakowy();
    	p.lista_przedmiotow.add((new przedmiot("statu�etka",20,100)));
    	p.lista_przedmiotow.add((new przedmiot("statu�etka1",40,650)));
    	p.lista_przedmiotow.add((new przedmiot("statu�etka2",30,450)));
    	p.lista_przedmiotow.add((new przedmiot("statu�etka3",10,400)));
    	p.lista_przedmiotow.add((new przedmiot("statu�etka4",60,650)));
        System.out.println("Hello World!");





        for(int i=0;i<=5;i++)
		{
			int iteraciones=500;
			int semilla=i;
			int nHormigas = 2;
			double p = 0.01;

			Random rand = new Random(semilla);
			String nombreArchivo = "Knapsack6.txt";

			AntColonyOptymalization ACO = new AntColonyOptymalization();
			Funcion f = new Function(nombreArchivo);

			Hormiga Best = ACO.ImplACO(f,f.n,rand,iteraciones,semilla,p,f.k, nHormigas);

			System.out.println("Semilla:"+semilla+" Fitness:"+Best.getFitness());

System.out.println("\nHorminga BEST:");
for(int j=0;j<f.n;j++){System.out.print(Best.getVector().get(i)+" ");
}


		}

















    }
}
