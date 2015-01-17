package project.models;

import project.dao.EhealthDAO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the "Measure" database table.
 * 
 */
@Entity
@Table(name="Phrase")

@NamedQueries({
    @NamedQuery(name="Phrase.find", query="SELECT p FROM Phrase p "
            + "WHERE p.weathertype = :w AND p.bmirange = :bmi AND p.change = :ch"),
})

@XmlRootElement(name="phrase")
@XmlAccessorType(XmlAccessType.FIELD)
public class Phrase implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idphrase")
    private int idphrase;
    
    @Column(name="phrase")
    private String phrase;
    
    @Column(name="weathertype")
    private int weathertype;
    
    @Column(name="bmirange")
    private int bmirange;
    
    @Column(name="change")
    private int change;
    
    @Column(name="activity")
    private String activity;

    public Phrase() {
    }

    public int getIdphrase() {
        return idphrase;
    }

    public void setIdphrase(int idphrase) {
        this.idphrase = idphrase;
    }
    
    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getWeathertype() {
        return weathertype;
    }

    public void setWeathertype(int weathertype) {
        this.weathertype = weathertype;
    }

    public int getBmirange() {
        return bmirange;
    }

    public void setBmirange(int bmirange) {
        this.bmirange = bmirange;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public static Phrase getPhraseByWeatherAndByBmi(int bmi,int change,int wType) {
        EntityManager em = EhealthDAO.instance.createEntityManager();
        List<Phrase> m = em.createNamedQuery("Phrase.find", Phrase.class)
                .setParameter("w", wType)
                .setParameter("ch", change)
                .setParameter("bmi", bmi).getResultList();
        EhealthDAO.instance.closeConnections(em);
        Collections.shuffle(m);
        
        return m.get(0);
    }

    @Override
    public String toString() {
        return phrase;
    }
    
}