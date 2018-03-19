package malomarket.product

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "PRODUCT", uniqueConstraints = [UniqueConstraint(name = "PRODUCT_NUMBER_CONSTRAINT", columnNames = ["productNumber"])])
data class Product(
        @Id @GeneratedValue
        @JsonIgnore
        val id: Long?,
        var productNumber: Long?,
        val name: String,
        val description: String
) {
        constructor(name: String, description: String) : this(null, null, name, description)
}