package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.java350.model.Employe;

import com.ipiecoles.java.java350.java350.model.Entreprise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;


public class EmployeTest {
    @Test
    public void testGetNombreAnneeAncienneteDateEmbaucheNull() {
        //Given = Initialisation des données d'entrée
        Employe mochi = new Employe();
        mochi.setDateEmbauche(null);

        //When = Exécution de la méthode à tester
        Integer nbAnnee = mochi.getNombreAnneeAnciennete();

        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(0, nbAnnee.intValue());
    }

    @Test
    public void testGetNombreAnneeAncienneteDateNow() {
        //Given = Initialisation des données d'entrée
        Employe mochi = new Employe();
        mochi.setDateEmbauche(LocalDate.now());

        //When = Exécution de la méthode à tester
        Integer nbAnnee = mochi.getNombreAnneeAnciennete();

        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(0, nbAnnee.intValue());
    }

    @Test
    public void testGetNombreAnneeAncienneteDateNominale() {
        //Given = Initialisation des données d'entrée
        Employe mochi = new Employe();

        mochi.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When = Exécution de la méthode à tester
        Integer nbAnnee = mochi.getNombreAnneeAnciennete();

        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(2, nbAnnee.intValue());
    }

    @Test
    public void testGetNombreAnneeAncienneteDatePosterieure() {
        //Given = Initialisation des données d'entrée
        Employe mochi = new Employe();
        LocalDate date = LocalDate.now();
        date.plusYears(1);
        mochi.setDateEmbauche(date);


        //When = Exécution de la méthode à tester
        Integer nbAnnee = mochi.getNombreAnneeAnciennete();

        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(0, nbAnnee.intValue());
    }

    @ParameterizedTest(name = "Performance {0} Matricule {1} NbAnnee {2} tempsPartiel {3} donne Prime {4}")
    @CsvSource({
            "1,'M00231',0,1.0, 1700.0",
            "1,'M00231',2,1.0, 1900.0",
            "1,'M00231',0,1.0, 1700.0",
            "1,'M00231',2,1.0, 1900.0"
            })
    public void testGetPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennte,Double tempsPartiel, Double primeAnnuelle){
        //Given = Initialisation des données d'entrée
        Employe mochi = new Employe("John","Doe",matricule,LocalDate.now().minusYears(nbYearsAnciennte), Entreprise.SALAIRE_BASE,performance,tempsPartiel);
        mochi.setPerformance(performance);
        mochi.setMatricule(matricule);
        mochi.setDateEmbauche(LocalDate.now().minusYears(nbYearsAnciennte));
        mochi.setTempsPartiel(tempsPartiel);


        //When = Exécution de la méthode à tester
        Double prime = mochi.getPrimeAnnuelle();

        //Then = Vérifications de ce qu'a fait la méthode
        Assertions.assertEquals(primeAnnuelle, prime);

    }
}



