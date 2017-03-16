
package persistencia;

/**
 *
 * @author xaviB
 */
public class BattleDTO {
    
    private String name;
    private long victories;

    public BattleDTO(String name, long victories) {
        this.name = name;
        this.victories = victories;
    }
    
    public BattleDTO(){
    
    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public long getVictories() {        return victories;    }
    public void setVictories(long victories) {        this.victories = victories;    }
    
    
    
}
