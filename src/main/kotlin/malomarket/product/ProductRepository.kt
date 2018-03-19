package malomarket.product

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long> {

    fun findByProductNumber(productNumber: Long): Product

    @Query("SELECT coalesce(max(p.productNumber),100000) FROM Product p")
    fun findMaxProductNumber(): Long

}