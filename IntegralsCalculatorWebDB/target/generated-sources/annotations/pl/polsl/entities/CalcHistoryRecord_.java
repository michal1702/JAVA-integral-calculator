package pl.polsl.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.polsl.entities.Calculation;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-05T20:26:48")
@StaticMetamodel(CalcHistoryRecord.class)
public class CalcHistoryRecord_ { 

    public static volatile SingularAttribute<CalcHistoryRecord, Date> date;
    public static volatile SingularAttribute<CalcHistoryRecord, Calculation> calculation;
    public static volatile SingularAttribute<CalcHistoryRecord, Integer> id;

}