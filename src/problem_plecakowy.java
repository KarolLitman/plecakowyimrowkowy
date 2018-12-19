import java.util.ArrayList;
import java.util.List;

class problem_plecakowy{



void dodaj_przedmiot(String nazwa, double masa, double cena){
    lista_przedmiotow.add(new przedmiot(nazwa, masa, cena));
}

void dodaj_przedmiot(przedmiot p){
    lista_przedmiotow.add(p);
}


    public List<przedmiot> lista_przedmiotow = new ArrayList<>();







    public Double policz_wartosc_plecaka()
    {
        Double tmp=0.0;
        for(przedmiot p : lista_przedmiotow)
        {
            tmp = tmp + p.cena;
        }
        return tmp;
    }

    public boolean czy_wysarczajaco_miejsca(przedmiot p)
    {
        return pozostala_masa >= p.masa;
    }


    double dopuszczalna_masa;

    double pozostala_masa;




}
