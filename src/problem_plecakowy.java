import java.util.ArrayList;
import java.util.List;

class problem_plecakowy{



    public List<przedmiot> wszystkie_przedmioty = new ArrayList<>();
    public List<przedmiot> przedmioty_w_plecaku = new ArrayList<>();



    public String toString(){
        return przedmioty_w_plecaku+"";
    }

void dodaj_przedmiot(String nazwa, double masa, double cena){
    przedmioty_w_plecaku.add(new przedmiot(nazwa, masa, cena));
}

void dodaj_przedmiot(przedmiot p){
    przedmioty_w_plecaku.add(p);
}




    public Double policz_wartosc_plecaka()
    {
        Double tmp=0.0;
        for(przedmiot p : przedmioty_w_plecaku)
        {
            tmp = tmp + p.cena;
        }
        return tmp;
    }

    public boolean czy_wystarczajaco_miejsca(przedmiot p)
    {
        return pozostala_masa >= p.masa;
    }



    double pozostala_masa=50;




}
