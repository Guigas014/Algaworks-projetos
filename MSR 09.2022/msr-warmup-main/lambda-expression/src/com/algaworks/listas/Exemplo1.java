package com.algaworks.listas;

import java.util.ArrayList;
import java.util.List;

public class Exemplo1 {

	public static void main(String[] args) {
		List<String> nomes = new ArrayList<>();
		
		nomes.add("José");
		nomes.add("Maria");
		nomes.add("João");

    nomes.remove(0);
		
    //FOREACH
		nomes.forEach(System.out::println);

    //FOREACH com Lambda Expression 
    nomes.forEach(nome -> {
      System.out.println(nome);
    });
    //Simplificada
    nomes.forEach(nome -> System.out.println(nome));

    //FOR
    for (int i = 0; i < nomes.size(); i++) {
      System.out.println("Nome: " + nomes.get(i));
    }

    //Enhanced FOR
    for (String nome : nomes) {
      System.out.println("Nome: " + nome);
    }

	}
	
}
