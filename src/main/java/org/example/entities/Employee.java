package org.example.entities;


import java.time.LocalDate;

public class Employee {
    public String id;
    public String meno;
    public String priezvisko;
    private String titul;
    private String pohlavie;
    private LocalDate datumNarodenia;

    // Constructors, getters, and setters

    public Employee(int id,String meno, String priezvisko, String titul, String pohlavie, LocalDate datumNarodenia) {
        this.id = String.valueOf(id);
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.titul = titul;
        this.pohlavie = pohlavie;
        this.datumNarodenia = datumNarodenia;
    }

    public Employee(String meno, String priezvisko, String titul, String pohlavie, LocalDate datumNarodenia) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.titul = titul;
        this.pohlavie = pohlavie;
        this.datumNarodenia = datumNarodenia;
    }

    public Employee() {
        this.meno = "";
        this.priezvisko = "";
        this.titul = "";
        this.pohlavie = "";
        this.datumNarodenia = null;
    }

    public String getId() {
        return id;
    }
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public String getPohlavie() {
        return pohlavie;
    }

    public void setPohlavie(String pohlavie) {
        this.pohlavie = pohlavie;
    }

    public LocalDate getDatumNarodenia() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(LocalDate datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", titul='" + titul + '\'' +
                ", pohlavie='" + pohlavie + '\'' +
                ", datumNarodenia=" + datumNarodenia +
                '}';
    }
}

