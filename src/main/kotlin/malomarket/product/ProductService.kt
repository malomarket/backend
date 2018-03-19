package malomarket.product

import malomarket.exception.NotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts() = productRepository.findAll()

    fun createNewProduct(product: Product): Product {
        val maxProductNumber = productRepository.findMaxProductNumber()
        product.productNumber = maxProductNumber + 1
        return productRepository.save(product)
    }

    fun getByProductNumber(productNumber: Long): Product {
        try {
            return productRepository.findByProductNumber(productNumber)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException("Product with number $productNumber was not found.", e)
        }
    }
}