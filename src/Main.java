public class Main {
	
    public static void main(String[] args) throws Exception {




    	problem_plecakowy p=new problem_plecakowy();
    	p.wszystkie_przedmioty.add((new przedmiot("s1",20,100)));
    	p.wszystkie_przedmioty.add((new przedmiot("s2",40,650)));
    	p.wszystkie_przedmioty.add((new przedmiot("s3",30,450)));
    	p.wszystkie_przedmioty.add((new przedmiot("s4",10,400)));
    	p.wszystkie_przedmioty.add((new przedmiot("s5",60,650)));

		algorytm_mrowkowy aw=new algorytm_mrowkowy(p);




    }
}
