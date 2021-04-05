/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
@Entity
@Table(name = "CALCULATIONS")
@NamedQueries({
    @NamedQuery(name = "Calculation.findAll", query = "SELECT c FROM Calculation c"),
    @NamedQuery(name = "Calculation.findById", query = "SELECT c FROM Calculation c WHERE c.id = :id"),
    @NamedQuery(name = "Calculation.findByBeginningValue", query = "SELECT c FROM Calculation c WHERE c.beginningValue = :beginningValue"),
    @NamedQuery(name = "Calculation.findByEndValue", query = "SELECT c FROM Calculation c WHERE c.endValue = :endValue"),
    @NamedQuery(name = "Calculation.findByMathematicalFunction", query = "SELECT c FROM Calculation c WHERE c.mathematicalFunction = :mathematicalFunction"),
    @NamedQuery(name = "Calculation.findByResult", query = "SELECT c FROM Calculation c WHERE c.result = :result")})
public class Calculation implements Serializable {

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
     * Beginning of integration
     */
    @Column(name = "BEGINNING_VALUE")
    private Integer beginningValue;

    /**
     * End of integration
     */
    @Column(name = "END_VALUE")
    private Integer endValue;

    /**
     * Integrated function
     */
    @Size(max = 30)
    @Column(name = "MATHEMATICAL_FUNCTION")
    private String mathematicalFunction;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    /**
     * Result of the integration
     */
    @Column(name = "RESULT")
    private Double result;

    /**
     * Collection of calculations
     */
    @OneToMany(mappedBy = "calculation")
    private Collection<CalcHistoryRecord> calcHistoryRecordCollection;

    /**
     * Constructor
     */
    public Calculation() {
    }

    /**
     * Parametrized constructor
     *
     * @param id ID
     */
    public Calculation(Integer id) {
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
     * Sets Id
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets beginning
     *
     * @return beginning
     */
    public Integer getBeginningValue() {
        return beginningValue;
    }

    /**
     * Sets beginning
     *
     * @param beginningValue beginning
     */
    public void setBeginningValue(Integer beginningValue) {
        this.beginningValue = beginningValue;
    }

    /**
     * Gets end value
     *
     * @return end value
     */
    public Integer getEndValue() {
        return endValue;
    }

    /**
     * Sets end value
     *
     * @param endValue end value
     */
    public void setEndValue(Integer endValue) {
        this.endValue = endValue;
    }

    /**
     * Gets integrated function
     *
     * @return integrated function
     */
    public String getMathematicalFunction() {
        return mathematicalFunction;
    }

    /**
     * Sets integrated function
     *
     * @param mathematicalFunction integrated function
     */
    public void setMathematicalFunction(String mathematicalFunction) {
        this.mathematicalFunction = mathematicalFunction;
    }

    /**
     * Gets result
     *
     * @return result
     */
    public Double getResult() {
        return result;
    }

    /**
     * Sets result
     *
     * @param result result
     */
    public void setResult(Double result) {
        this.result = result;
    }

    /**
     * Gets collection of calculations
     *
     * @return collection of calculations
     */
    public Collection<CalcHistoryRecord> getCalcHistoryRecordCollection() {
        return calcHistoryRecordCollection;
    }

    /**
     * Sets collection of calculations
     *
     * @param calcHistoryRecordCollection collection of calculations
     */
    public void setCalcHistoryRecordCollection(Collection<CalcHistoryRecord> calcHistoryRecordCollection) {
        this.calcHistoryRecordCollection = calcHistoryRecordCollection;
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
        if (!(object instanceof Calculation)) {
            return false;
        }
        Calculation other = (Calculation) object;
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
        return "pl.polsl.entities.Calculation[ id=" + id + " ]";
    }

}
