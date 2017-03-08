
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
  
  public List<Pokemon> SellectAllPokemons(){
      EntityManager em = emf.createEntityManager();
      List<Pokemon> resultado = em.createNamedQuery("Pokemon.findAll").getResultList();
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
  
}
