package pl.polsl.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.polsl.entities.CalcHistoryRecord;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-05T20:26:48")
@StaticMetamodel(Calculation.class)
public class Calculation_ { 

    public static volatile SingularAttribute<Calculation, Double> result;
    public static volatile CollectionAttribute<Calculation, CalcHistoryRecord> calcHistoryRecordCollection;
    public static volatile SingularAttribute<Calculation, Integer> endValue;
    public static volatile SingularAttribute<Calculation, Integer> id;
    public static volatile SingularAttribute<Calculation, Integer> beginningValue;
    public static volatile SingularAttribute<Calculation, String> mathematicalFunction;

}