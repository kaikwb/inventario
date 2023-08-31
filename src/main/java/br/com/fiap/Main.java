package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        saveItem(manager);

        Inventario inventario = findInventarioById(manager);
        System.out.println(inventario);

        List<Bem> bens = getAllBens(manager);

        for (Bem bem : bens) {
            System.out.println(bem);
        }

        manager.close();
        factory.close();
    }

    public static void saveItem(EntityManager manager) {
        TipoDeBem tipoDeBem = new TipoDeBem();
        tipoDeBem.setNome("Computador");

        Departamento departamento = new Departamento();
        departamento.setNome("Tecnologia");

        Inventario inventario = new Inventario();
        inventario.setDepartamento(departamento).setInicio(LocalDate.now());

        Bem bem = new Bem();
        bem.setNome("Computador Dell").setTipo(tipoDeBem).setEtiqueta("123456").setLocalizacao(departamento).setAquisicao(LocalDate.now());

        manager.getTransaction().begin();
        manager.persist(bem);
        manager.persist(inventario);
        manager.getTransaction().commit();
    }

    public static Inventario findInventarioById(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Digite o id do inventário: "));
        return manager.find(Inventario.class, id);
    }

    public static List<Bem> getAllBens(EntityManager manager) {
        return manager.createQuery("SELECT b FROM Bem b", Bem.class).getResultList();
    }
}