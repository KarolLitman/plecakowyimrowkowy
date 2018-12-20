public class Main {
	
    public static void main(String[] args) throws Exception {




    	problem_plecakowy p=new problem_plecakowy();
    	p.lista_przedmiotow.add((new przedmiot("s1",20,100)));
    	p.lista_przedmiotow.add((new przedmiot("s2",40,650)));
    	p.lista_przedmiotow.add((new przedmiot("s3",30,450)));
    	p.lista_przedmiotow.add((new przedmiot("s4",10,400)));
    	p.lista_przedmiotow.add((new przedmiot("s5",60,650)));

		algorytm_mrowkowy aw=new algorytm_mrowkowy(p);




    }
}
