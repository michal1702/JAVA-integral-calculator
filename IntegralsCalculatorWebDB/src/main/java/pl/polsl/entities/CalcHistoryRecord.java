/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
@Entity
@Table(name = "CALC_HISTORY")
@NamedQueries({
    @NamedQuery(name = "CalcHistoryRecord.findAll", query = "SELECT c FROM CalcHistoryRecord c"),
    @NamedQuery(name = "CalcHistoryRecord.findById", query = "SELECT c FROM CalcHistoryRecord c WHERE c.id = :id"),
    @NamedQuery(name = "CalcHistoryRecord.findByDate", query = "SELECT c FROM CalcHistoryRecord c WHERE c.date = :date")})
public class CalcHistoryRecord implements Serializable {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    /**
     * Date
     */
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Calculation record
     */
    @JoinColumn(name = "CALCULATION", referencedColumnName = "ID")
    @ManyToOne
    private Calculation calculation;

    /**
     * Constructor
     */
    public CalcHistoryRecord() {
    }

    /**
     * Parametrized constructor
     *
     * @param id ID
     */
    public CalcHistoryRecord(Integer id) {
        this.id = id;
    }

    /**
     * Gets ID
     *
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets calculations information
     *
     * @return calculation information
     */
    public Calculation getCalculation() {
        return calculation;
    }

    /**
     * Sets calculation
     *
     * @param calculation calculaion
     */
    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    /**
     * Hashing method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if objects are equals
     *
     * @param object object
     * @return if equal
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalcHistoryRecord)) {
            return false;
        }
        CalcHistoryRecord other = (CalcHistoryRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Converts package name to string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "pl.polsl.entities.CalcHistoryRecord[ id=" + id + " ]";
    }

}
