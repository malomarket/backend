package malomarket

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class Application {

    val log = LoggerFactory.getLogger(Application::class.java)

    @GetMapping("/")
    fun home(): String {
        return "Malo Backend is running"
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
