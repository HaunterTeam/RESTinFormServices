/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.models;

import java.io.Serializable;
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

import project.dao.EhealthDAO;

/**
 *
 * @author luca
 */
@Entity
@Table(name="Measure")

@NamedQueries({
    @NamedQuery(name="Measure.find", query="SELECT m FROM Measure m WHERE m.idface = :idface")
})

@XmlRootElement(name="measure")
@XmlAccessorType(XmlAccessType.FIELD)
public class Measure implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idmeasure")
    private int idmeasure;
    
    @Column(name="weight")
    private double weight;
    
    @Column(name="height")
    private double height;
    
    @Column(name="idface")
    private String idface;
    
    @Column(name="times")
    private String times;

    public Measure() { }

    public int getIdmeasure() {
        return idmeasure;
    }

    public void setIdmeasure(int idmeasure) {
        this.idmeasure = idmeasure;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getIdface() {
        return idface;
    }

    public void setIdface(String idface) {
        this.idface = idface;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public static double getLastBmi(int idface) {
        Measure ret;
        EntityManager em = EhealthDAO.instance.createEntityManager();
        String idF = idface+"";
        List<Measure> m = em.createNamedQuery("Measure.find", Measure.class)
                .setParameter("idface", idF).getResultList();
        ret = m.get(0);
        EhealthDAO.instance.closeConnections(em);
        
        return ret.weight / (ret.height*ret.height);
    }
    
    public static double getOldBmi(int idface) {
        Measure ret;
        EntityManager em = EhealthDAO.instance.createEntityManager();
        String idF = idface+"";
        List<Measure> m = em.createNamedQuery("Measure.find", Measure.class)
                .setParameter("idface", idF).getResultList();
        ret = m.get(1);
        EhealthDAO.instance.closeConnections(em);
        
        return ret.weight / (ret.height*ret.height);
    }
}