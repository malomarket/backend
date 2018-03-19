package malomarket.product

import malomarket.exception.NotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.dao.EmptyResultDataAccessException

class ProductServiceTest {

    @Test
    fun findsAllProducts() {
        val productRepository = mock(ProductRepository::class.java)
        val service = ProductService(productRepository)
        val product1 = Product(1L, 100001L, "Product 1", "Description 1")
        val product2 = Product(2L, 100002L, "Product 2", "Description 2")
        given(productRepository.findAll()).willReturn(arrayListOf(product1, product2))

        val result = service.getAllProducts() as List<*>

        assertEquals(2, result.size)
        assertEquals(product1, result[0])
        assertEquals(product2, result[1])
    }

    @Test
    fun createsANewProduct() {
        val productRepository = mock(ProductRepository::class.java)
        val service = ProductService(productRepository)
        val product = Product(name = "Name", description = "Description")
        given(productRepository.findMaxProductNumber()).willReturn(100000)
        given(productRepository.save(product)).willReturn(product)

        val createdProduct = service.createNewProduct(product)

        assertEquals(createdProduct.productNumber, 100001L)
    }

    @Test
    fun findsByProductNumber() {
        val productRepository = mock(ProductRepository::class.java)
        val service = ProductService(productRepository)
        val product = Product(1L, 100001L, "Product", "Description")
        given(productRepository.findByProductNumber(100001L)).willReturn(product)

        val result = service.getByProductNumber(100001L)

        assertEquals(product, result)
    }

    @Test
    fun productWasNotFound() {
        val productRepository = mock(ProductRepository::class.java)
        val service = ProductService(productRepository)
        given(productRepository.findByProductNumber(100001L)).willThrow(EmptyResultDataAccessException("Not Found", 1))

        assertThrows(NotFoundException::class.java, { service.getByProductNumber(100001L) })
    }
}