package fr.ib.test.springMvcTest.domain;


import fr.ib.test.springMvcTest.common.xml.DateTimeJsonSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Product")
@NamedQueries({
        @NamedQuery(name = Product.FIND_BY_NAME_LIKE, query = "select p from Product p where p.name like :name"),
        @NamedQuery(name = Product.FIND_ALL, query = "select p from Product p")

})
public class Product {

    public static final String FIND_BY_NAME_LIKE = "FIND_BY_NAME_LIKE";
    public static final String FIND_ALL = "FIND_ALL";
    @Id
    @GeneratedValue
    public long id;

    @NotNull
    @Size(min = 1, max = 25)
    public String name;

    @Size(max = 500)
    public String description;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using=DateTimeJsonSerializer.class)
    public DateTime creationDate;


    @PrePersist
    void prePersist() {
        creationDate = new DateTime();
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
