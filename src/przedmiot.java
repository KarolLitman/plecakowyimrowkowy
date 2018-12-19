class przedmiot{
    String nazwa;
    double masa;
    double cena;
    double stosunek;
    przedmiot(String nazwa,double masa, double cena) {

    this.nazwa=nazwa;
    this.masa=masa;
    this.cena=cena;
    stosunek=oblicz_stosunek();
    }

    double oblicz_stosunek(){
        return cena/masa;
    }

    }
