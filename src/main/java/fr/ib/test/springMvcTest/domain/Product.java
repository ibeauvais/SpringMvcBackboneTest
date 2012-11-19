package fr.ib.test.springMvcTest.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Product")
@NamedQuery(name = Product.FIND_BY_NAME_LIKE,query = "select p from Product p where p.name like :name")
public class Product {

    public static final String FIND_BY_NAME_LIKE = "FIND_BY_NAME_LIKE";
    @Id
    public long id;

    @NotNull
    @Size(min = 1, max = 25)
    public String name;

    @Size( max = 500)
    public String description;

    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;

}
