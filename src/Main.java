public class Main {
	
    public static void main(String[] args) {
    	problem_plecakowy p=new problem_plecakowy();
    	p.lista_przedmiotow.add((new przedmiot("statułetka",20,100)));
    	p.lista_przedmiotow.add((new przedmiot("statułetka1",40,650)));
    	p.lista_przedmiotow.add((new przedmiot("statułetka2",30,450)));
    	p.lista_przedmiotow.add((new przedmiot("statułetka3",10,400)));
    	p.lista_przedmiotow.add((new przedmiot("statułetka4",60,650)));
        System.out.println("Hello World!");
    }
}
