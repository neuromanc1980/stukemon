
package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import persistencia.Battle;
import persistencia.BattleDTO;
import persistencia.Pokemon;
import persistencia.Trainer;

/**
 aqui van las consultas y tal
 */
@Stateless
public class StukemonEJB {

  @PersistenceUnit EntityManagerFactory emf;
  
  public List<Trainer> SellectAllTrainers(){
      EntityManager em = emf.createEntityManager();
      List<Trainer> resultado = em.createNamedQuery("Trainer.findAll").getResultList();
      return resultado;
  }
  
  public void UpgradePokemon(Pokemon p){
      EntityManager em = emf.createEntityManager();
      Pokemon pok = em.find(Pokemon.class, p.getName());
      //em.getTransaction().begin();
      pok.setLife(pok.getLife()+50);
      em.persist(pok);
      Trainer t = em.find(Trainer.class, p.getTrainer().getName());
      t.setPotions(t.getPotions() - 1);
      em.persist(t);
      //em.getTransaction().commit();
      em.close();      
  }
  
  public void buyPotions(Trainer t){
      EntityManager em = emf.createEntityManager();
      Trainer tr = em.find(Trainer.class, t.getName());
      tr.setPoints(tr.getPoints()-10);
      tr.setPotions(tr.getPotions()+1);
      em.persist(tr);
      em.close();
  }
  
  public List<Trainer> SellectTrainerRanking(){
      EntityManager em = emf.createEntityManager();
      List<Trainer> resultado = em.createNamedQuery("Trainer.ranking").getResultList();
      return resultado;
  }
  
  public List<Pokemon> SellectAllPokemons(){
      EntityManager em = emf.createEntityManager();
      List<Pokemon> resultado = em.createNamedQuery("Pokemon.findAll").getResultList();
      return resultado;
  }
  
  public List<Pokemon> SellectAllPokemonsOrdered(){
      EntityManager em = emf.createEntityManager();
      List<Pokemon> resultado = em.createNamedQuery("Pokemon.findAllOrdered").getResultList();
      return resultado;
  }
  
  public ArrayList<BattleDTO> SellectAllBattles(){
      //System.out.println("1");
      EntityManager em = emf.createEntityManager();
      List resultado = em.createQuery("select b.winner.name, count(b.winner) from Battle b "
              + "group by b.winner order by count(b.winner) desc").getResultList();
      //System.out.println("2");
      ArrayList <BattleDTO> batallas = new ArrayList<>();
      Iterator it = resultado.iterator();
      while(it.hasNext()) {
          Object[] b = (Object[]) it.next();
          String nombre = (String) b[0];
          long contador = (long) b[1];
          batallas.add(new BattleDTO(nombre, contador));
      }
      
      em.close();
      return batallas;
  }
  
   public List<Pokemon> SellectAllPokemonsOrderedBattle(){
      EntityManager em = emf.createEntityManager();
      List<Pokemon> resultado = em.createNamedQuery("Pokemon.findAllOrderedBattle").getResultList();
      return resultado;
  }
  
  public boolean insertarTrainer(Trainer t){
      if(!comprobarTrainer(t)){
          EntityManager em = emf.createEntityManager();
          em.persist(t);
          em.flush();
          em.close();
          return true;
      } else return false;
  }
  
  public void bajaPokemon(String name){
            EntityManager em = emf.createEntityManager();
            Pokemon p = em.find(Pokemon.class, name);
            em.remove(p);
            em.flush();
            em.close();
      
  }
  
  public void insertarPokemon(Pokemon p){
      EntityManager em = emf.createEntityManager();
      em.persist(p);
      em.flush();
      em.close();
  }
  
  public boolean comprobarTrainer(Trainer t){
      EntityManager em = emf.createEntityManager();
      Trainer trainer = em.find(Trainer.class, t.getName());
      em.close();
      return trainer != null;
  }
  
   public List<Trainer> AvailableTrainers(){
      EntityManager em = emf.createEntityManager();
      List<Trainer> resultado = em.createNamedQuery("Trainer.findAll").getResultList();
      em.flush();
      em.close();
      return resultado;
  }
  
  public Trainer getTrainerByName(String name){
      EntityManager em = emf.createEntityManager();
      Query q = em.createNamedQuery("Trainer.findByName");
      q.setParameter("name", name);
      Trainer t = (Trainer) q.getSingleResult();
      //Trainer t = new Trainer();      
      return t;
      
  } 
  
   public Pokemon getPokemonByName(String name){
      EntityManager em = emf.createEntityManager();
      Query q = em.createNamedQuery("Pokemon.findByName");
      q.setParameter("name", name);
      Pokemon p = (Pokemon) q.getSingleResult();
      return p;
      
  } 
  
}
